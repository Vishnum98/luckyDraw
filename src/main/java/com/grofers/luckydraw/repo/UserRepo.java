package com.grofers.luckydraw.repo;


import com.grofers.luckydraw.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Integer> {
    /**
     * Get's Count of a particular user to a particular contest
     */
    String QUERY = "Select count(*) from users u " +
            "join users_raffle_list ur on u.id = ur.user_id    " +
            "join raffle r on ur.raffle_list_id = r.id    " +
            "join contest c on r.contest_contest_id = c.contest_id   " +
            " where u.id = :id and c.contest_id = :cid";
    @Query(value = QUERY,nativeQuery = true)
    Integer getContest(@Param("id") Integer id,@Param("cid") Integer cid);

    List<User> findByIdAndName(Integer id, String name);

    List<User> findByName(String name);
    
    @Query(value = "select * from users u " +
            "join users_raffle_list ur on u.id = ur.user_id " +
            "where ur.raffle_list_id = :id1",nativeQuery = true)
    User findByRaffleId(@Param("id1") Integer id);
}
