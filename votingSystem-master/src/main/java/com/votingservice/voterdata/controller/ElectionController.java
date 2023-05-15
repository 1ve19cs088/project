package com.votingservice.voterdata.controller;

import com.votingservice.voterdata.model.Election;
import com.votingservice.voterdata.service.election.CreateElectionImp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;


@RestController
@RequestMapping("/election")
public class ElectionController {

    @Autowired
    private CreateElectionImp CreateElectionImp;

    private static final Logger LOGGER = LogManager.getLogger(ElectionController.class);

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity createElection(@Valid  @RequestBody Election ele) throws Exception {
        HashMap<String, Object> resp = new HashMap<>();
        CreateElectionImp.createElection(ele);
        resp.put("election", ele);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity getElection() throws Exception {
        return new ResponseEntity<>(CreateElectionImp.getElection(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{electionid}", method = RequestMethod.PATCH)
    public ResponseEntity voteForCanditate(@Valid
                                           @PathVariable("electionid") String electionid) throws Exception {
        HashMap<String, Object> resp = new HashMap<>();
        CreateElectionImp.setActiveStatusFalse(electionid);
        return new ResponseEntity<>(resp, HttpStatus.OK);

    }

    @RequestMapping(value = "/result/{electionid}", method = RequestMethod.GET)
    public ResponseEntity getResult(@PathVariable("electionid") String electionid) throws Exception {
        return new ResponseEntity<>(CreateElectionImp.getResult(electionid), HttpStatus.OK);
    }
}
