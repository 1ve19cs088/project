package com.votingservice.voterdata.repository;

import com.votingservice.voterdata.dto.ElectionResultDTO;
import com.votingservice.voterdata.model.NominatedCanditate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;


@Repository
public class NominatedCanditateCustomRepositoryImp implements NominatedCanditateCustomRepository {


    private final MongoTemplate mongoTemplate;

    @Autowired
    public NominatedCanditateCustomRepositoryImp(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    private static final Logger LOGGER = LogManager.getLogger(NominatedCanditateCustomRepositoryImp.class);

    @Override
    public NominatedCanditate getResult() {

        Query query = new Query();
        query.limit(1);
        query.with(Sort.by(Sort.Direction.DESC, "count"));
        NominatedCanditate canditate =  mongoTemplate.findOne(query, NominatedCanditate.class);
        LOGGER.info("WInner "+ canditate.getCount());
        return canditate;
    }

    @Override
    public List<ElectionResultDTO> totalVotes() {
        Criteria studentIdCriteria = Criteria.where("studentId").exists(true);
        MatchOperation filterStates = match(studentIdCriteria);
        GroupOperation group = group().count().as("NumberofCandidates").sum("count").as("NumberofVotesCasted");
        Aggregation aggregation = newAggregation(filterStates,group);

        AggregationResults<ElectionResultDTO> result = mongoTemplate
                .aggregate(aggregation, "nominatedCanditate", ElectionResultDTO.class);

        List<ElectionResultDTO> resultCandidate = result.getMappedResults();
        LOGGER.info("Result "+ result.getRawResults().get("results"));
        return resultCandidate;
    }
}
