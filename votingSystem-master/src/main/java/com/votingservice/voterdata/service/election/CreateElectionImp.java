package com.votingservice.voterdata.service.election;

import com.votingservice.voterdata.dto.ElectionResultDTO;
import com.votingservice.voterdata.model.Election;
import com.votingservice.voterdata.model.NominatedCanditate;
import com.votingservice.voterdata.repository.ElectionRepository;
import com.votingservice.voterdata.repository.NominatedCanditateCustomRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CreateElectionImp implements CreateElection {

    @Autowired
    private ElectionRepository elr;

    @Autowired
    private NominatedCanditateCustomRepository nocr;

    private static final Logger LOGGER = LogManager.getLogger(CreateElectionImp.class);

    @Override
    public Election createElection(Election election) throws Exception {
        election = elr.save(election);
        return election;

    }

    @Override
    public Election getElection() throws Exception {
        List<Election> elections = elr.findAll();
        for (Election electionActiveStatus :elections) {
            if(electionActiveStatus.getActive()){
                return electionActiveStatus;
            }
        }
        return null;
    }

    @Override
    public Election updateElection(Election election) throws Exception {
        return null;
    }

    @Override
    public void deleteElection(String Id) throws Exception {

    }

    @Override
    public void setActiveStatusFalse(String Id) {
        Optional<Election> election = elr.findById(Id);
        election.ifPresent(electionSetStatus -> electionSetStatus.setActive(false));

    }

    @Override
    public ElectionResultDTO getResult(String electionid) throws Exception {
        NominatedCanditate winnerObj = nocr.getResult();
        List<ElectionResultDTO> totalCountObj = nocr.totalVotes();
        ElectionResultDTO resultObject = getResultObject(totalCountObj);
        resultObject.setWinner(winnerObj);
        persistResult(winnerObj,electionid);
        return resultObject;
    }

    private void persistResult(NominatedCanditate winnerObj, String electionid) {
        Optional<Election> election = elr.findById(electionid);
        election.ifPresent(electionSetStatus -> electionSetStatus.setResult(winnerObj.getStudentId()));


    }

    private ElectionResultDTO getResultObject(List<ElectionResultDTO> results) {
        return results.stream().findAny().get();
    }
}
