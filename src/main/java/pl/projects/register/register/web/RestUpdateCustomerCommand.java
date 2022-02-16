package pl.projects.register.register.web;

import lombok.Value;
import pl.projects.register.register.application.port.CustomerUseCase.UpdateCustomerCommand;

import javax.validation.constraints.NotBlank;

@Value
class RestUpdateCustomerCommand {

    @NotBlank(message = "Please provide a firstname")
    private String firstname;
    @NotBlank(message = "Please provide a lastname")
    private String lastname;


    UpdateCustomerCommand toUpdateCommand(Long id) {
        return new UpdateCustomerCommand(id, firstname, lastname);
    }
}
