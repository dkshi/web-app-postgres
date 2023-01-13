package ru.savosin.databasewebapplication.service.interfaces;


import ru.savosin.databasewebapplication.entity.Transaction;

import java.util.List;

public interface TransactionService {

    Transaction save(Transaction transaction);

    List<Transaction> fetchAll();

    List<Transaction> findByTrDescription(String trDescription);

}
