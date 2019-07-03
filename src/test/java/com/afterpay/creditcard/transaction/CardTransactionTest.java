package com.afterpay.creditcard.transaction;

import java.math.BigDecimal;
import java.time.format.DateTimeParseException;

import  org.junit.Assert;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

public class CardTransactionTest {

    @Test
    public void testCardTransactionCreation() {
        CardTransaction transaction = new CardTransaction("10d7ce2f43e35fa57d1bbf8b1e2", "2019-06-10T13:15:54", "10");
        Assert.assertEquals("10d7ce2f43e35fa57d1bbf8b1e2", transaction.getCreditCardNumber());
        Assert.assertEquals("10d7ce2f43e35fa57d1bbf8b1e2", transaction.getCreditCardNumber());
        Assert.assertEquals(BigDecimal.valueOf(10), transaction.getAmount());
    }

    @Test
    public void testCardTransactionFailureWithInvalidDate() {
        try {
            new CardTransaction("10d7ce2f43e35fa57d1bbf8b1e2", "2019-06-10T13:15:54.1234", "10");
        }
        catch(DateTimeParseException dtpe) {
            assertThat(dtpe).hasRootCauseInstanceOf(DateTimeParseException.class);
        }
    }

}
