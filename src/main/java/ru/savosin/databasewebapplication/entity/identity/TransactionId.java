package ru.savosin.databasewebapplication.entity.identity;

import javax.persistence.Embeddable;
import java.io.Serializable;

public class TransactionId implements Serializable {
    private Integer customerId;
    private Integer mccCodeId;

    public TransactionId(Integer customerId, Integer mccCodeId) {
        this.customerId = customerId;
        this.mccCodeId = mccCodeId;
    }

    public TransactionId() {

    }
}
