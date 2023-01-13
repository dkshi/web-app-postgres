package ru.savosin.databasewebapplication.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.savosin.databasewebapplication.entity.TransactionType;
import ru.savosin.databasewebapplication.repository.TransactionTypeRepository;
import ru.savosin.databasewebapplication.service.interfaces.TransactionTypeService;

import java.util.List;


@Service
public class TransactionTypeServiceImpl implements TransactionTypeService {
    private TransactionTypeRepository transactionTypeRepository;

    @Autowired
    public TransactionTypeServiceImpl(TransactionTypeRepository transactionTypeRepository) {
        this.transactionTypeRepository = transactionTypeRepository;
    }

    @Override
    public TransactionType save(TransactionType transactionType) {
        return transactionTypeRepository.save(transactionType);
    }

    @Override
    public List<TransactionType> fetchAll() {
        return transactionTypeRepository.findAll(Sort.by(Sort.Direction.ASC, "trTypeId"));
    }

}
