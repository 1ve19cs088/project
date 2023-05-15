package com.votingservice.voterdata.service.registervoter;

import com.votingservice.voterdata.model.Voter;

import java.util.List;

public interface Register {

    Voter registerVoter(Voter voter) throws Exception;

    List<Voter> getAllVoter() throws Exception;

    Voter updateVoter(Voter voter) throws Exception;

    void deleteVoter(String voterId) throws Exception;

    Integer validateVoter(String token)throws Exception;

    void setVoteStatus(Integer b)throws Exception;
}

