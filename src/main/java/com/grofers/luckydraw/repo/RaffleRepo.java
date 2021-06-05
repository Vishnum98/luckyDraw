package com.grofers.luckydraw.repo;

import com.grofers.luckydraw.model.Raffle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RaffleRepo extends JpaRepository<Raffle,Integer> {
    /**
     * Get linked raffle's based on contest id
     * @param cid is contest id
     * @return List of raffle's
     */
    @Query(value = "SELECT * FROM RAFFLE WHERE contest_contest_id = :cid",nativeQuery = true)
    List<Raffle> getByContest(@Param("cid") Integer cid);

    /**
     * Checks if we already have the particular String in DB
     * @param generatedString is the string which gets checked in db
     * @return 1 if we have the string in db
     */
    Long countByRaffle(String generatedString);
}
