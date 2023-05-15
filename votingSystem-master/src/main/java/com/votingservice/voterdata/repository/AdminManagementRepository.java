package com.votingservice.voterdata.repository;


import com.votingservice.voterdata.model.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminManagementRepository extends MongoRepository<Admin, String> {
}
