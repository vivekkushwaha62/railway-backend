package com.railway.repository;

import com.railway.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository
        extends JpaRepository<Train, Long> {
}