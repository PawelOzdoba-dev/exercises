package pl.projects.register.register.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.projects.register.register.application.port.CustomerUseCase;
import pl.projects.register.register.db.CustomerJpaRepository;
import pl.projects.register.register.domain.Customer;
import pl.projects.register.register.web.RestCustomer;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
class CustomerService implements CustomerUseCase {

    private final CustomerJpaRepository repository;

    @Override
    public List<RestCustomer> findAll() {
        return repository.findAll().stream()
                .map(customer -> convert(customer))
                .collect(Collectors.toList());
    }

    @Override
    public RestCustomer findById(Long id) {
        return repository.findById(id)
                .map(customer -> convert(customer) )
                .orElseThrow(() -> new EntityNotFoundException("Customer with id: " + id + " doesn't exist"));
    }

    private RestCustomer convert(Customer customer) {
        return new RestCustomer(
                customer.getFirstname(),
                customer.getLastname(),
                customer.getEmail(),
                customer.getBirthday()
        );
    }

    @Override
    @Transactional
    public Long addCustomer(CreateCustomerCommand command) {
        Customer customer = toCustomer(command);
        return repository.save(customer).getId();
    }

    @Override
    public List<RestCustomer> findByEmail(String email) {
        return repository.findByEmailStartsWithIgnoreCase(email)
                .stream()
                .map(customer -> convert(customer))
                .collect(Collectors.toList());

    }

    @Override
    public Optional<Customer> findOneByEmail(String email) {
        return repository.findAll()
                .stream()
                .filter(customer -> customer.getEmail().startsWith(email))
                .findFirst();
    }

    private Customer toCustomer(CreateCustomerCommand command) {
        Customer customer = new Customer(command.getFirstname(), command.getLastname(), command.getEmail(), command.getBirthday());
        return customer;
    }

    @Override
    @Transactional
    public UpdateCustomerResponse updateCustomer(UpdateCustomerCommand command) {
        return repository
                .findById(command.getId())
                .map(customer -> {
                    updateFields(command, customer);
                    return UpdateCustomerResponse.SUCCESS;
                })
                .orElseGet(() -> new UpdateCustomerResponse(false, Collections.singletonList("Customer not found with id: " + command.getId())));
    }

    private Customer updateFields(UpdateCustomerCommand command, Customer customer) {
        if (customer.getFirstname() != null) {
            customer.setFirstname(command.getFirstname());
        }
        if (customer.getLastname() != null) {
            customer.setLastname(command.getLastname());
        }
        return customer;

    }

    @Override
    public void removeById(Long id) {
        repository.deleteById(id);
    }
}
