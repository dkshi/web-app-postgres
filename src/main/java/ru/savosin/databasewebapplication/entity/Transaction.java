package ru.savosin.databasewebapplication.entity;


import com.opencsv.bean.CsvBindByName;
import ru.savosin.databasewebapplication.entity.identity.TransactionId;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Entity
@IdClass(TransactionId.class)
public class Transaction {
    @Id
    @Column(name="customer_id")
    @CsvBindByName(column = "customer_id")
    @Positive
    private Integer customerId;

    @Id
    @Column(name="mcc_code")
    @CsvBindByName(column = "mcc_code")
    @Positive
    private Integer mccCodeId;

    @Column(name="tr_datetime", nullable = false)
    @CsvBindByName(column = "tr_datetime")
    private LocalDateTime trDateTime;

    @Column(name="tr_type", nullable = false)
    @CsvBindByName(column = "tr_type")
    @Positive
    private Integer trTypeId;

    @Column(name="amount", nullable = false)
    @CsvBindByName(column = "amount")
    private Integer amount;

    @Column(name="term_id", nullable = false)
    @CsvBindByName(column = "term_id")
    @Positive
    private Integer terminalId;

    public Transaction() {
    }

    @ManyToOne(fetch= FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "customer_id", nullable = false, updatable = false, insertable = false)
    private GenderTrain genderTrain;

    public GenderTrain getGenderTrain() {
        return genderTrain;
    }

    public void setGenderTrain(GenderTrain genderTrain) {
        this.genderTrain = genderTrain;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "mcc_code", nullable = false, updatable = false, insertable = false)
    private MccCode mccCode;

    public MccCode getMccCode() {
        return mccCode;
    }

    public void setMccCode(MccCode mccCode) {
        this.mccCode = mccCode;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "tr_type", nullable = false, updatable = false, insertable = false)
    private TransactionType transactionType;

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Transaction(Integer customerId,
                       Integer mccCodeId,
                       LocalDateTime trDateTime,
                       Integer trTypeId,
                       Integer amount,
                       Integer terminalId) {
        this.customerId = customerId;
        this.mccCodeId = mccCodeId;
        this.trDateTime = trDateTime;
        this.trTypeId = trTypeId;
        this.amount = amount;
        this.terminalId = terminalId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getMccCodeId() {
        return mccCodeId;
    }

    public void setMccCodeId(Integer mccCodeId) {
        this.mccCodeId = mccCodeId;
    }

    public LocalDateTime getTrDateTime() {
        return trDateTime;
    }

    public void setTrDateTime(LocalDateTime trDateTime) {
        this.trDateTime = trDateTime;
    }

    public Integer getTrTypeId() {
        return trTypeId;
    }

    public void setTrTypeId(Integer trTypeId) {
        this.trTypeId = trTypeId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(Integer terminalId) {
        this.terminalId = terminalId;
    }

    @Override
    public String toString() {
        String[] strings = new String[]{customerId.toString(), mccCodeId.toString(),
                trDateTime.toString(), trTypeId.toString(), amount.toString(), terminalId.toString()};
        return String.join(",",strings);
    }
}
