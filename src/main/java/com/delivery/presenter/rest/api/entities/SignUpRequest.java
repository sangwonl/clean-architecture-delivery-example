package com.delivery.presenter.rest.api.entities;

import com.delivery.core.usecases.customer.CreateCustomerInput;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Value
public class SignUpRequest {
    @NotBlank
    @Size(min = 4, max = 50)
    private final String name;

    @Email
    @NotBlank
    @Size(max = 100)
    private final String email;

    @NotBlank
    private final String address;

    @NotBlank
    @Size(min = 6, max = 50)
    private final String password;

    public static CreateCustomerInput from(SignUpRequest signUpRequest) {
        return new CreateCustomerInput(
                signUpRequest.getName(),
                signUpRequest.getEmail(),
                signUpRequest.getAddress(),
                signUpRequest.getPassword());
    }
}
