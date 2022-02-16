package pl.projects.register.register.web;

import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;
import pl.projects.register.register.application.port.CustomerUseCase;
import pl.projects.register.register.web.validation.CustomerIsAdult;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Value
class RestCustomerCommand {

    @NotBlank(message = "Please provide a firstname")
    private String firstname;
    @NotBlank(message = "Please provide a lastname")
    private String lastname;
    @NotBlank(message = "Please provide a email")
    private String email;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "YYYY-MM-DD")
    @CustomerIsAdult
    private LocalDate birthday;
//DataIntegrityViolationException
//        public RestCustomerCommand(String dc, String sd, String sdc, LocalDate now) {
//        }

        /*
        org.springframework.http.converter.HttpMessageNotReadableException: JSON parse error: Cannot deserialize value of type `java.time.LocalDate` from String \"1111-11-35\": Failed to deserialize java.time.LocalDate: (java.time.format.DateTimeParseException) Text '1111-11-35' could not be parsed: Invalid value for DayOfMonth (valid values 1 - 28/31): 35; nested exception is com.fasterxml.jackson.databind.exc.InvalidFormatException: Cannot deserialize value of type `java.time.LocalDate` from String \"1111-11-35\": Failed to deserialize java.time.LocalDate: (java.time.format.DateTimeParseException) Text '1111-11-35' could not be parsed: Invalid value for DayOfMonth (valid values 1 - 28/31): 35\n at [Source: (PushbackInputStream); line: 5, column: 13] (through reference chain: pl.projects.exercises.register.web.CustomerController$RestCustomerCommand[\"birthday\"])\r\n\tat org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter.readJavaType(AbstractJackson2HttpMessageConverter.java:389)\r\n\tat org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter.read(AbstractJackson2HttpMessageConverter.java:342)\r\n\tat org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodArgumentResolver.readWithMessageConverters(AbstractMessageConverterMethodArgumentResolver.java:185)\r\n\tat org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor.readWithMessageConverters(RequestResponseBodyMethodProcessor.java:160)\r\n\tat org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor.resolveArgumen
         */

        /*
            "trace": "org.springframework.web.bind.MethodArgumentNotValidException: Validation failed for argument [0] in public org.springframework.http.ResponseEntity<java.lang.Long> pl.projects.exercises.register.web.CustomerController.addCustomer(pl.projects.exercises.register.web.CustomerController$RestCustomerCommand): [Field error in object 'restCustomerCommand' on field 'birthday': rejected value [null]; codes [NotNull.restCustomerCommand.birthday,NotNull.birthday,NotNull.java.time.LocalDate,NotNull]; arguments [org.springframework.context.suppor
         */

    CustomerUseCase.CreateCustomerCommand toCreateCommand() {
        return new CustomerUseCase.CreateCustomerCommand(firstname, lastname, email, birthday);
    }
}
