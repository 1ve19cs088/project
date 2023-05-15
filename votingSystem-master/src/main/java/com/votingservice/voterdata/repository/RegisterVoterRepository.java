package com.votingservice.voterdata.repository;

import com.votingservice.voterdata.model.Voter;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RegisterVoterRepository extends MongoRepository<Voter, String> {

    Voter findByStudentId(Integer studentId);
}
