package pl.projects.register.register.web;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.projects.register.register.application.port.CustomerUseCase;
import pl.projects.register.register.application.port.CustomerUseCase.UpdateCustomerResponse;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/customers")
@RestController
@AllArgsConstructor
public class CustomerController {
    private final CustomerUseCase customerUseCase;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RestCustomer> getAll(
            HttpServletRequest request,
            @RequestParam Optional<String> email) {
        List<RestCustomer> customers;
        if (email.isPresent()) {
            customers = customerUseCase.findByEmail(email.get());
        } else {
            customers = customerUseCase.findAll();
        }
        return customers.stream()
                .map(customer -> toRestCustomer(customer, request))
                .collect(Collectors.toList());
    }

    private RestCustomer toRestCustomer(RestCustomer customer, HttpServletRequest request) {
        return new RestCustomer(
                customer.getFirstname(),
                customer.getFirstname(),
                customer.getEmail(),
                customer.getBirthday()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        RestCustomer foundCustomer = customerUseCase.findById(id);
        return ResponseEntity.ok().body(foundCustomer);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateCustomer(@PathVariable Long id, @RequestBody RestUpdateCustomerCommand command) {
        UpdateCustomerResponse response = customerUseCase.updateCustomer(command.toUpdateCommand(id));
        if (!response.isSuccess()) {
            String message = String.join(", ", response.getErrors());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Long> addCustomer(@Valid @RequestBody RestCustomerCommand command) {
        Long customerId = customerUseCase.addCustomer(command.toCreateCommand());
        return ResponseEntity.created(createdCustomerUri(customerId)).body(customerId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        customerUseCase.removeById(id);
    }

    private URI createdCustomerUri(Long customerId) {
        return new CreatedURI("/" + customerId.toString()).uri();
    }

}
