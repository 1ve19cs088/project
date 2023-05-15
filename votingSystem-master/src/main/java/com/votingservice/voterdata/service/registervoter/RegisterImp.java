package com.votingservice.voterdata.service.registervoter;

import com.votingservice.voterdata.execption.AlreadyVotedExecption;
import com.votingservice.voterdata.execption.VoterNotFound;
import com.votingservice.voterdata.model.Voter;
import com.votingservice.voterdata.repository.RegisterVoterRepository;
import com.votingservice.voterdata.utilities.AddUserDetails;
import com.votingservice.voterdata.utilities.JwtUtilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterImp implements Register {

    @Autowired
    private RegisterVoterRepository revr;

    @Autowired
    private JwtUtilities jwtTokenUtil;

    @Autowired
    private AddUserDetails addusdel;

    private static final Logger LOGGER = LogManager.getLogger(RegisterImp.class);

    @Override
    public Voter registerVoter(Voter voter) throws Exception {
        voter = revr.save(voter);
        addusdel.AddVoterUserDetails(voter);
        return voter;
    }


    @Override
    public List<Voter> getAllVoter() throws Exception {
        return revr.findAll();
    }

    @Override
    public Voter updateVoter(Voter voter) throws Exception {
        return null;
    }

    @Override
    public void deleteVoter(String voterId) throws Exception {

    }

    @Override
    public Integer validateVoter(String token) throws Exception {
        Integer studentID = jwtTokenUtil.validateToken(token);
        Voter voter = revr.findByStudentId(studentID);
        if (voter.getStudentId() == null) {
            throw new VoterNotFound(studentID);
        }else if(voter.getVoteStatus() == true){
            throw new AlreadyVotedExecption();
        }else{
            return voter.getStudentId();
        }
    }

    @Override
    public void setVoteStatus(Integer studentID) throws VoterNotFound {
        Voter voter = revr.findByStudentId(studentID);
        LOGGER.info("StudentID is "+ voter.getStudentId());
        if (voter == null) {
            throw new VoterNotFound(studentID);
        }else{
            voter.setVoteStatus(true);
            revr.save(voter);
        }

    }
}
