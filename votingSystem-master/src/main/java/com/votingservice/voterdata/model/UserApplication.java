package com.votingservice.voterdata.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class UserApplication {


    @Id
    private String id;

    @NotNull(message = "User Name is mandatory")
    private String username;
    @NotNull(message = "Password is mandatory")
    private String password;
    @NotNull(message = "Role  is mandatory")
    private String role;

}
