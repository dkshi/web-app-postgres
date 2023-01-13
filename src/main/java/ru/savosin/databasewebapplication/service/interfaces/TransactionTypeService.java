package ru.savosin.databasewebapplication.service.interfaces;

import ru.savosin.databasewebapplication.entity.TransactionType;

import java.util.List;

public interface TransactionTypeService {
    TransactionType save(TransactionType transactionType);
    List<TransactionType> fetchAll();
}
