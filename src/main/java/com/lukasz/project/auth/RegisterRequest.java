package com.lukasz.project.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;



@Data
@Builder
@AllArgsConstructor
public class RegisterRequest {

    @NotNull
    @NotEmpty(message = "name should not be empty")
    private String firstname;

    @NotNull
    @NotEmpty(message = "lastname should not be empty")
    private String lastname;

    @Email
    @NotNull
    @NotEmpty(message = "email should not be empty")
    private String email;

    @NotNull
    @NotEmpty(message = "password should not be empty")
    private String password;
}
