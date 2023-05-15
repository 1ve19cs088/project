package com.votingservice.voterdata.service.election;

import com.votingservice.voterdata.dto.ElectionResultDTO;
import com.votingservice.voterdata.model.Election;

public interface CreateElection {

    Election createElection(Election election) throws Exception;

    Election getElection() throws Exception;

    Election updateElection(Election election) throws Exception;

    void deleteElection(String Id) throws Exception;

    void setActiveStatusFalse(String Id);

    ElectionResultDTO getResult(String electionid) throws Exception;
}
