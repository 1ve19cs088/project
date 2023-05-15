package com.votingservice.voterdata.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class Election implements Serializable {
    @Id
    private String id;
    @NotNull(message = "Start date is mandatory")
    private Date startdate;
    @NotNull(message = "End date is mandatory")
    private Date enddate;
    private Integer result = 0;
    private Boolean active = true;

}
