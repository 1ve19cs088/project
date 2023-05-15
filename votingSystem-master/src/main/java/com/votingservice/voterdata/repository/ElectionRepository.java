package com.votingservice.voterdata.repository;

import com.votingservice.voterdata.model.Election;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ElectionRepository extends MongoRepository<Election, String> {

}
