package com.afterpay.creditcard.transaction;

import com.afterpay.creditcard.util.DateUtil;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

/**
 * Represents a credit card transaction
 */
public class CardTransaction {

    /* Hashed credit card number */
    private final String creditCardNumber;

    /* Credit card transaction amount*/
    private final BigDecimal amount;

    /* The date time at which the transaction occurred */
    private final LocalDateTime  timestamp;

    public CardTransaction(String creditCardNumber, String transactionDateTime, String amount) {
        this.creditCardNumber = creditCardNumber.trim();
        this.amount = new BigDecimal(amount.trim());
        this.timestamp = DateUtil.formatDateTime(transactionDateTime.trim(), Optional.empty());
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "CardTransaction{" +
                "creditCardNumber='" + creditCardNumber + '\'' +
                ", timestamp=" + timestamp +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CardTransaction)) return false;
        CardTransaction that = (CardTransaction) o;
        return Objects.equals(creditCardNumber, that.creditCardNumber) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creditCardNumber, amount, timestamp);
    }
}
