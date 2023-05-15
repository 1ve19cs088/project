package com.votingservice.voterdata.service.nomination;

import com.votingservice.voterdata.execption.CandidateNotFoundException;
import com.votingservice.voterdata.model.NominatedCanditate;
import com.votingservice.voterdata.repository.NominatedCanditateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NominationImp implements Nomination {

    @Autowired
    private NominatedCanditateRepository nocr;

    @Override
    public NominatedCanditate registerCanditate(NominatedCanditate nocm) throws Exception {
        nocm = nocr.save(nocm);
        return nocm;
    }

    @Override
    public List<NominatedCanditate> getAllCanditate() throws Exception {
        return nocr.findAll();
    }

    @Override
    public NominatedCanditate updateCanditate(NominatedCanditate user) throws Exception {
        return null;
    }

    @Override
    public void deleteCanditate(String canditateId) throws Exception {

    }

    @Override
    public void voteForCanditate(Integer student_id) {

        NominatedCanditate canditate = nocr.findByStudentId(student_id);
        if(canditate.getStudentId() == null){
            throw new CandidateNotFoundException(student_id);
        }
        else if (canditate.getCount() == 0) {
            canditate.setCount(1);
            nocr.save(canditate);
        } else {
            canditate.setCount(canditate.getCount() + 1);
            nocr.save(canditate);
        }


    }


}
