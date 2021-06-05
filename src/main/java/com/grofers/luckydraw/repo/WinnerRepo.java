package com.grofers.luckydraw.repo;

import com.grofers.luckydraw.model.Winner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface WinnerRepo extends JpaRepository<Winner,Integer> {

    @Query(value = "Select * from winners w join contest c on w.contest_contest_id = c.contest_id  " +
            "where  c.contest_id =:cid limit 1",nativeQuery = true)
    List<Winner> getByContest(@Param("cid") Integer cid);

    @Query(value = "Select *  from winners w  " +
            "join contest c on w.contest_contest_id = c.contest_id " +
            "  where c.date >= :date -7 and c.date <= :date",nativeQuery = true)
    List<Winner> findLastWeekWinners(LocalDateTime date);

//    @Query("Select * from winners w join contest c on " +
//            "w.contest_id = c.id where c.date<:date")
//    List<Winner> getWinnersForDate(@Param("date") Date date);

}
