package ru.savosin.databasewebapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.savosin.databasewebapplication.entity.GenderTrain;


@Repository
public interface GenderTrainRepository extends JpaRepository<GenderTrain, Long> {

}
