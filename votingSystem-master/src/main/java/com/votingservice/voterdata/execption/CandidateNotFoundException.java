package com.votingservice.voterdata.execption;

public class CandidateNotFoundException extends RuntimeException {

    public CandidateNotFoundException(Integer id) {
        super("Could not find candidate " + id);
    }
}
