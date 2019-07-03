package com.afterpay.creditcard;

import com.afterpay.creditcard.transaction.CardTransaction;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class FraudDetectorTest {

    private static Optional<LocalDate> TIMEWINDOW = Optional.of(LocalDate.of(2019, 06, 10));

    @Test
    public void testDetectAnomaly() {
        List<CardTransaction> transactions = Arrays.asList(
                new CardTransaction("10d7ce2f43e35fa57d1bbf8b1e2", "2019-06-10T13:15:54", "10"),
                new CardTransaction("10d7ce2f43e35fa57d1bbf8b1e3", "2019-06-10T13:15:54", "20"),
                new CardTransaction("10d7ce2f43e35fa57d1bbf8b1e2", "2019-06-10T13:15:54", "30.33"),
                new CardTransaction("10d7ce2f43e35fa57d1bbf8b1e3", "2019-06-10T13:15:54", "14.20")
        );

        List<String> fraudulentTransactions = FraudDetector.
                detectAnomaly(transactions, BigDecimal.valueOf(30),
                        FraudDetectorTest.TIMEWINDOW);
        assertThat(fraudulentTransactions).containsExactlyInAnyOrder(new String[] {
                "10d7ce2f43e35fa57d1bbf8b1e3","10d7ce2f43e35fa57d1bbf8b1e2"
        });
    }

    @Test
    public void testDetectAnomalyWithHigherThresholdAndOneMatch() {
        List<CardTransaction> transactions = Arrays.asList(
                new CardTransaction("10d7ce2f43e35fa57d1bbf8b1e2", "2019-06-10T13:15:54", "10"),
                new CardTransaction("10d7ce2f43e35fa57d1bbf8b1e3", "2019-06-10T13:15:54", "20"),
                new CardTransaction("10d7ce2f43e35fa57d1bbf8b1e2", "2019-06-10T13:15:54", "30.33"),
                new CardTransaction("10d7ce2f43e35fa57d1bbf8b1e3", "2019-06-10T13:15:54", "14.20")
        );

        List<String> fraudulentTransactions = FraudDetector.
                detectAnomaly(transactions, BigDecimal.valueOf(36),
                        FraudDetectorTest.TIMEWINDOW);
        assertThat(fraudulentTransactions).containsExactlyInAnyOrder(new String[] {
                "10d7ce2f43e35fa57d1bbf8b1e2"
        });

    }

    @Test
    public void testDetectAnomalyWithNoMatches() {
        List<CardTransaction> transactions = Arrays.asList(
                new CardTransaction("10d7ce2f43e35fa57d1bbf8b1e2", "2019-06-10T13:15:54", "10"),
                new CardTransaction("10d7ce2f43e35fa57d1bbf8b1e3", "2019-06-10T13:15:54", "20"),
                new CardTransaction("10d7ce2f43e35fa57d1bbf8b1e2", "2019-06-10T13:15:54", "30.33"),
                new CardTransaction("10d7ce2f43e35fa57d1bbf8b1e3", "2019-06-10T13:15:54", "14.20")
        );

        List<String> fraudulentTransactions = FraudDetector.
                detectAnomaly(transactions, BigDecimal.valueOf(45),
                        FraudDetectorTest.TIMEWINDOW);
        assertThat(fraudulentTransactions).containsExactlyInAnyOrder(new String[] {
        });
    }

    @Test
    public void testDetectAnomalyWithOneMatchDifferentDayTransaction() {
        List<CardTransaction> transactions = Arrays.asList(
                new CardTransaction("10d7ce2f43e35fa57d1bbf8b1e2", "2019-06-10T13:15:54", "10"),
                new CardTransaction("10d7ce2f43e35fa57d1bbf8b1e3", "2019-06-10T13:15:54", "20"),
                new CardTransaction("10d7ce2f43e35fa57d1bbf8b1e2", "2019-06-09T13:15:54", "30.33"),
                new CardTransaction("10d7ce2f43e35fa57d1bbf8b1e3", "2019-06-10T13:15:54", "14.20")
        );

        List<String> fraudulentTransactions = FraudDetector.
                detectAnomaly(transactions, BigDecimal.valueOf(10),
                        FraudDetectorTest.TIMEWINDOW);
        assertThat(fraudulentTransactions).containsExactlyInAnyOrder(new String[] {
                "10d7ce2f43e35fa57d1bbf8b1e3"
        });
    }

    @Test
    public void testDetectAnomalyWithOneMatchDifferentTimeWindow() {
        List<CardTransaction> transactions = Arrays.asList(
                new CardTransaction("10d7ce2f43e35fa57d1bbf8b1e2", "2019-06-10T13:15:54", "10"),
                new CardTransaction("10d7ce2f43e35fa57d1bbf8b1e3", "2019-06-10T13:15:54", "20"),
                new CardTransaction("10d7ce2f43e35fa57d1bbf8b1e2", "2019-06-10T13:15:54", "30.33"),
                new CardTransaction("10d7ce2f43e35fa57d1bbf8b1e3", "2019-06-10T13:15:54", "14.20")
        );

        List<String> fraudulentTransactions = FraudDetector.
                detectAnomaly(transactions, BigDecimal.valueOf(10),
                        Optional.of(LocalDate.of(2019, 06, 9)));
        assertThat(fraudulentTransactions).containsExactlyInAnyOrder(new String[] {
        });
    }
}
