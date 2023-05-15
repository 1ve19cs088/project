package com.votingservice.voterdata.repository;

import com.votingservice.voterdata.dto.ElectionResultDTO;
import com.votingservice.voterdata.model.NominatedCanditate;

import java.util.List;


public interface NominatedCanditateCustomRepository {
    NominatedCanditate getResult();
    List<ElectionResultDTO> totalVotes();
}
