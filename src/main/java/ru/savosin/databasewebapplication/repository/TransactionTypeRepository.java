package ru.savosin.databasewebapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.savosin.databasewebapplication.entity.TransactionType;

import java.util.List;

@Repository
public interface TransactionTypeRepository extends JpaRepository<TransactionType, Long> {
    List<TransactionType> findByTrDescriptionContainingIgnoreCase(String trDescription);
}
