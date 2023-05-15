package com.votingservice.voterdata.execption;

public class TokenExpiredExecption extends RuntimeException {
    public TokenExpiredExecption() {
        super("Token expired");
    }
}
