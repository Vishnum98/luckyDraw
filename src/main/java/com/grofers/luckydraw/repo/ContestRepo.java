package com.grofers.luckydraw.repo;

import com.grofers.luckydraw.model.Contest;
import com.grofers.luckydraw.model.Winner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ContestRepo extends JpaRepository<Contest,Integer> {


    Contest findFirstByDateAfterOrderByDateAsc(LocalDateTime date);

    List<Contest> findByDateBetween(LocalDateTime minusDays, LocalDateTime date);
}
