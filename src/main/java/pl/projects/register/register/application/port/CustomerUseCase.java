package pl.projects.register.register.application.port;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import pl.projects.register.register.domain.Customer;
import pl.projects.register.register.web.RestCustomer;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

public interface CustomerUseCase {
    List<RestCustomer> findAll();

    RestCustomer findById(Long id);

    Long addCustomer(CreateCustomerCommand command);

    Optional<Customer> findOneByEmail(String email);

    void removeById(Long id);

    List<RestCustomer> findByEmail(String email);

    UpdateCustomerResponse updateCustomer(UpdateCustomerCommand toUpdateCommand);

    @Value
    class CreateCustomerCommand {
        String firstname;
        String lastname;
        String email;
        @JsonSerialize(using = LocalDateSerializer.class)
        @JsonDeserialize(using = LocalDateDeserializer.class)
        LocalDate birthday;
    }

    @Value
    @Builder
    @AllArgsConstructor
    class UpdateCustomerCommand {
        Long id;
        String firstname;
        String lastname;
//        LocalDate birthday;

    }

    @Value
    class UpdateCustomerResponse {
        public static UpdateCustomerResponse SUCCESS = new UpdateCustomerResponse(true, emptyList());

        boolean success;
        List<String> errors;
    }

}
