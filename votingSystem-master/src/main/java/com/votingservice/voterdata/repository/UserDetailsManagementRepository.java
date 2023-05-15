package com.votingservice.voterdata.repository;

import com.votingservice.voterdata.model.UserApplication;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserDetailsManagementRepository extends MongoRepository<UserApplication, String> {

    UserApplication findByUsername(String username);
}
