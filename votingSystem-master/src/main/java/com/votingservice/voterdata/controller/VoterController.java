package com.votingservice.voterdata.controller;

import com.votingservice.voterdata.model.Voter;
import com.votingservice.voterdata.service.registervoter.RegisterImp;
import com.votingservice.voterdata.utilities.JwtUtilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("/registervoter")
public class VoterController {

    @Autowired
    private RegisterImp RegisterImp;

    @Autowired
    private JwtUtilities jwtTokenUtil;


    private static final Logger LOGGER = LogManager.getLogger(NominationController.class);

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity registerVoter(@Valid @RequestBody Voter voter) throws Exception {
        HashMap<String, Object> resp = new HashMap<>();
        RegisterImp.registerVoter(voter);
        resp.put("voter", voter);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity getVoterList() throws Exception {
        return new ResponseEntity<>(RegisterImp.getAllVoter(), HttpStatus.OK);
    }

    @RequestMapping(value = "/getelectiontoken/{student_id}", method = RequestMethod.GET)
    public ResponseEntity getElectionToken(@PathVariable("student_id") Integer student_id) throws Exception {
        HashMap<String, Object> resp = new HashMap<>();
        String tokenForVoting = jwtTokenUtil.generateTokenForVote(student_id);
        resp.put("votertoken", tokenForVoting);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
}
