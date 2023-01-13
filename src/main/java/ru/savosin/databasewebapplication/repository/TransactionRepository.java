package ru.savosin.databasewebapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.savosin.databasewebapplication.entity.Transaction;
import java.util.List;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByTrTypeIdIn(List<Integer> trTypeIds);
}
