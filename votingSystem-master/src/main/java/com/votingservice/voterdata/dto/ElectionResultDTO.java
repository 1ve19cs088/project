package com.votingservice.voterdata.dto;

import com.votingservice.voterdata.model.NominatedCanditate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ElectionResultDTO {
    private Integer NumberofCandidates;
    private Integer NumberofVotesCasted;
    private NominatedCanditate Winner;

}
