package ru.savosin.databasewebapplication.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.savosin.databasewebapplication.entity.Transaction;
import ru.savosin.databasewebapplication.entity.TransactionType;
import ru.savosin.databasewebapplication.repository.TransactionRepository;
import ru.savosin.databasewebapplication.repository.TransactionTypeRepository;
import ru.savosin.databasewebapplication.service.interfaces.TransactionService;

import java.util.ArrayList;
import java.util.List;


@Service
public class TransactionServiceImpl implements TransactionService {
    private TransactionRepository transactionRepository;

    private TransactionTypeRepository transactionTypeRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionTypeRepository transactionTypeRepository) {
        this.transactionRepository = transactionRepository;
        this.transactionTypeRepository = transactionTypeRepository;
    }

    @Override
    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> fetchAll() {
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> findByTrDescription(String trDescription) {
        List<TransactionType> transactionTypes = transactionTypeRepository.findByTrDescriptionContainingIgnoreCase(trDescription);
        List<Integer> trTypeIds = new ArrayList<>();
        for(var transactionType : transactionTypes){
            trTypeIds.add(transactionType.getTrTypeId());
        }
        return transactionRepository.findByTrTypeIdIn(trTypeIds);
    }


}
