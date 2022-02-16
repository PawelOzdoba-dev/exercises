package pl.projects.register.register.db;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.projects.register.register.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerJpaRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmailIgnoreCase(String email);

    List<Customer> findByEmailStartsWithIgnoreCase(String email);
}
