package ru.savosin.databasewebapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.savosin.databasewebapplication.entity.MccCode;

@Repository
public interface MccCodeRepository extends JpaRepository<MccCode, Long> {
}
