package pl.projects.register.register.infrastructure;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import pl.projects.register.register.application.port.CustomerUseCase;

//@RequestMapping("/customer")
@RestController
@AllArgsConstructor
public class CustomerController1 {
    private final CustomerUseCase customer;

//    @GetMapping
//    public List<Customer> getAll(){
//        return customer.findAll();
//    }
//
//    @GetMapping("/{id}")
//    public Customer getById(@PathVariable Long id){
//        return customer.findById(id).orElse(null);
//    }

}
