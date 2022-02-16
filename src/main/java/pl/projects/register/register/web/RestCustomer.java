package pl.projects.register.register.web;

import lombok.Getter;
import lombok.Value;

import java.time.LocalDate;

@Value
@Getter
public class RestCustomer {
    String firstname;
    String lastname;
    String email;
    LocalDate birthday;
}
