package ru.savosin.databasewebapplication.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.savosin.databasewebapplication.entity.MccCode;
import ru.savosin.databasewebapplication.repository.MccCodeRepository;
import ru.savosin.databasewebapplication.service.interfaces.MccCodeService;

import java.util.List;


@Service
public class MccCodeServiceImpl implements MccCodeService {
    private MccCodeRepository mccCodeRepository;

    @Autowired
    public MccCodeServiceImpl(MccCodeRepository mccCodeRepository) {
        this.mccCodeRepository = mccCodeRepository;
    }

    @Override
    public MccCode save(MccCode mccCode) {
        return mccCodeRepository.save(mccCode);
    }

    @Override
    public List<MccCode> fetchAll() {
        return mccCodeRepository.findAll(Sort.by(Sort.Direction.ASC, "mccCodeId"));
    }
}
