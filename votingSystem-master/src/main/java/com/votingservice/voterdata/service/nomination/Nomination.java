package com.votingservice.voterdata.service.nomination;
import com.votingservice.voterdata.model.NominatedCanditate;

import java.util.List;

public interface Nomination {

    NominatedCanditate registerCanditate(NominatedCanditate canditate) throws Exception;

    List<NominatedCanditate> getAllCanditate() throws Exception;

    NominatedCanditate updateCanditate(NominatedCanditate user) throws Exception;

    void deleteCanditate(String canditateId) throws Exception;

    void voteForCanditate(Integer student_id);
}
