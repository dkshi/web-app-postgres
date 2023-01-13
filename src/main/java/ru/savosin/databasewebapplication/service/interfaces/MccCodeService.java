package ru.savosin.databasewebapplication.service.interfaces;

import ru.savosin.databasewebapplication.entity.MccCode;

import java.util.List;

public interface MccCodeService {
    MccCode save(MccCode mccCode);
    List<MccCode> fetchAll();
}
