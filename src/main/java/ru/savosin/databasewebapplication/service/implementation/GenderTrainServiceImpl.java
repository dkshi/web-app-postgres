package ru.savosin.databasewebapplication.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.savosin.databasewebapplication.entity.GenderTrain;
import ru.savosin.databasewebapplication.repository.GenderTrainRepository;
import ru.savosin.databasewebapplication.service.interfaces.GenderTrainService;

import java.util.List;

@Service
public class GenderTrainServiceImpl implements GenderTrainService {
    private GenderTrainRepository genderTrainRepository;

    @Autowired
    public GenderTrainServiceImpl(GenderTrainRepository genderTrainRepository) {
        this.genderTrainRepository = genderTrainRepository;
    }

    @Override
    public GenderTrain save(GenderTrain genderTrain){
        return genderTrainRepository.save(genderTrain);
    }

    @Override
    public List<GenderTrain> fetchAll(){
        return genderTrainRepository.findAll(Sort.by(Sort.Direction.ASC, "customerId"));
    }
}
