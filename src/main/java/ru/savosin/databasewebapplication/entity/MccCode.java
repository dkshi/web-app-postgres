package ru.savosin.databasewebapplication.entity;


import com.opencsv.bean.CsvBindByName;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.Set;

@Entity
@Table(name="tr_mcc_codes")
public class MccCode {
    @Id
    @Column(name = "mcc_code")
    @CsvBindByName(column = "mcc_code")
    @Positive
    private Integer mccCodeId;

    @Column(name="mcc_description", nullable = false)
    @NotEmpty
    @CsvBindByName(column = "mcc_description")
    private String mccDescription;

    public MccCode(Integer mccCodeId, String mccDescription) {
        this.mccCodeId = mccCodeId;
        this.mccDescription = mccDescription;
    }

    public MccCode(){}

    public Integer getMccCodeId() {
        return mccCodeId;
    }

    public void setMccCodeId(Integer mccCodeId) {
        this.mccCodeId = mccCodeId;
    }

    public String getMccDescription() {
        return mccDescription;
    }

    public void setMccDescription(String mccDescription) {
        this.mccDescription = mccDescription;
    }

    @Override
    public String toString() {
        String[] strings = new String[]{mccCodeId.toString(), mccDescription};
        return String.join(",",strings);
    }
}
