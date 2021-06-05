package com.grofers.luckydraw.repo;

import com.grofers.luckydraw.model.Gift;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GiftRepo extends JpaRepository<Gift,Integer> {
}
