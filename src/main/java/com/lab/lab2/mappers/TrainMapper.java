package com.lab.lab2.mappers;

import com.lab.lab2.domain.data.TrainData;
import com.lab.lab2.domain.entities.Train;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class TrainMapper implements Function<TrainData, Train> {
    @Override
    public Train apply(TrainData trainData) {
        return new Train(
                trainData.getName(),
                trainData.getSize(),
                trainData.getColor(),
                trainData.getType(),
                trainData.getCrews(),
                trainData.getTimetables());
    }
}
