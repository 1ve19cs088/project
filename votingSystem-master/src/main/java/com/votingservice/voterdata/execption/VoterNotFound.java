package com.votingservice.voterdata.execption;

public class VoterNotFound  extends Exception {
    public VoterNotFound(Integer studentID) {
        super("Could not find voter " + studentID);
    }
}
