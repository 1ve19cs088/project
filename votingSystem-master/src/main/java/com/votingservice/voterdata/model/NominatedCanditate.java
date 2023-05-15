package com.votingservice.voterdata.model;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Setter
@Getter
public class NominatedCanditate implements Serializable {

    @Id
    private String id;

    @NotNull(message = "First Name is mandatory")
    private String firstName;
    @NotNull(message = "Last Name is mandatory")
    private String lastName;
    @NotNull(message = "Student ID is mandatory")
    @Indexed(unique=true)
    private Integer studentId;
    @NotNull(message = "Student ID is mandatory")
    private String department;
    private Integer count = 0;

    @Transient
    private String token;


}

