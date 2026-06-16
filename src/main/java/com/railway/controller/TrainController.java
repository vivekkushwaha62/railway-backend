package com.railway.controller;

import com.railway.entity.Train;
import com.railway.repository.TrainRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trains")
public class TrainController {

    private final TrainRepository trainRepository;

    public TrainController(TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }

    @PostMapping
    public Train addTrain(@RequestBody Train train) {
        return trainRepository.save(train);
    }

    @GetMapping
    public List<Train> getAllTrains() {
        return trainRepository.findAll();
    }
}