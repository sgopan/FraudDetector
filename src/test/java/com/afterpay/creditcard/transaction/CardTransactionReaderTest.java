package com.afterpay.creditcard.transaction;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;


public class CardTransactionReaderTest {

    @Test
    public void testRead() {
        Stream<String> transactions = Stream.of(
                "10d7ce2f43e35fa57d1bbf8b1e2, 2019-06-10T13:15:54, 10",
                "10d7ce2f43e35fa57d1bbf8b1e3, 2019-06-09T13:15:54, 20.03",
                "10d7ce2f43e35fa57d1bbf8b1e4, 2019-06-08T13:15:54, 25.03",
                "10d7ce2f43e35fa57d1bbf8b1e5, 2018-06-10T13:15:54, 22.04",
                "10d7ce2f43e35fa57d1bbf8b1e6, 2017-06-10T13:15:54, 45.04"
        );

        List<CardTransaction> cardTransactions = CardTransactionReader.read(transactions);

        List<CardTransaction> expectedValue = Arrays.asList(
                new CardTransaction("10d7ce2f43e35fa57d1bbf8b1e2", "2019-06-10T13:15:54", "10"),
                new CardTransaction("10d7ce2f43e35fa57d1bbf8b1e3", "2019-06-09T13:15:54", "20.03"),
                new CardTransaction("10d7ce2f43e35fa57d1bbf8b1e4", "2019-06-08T13:15:54", "25.03"),
                new CardTransaction("10d7ce2f43e35fa57d1bbf8b1e5", "2018-06-10T13:15:54", "22.04"),
                new CardTransaction("10d7ce2f43e35fa57d1bbf8b1e6", "2017-06-10T13:15:54", "45.04")
        );
        assertThat(cardTransactions).containsExactlyInAnyOrder(expectedValue.toArray(new CardTransaction[] {}));
    }

    @Test ( expected = RuntimeException.class)
    public void testReadWithInvalidTransaction() {
        Stream<String> transactions = Stream.of(
                "10d7ce2f43e35fa57d1bbf8b1e2, 2019-06-10T13:15:54, 10",
                "10d7ce2f43e35fa57d1bbf8b1e3, 2019-06-09T13:15:54, 20.03",
                "10d7ce2f43e35fa57d1bbf8b1e4, 2019-06-08T13:15:54, 25.03",
                "10d7ce2f43e35fa57d1bbf8b1e5, 2018-06-10T13:15:54, 22.04",
                "10d7ce2f43e35fa57d1bbf8b1e6, 2017-06-10T13:15:54"
        );

        CardTransactionReader.read(transactions);
    }
}
