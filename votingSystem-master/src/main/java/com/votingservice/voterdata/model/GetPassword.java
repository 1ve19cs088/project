package com.votingservice.voterdata.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Transient;

@Getter
@Setter
public abstract class GetPassword {
    @Transient
    private String password;
}
