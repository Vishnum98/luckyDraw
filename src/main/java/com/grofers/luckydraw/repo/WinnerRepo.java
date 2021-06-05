package com.grofers.luckydraw.repo;

import com.grofers.luckydraw.model.Winner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WinnerRepo extends JpaRepository<Winner,Integer> {
}
