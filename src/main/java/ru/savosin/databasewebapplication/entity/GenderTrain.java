package ru.savosin.databasewebapplication.entity;

import com.opencsv.bean.CsvBindByName;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.Set;

@Entity
@Table(name="gender_train")
public class GenderTrain {
    public GenderTrain() {

    }

    public GenderTrain(Integer customerId, String gender) {
        this.customerId = customerId;
        this.gender = gender;
    }

    @Id
    @Column(name = "customer_id")
    @CsvBindByName(column = "customer_id")
    @Positive
    private Integer customerId;
    @Column(name="gender", nullable = false)
    @NotEmpty
    @CsvBindByName(column = "gender")
    private String gender;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        String[] strings = new String[]{customerId.toString(), gender};
        return String.join(",",strings);
    }
}
