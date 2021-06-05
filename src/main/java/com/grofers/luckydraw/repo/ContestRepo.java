package com.grofers.luckydraw.repo;

import com.grofers.luckydraw.model.Contest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ContestRepo extends JpaRepository<Contest,Integer> {

    /**
     * Gets the first Contest after the input date when results are sorted in Asc order
     * based ond date
     * @param date get contest which has date greater than this param
     * @return First such contest which satisfies the condition
     */
    Contest findFirstByDateAfterOrderByDateAsc(LocalDateTime date);

    /**
     * Get list of contests between the given dates
     * @param startDate
     * @param endDate
     * @return list of contests fulfilling the condition
     */
    List<Contest> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
