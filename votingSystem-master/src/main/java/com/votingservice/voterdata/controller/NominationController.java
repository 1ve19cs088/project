package com.votingservice.voterdata.controller;

import com.votingservice.voterdata.model.NominatedCanditate;
import com.votingservice.voterdata.service.nomination.NominationImp;
import com.votingservice.voterdata.service.registervoter.RegisterImp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/nomination")


public class NominationController {
    @Autowired
    private NominationImp NominationImpl;

        @Autowired
        private RegisterImp RegisterImp;
    private static final Logger LOGGER = LogManager.getLogger(NominationController.class);

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity createCanditate(@Valid @RequestBody NominatedCanditate nocm) throws Exception {
        HashMap<String, Object> resp = new HashMap<>();
        LOGGER.info("Inside post call");
        NominationImpl.registerCanditate(nocm);
        resp.put("candidate", nocm);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity getCanditate() throws Exception {
        return new ResponseEntity<>(NominationImpl.getAllCanditate(), HttpStatus.OK);

    }

    @RequestMapping(value = "/canditate/{student_id}", method = RequestMethod.PATCH)
    public ResponseEntity voteForCanditate(@Valid @RequestBody Map<String, Object> canditateData,
                                           @PathVariable("student_id") String student_id) throws Exception {
        HashMap<String, Object> resp = new HashMap<>();
        Integer votestudent_Id = RegisterImp.validateVoter(String.valueOf(canditateData.get("token")));
        NominationImpl.voteForCanditate(Integer.valueOf(student_id));
        RegisterImp.setVoteStatus(votestudent_Id);
        resp.put("You have voted for canditate ID", student_id);
        return new ResponseEntity<>(resp, HttpStatus.OK);

    }

}
