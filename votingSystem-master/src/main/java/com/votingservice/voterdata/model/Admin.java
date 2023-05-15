package com.votingservice.voterdata.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
public class Admin extends GetPassword implements  Serializable {
    @Id
    private String id;

    @NotNull(message = "First Name is mandatory")
    private String firstName;
    @NotNull(message = "Last Name is mandatory")
    private String lastName;
    @NotNull(message = "Employee ID is mandatory")
    @Indexed(unique=true)
    private Integer employeeId;
}
