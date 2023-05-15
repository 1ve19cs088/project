package com.votingservice.voterdata.execption;

public class AlreadyVotedExecption extends RuntimeException {
    public AlreadyVotedExecption() {
        super("Voter has voted already");
    }
}
