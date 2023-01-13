package ru.savosin.databasewebapplication.service.interfaces;

import ru.savosin.databasewebapplication.entity.GenderTrain;

import java.util.List;

public interface GenderTrainService {
    GenderTrain save(GenderTrain genderTrain);
    List<GenderTrain> fetchAll();
}
