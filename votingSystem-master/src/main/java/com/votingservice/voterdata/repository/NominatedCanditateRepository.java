package com.votingservice.voterdata.repository;

import com.votingservice.voterdata.model.NominatedCanditate;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NominatedCanditateRepository extends MongoRepository <NominatedCanditate, String>{

    NominatedCanditate findByStudentId(Integer studentId);

}
