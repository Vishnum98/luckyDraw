package com.grofers.luckydraw.repo;

import com.grofers.luckydraw.model.Raffle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RaffleRepo extends JpaRepository<Raffle,Integer> {
    @Query(value = "SELECT * FROM RAFFLE WHERE contest_contest_id = :cid",nativeQuery = true)
    List<Raffle> getByContest(@Param("cid") Integer cid);

    Long countByRaffle(String generatedString);
}
