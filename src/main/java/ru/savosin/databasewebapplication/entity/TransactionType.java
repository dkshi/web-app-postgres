package ru.savosin.databasewebapplication.entity;

import com.opencsv.bean.CsvBindByName;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.Set;

@Entity
@Table(name = "tr_types")
public class TransactionType {
    @Id
    @Column(name="tr_type")
    @CsvBindByName(column = "tr_type")
    @Positive
    private Integer trTypeId;

    @Column(name="tr_description", nullable = false)
    @NotEmpty
    @CsvBindByName(column = "tr_description")
    private String trDescription;

    public TransactionType(Integer trTypeId, String trDescription) {
        this.trTypeId = trTypeId;
        this.trDescription = trDescription;
    }

    public TransactionType() {}

    public Integer getTrTypeId() {
        return trTypeId;
    }

    public void setTrTypeId(Integer trTypeId) {
        this.trTypeId = trTypeId;
    }

    public String getTrDescription() {
        return trDescription;
    }

    public void setTrDescription(String trDescription) {
        this.trDescription = trDescription;
    }

    @Override
    public String toString() {
        String[] strings = new String[]{trTypeId.toString(), trDescription};
        return String.join(",",strings);
    }

}
