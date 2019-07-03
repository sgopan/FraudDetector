package com.afterpay.creditcard;


import com.afterpay.creditcard.transaction.CardTransaction;
import com.afterpay.creditcard.util.DateUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FraudDetector {

    /**
     * This method checks a list of credit card transactions which have exceeded a price threshold in a day
     * @param transactions The list of credit card transactions
     * @param priceThreshold The credit card transaction amount
     * @param day - The 24 hour day for which the credit cards should be checked against
     * @return The list of credit card numbers for which anomaly has been detected.
     */
    public static List<String> detectAnomaly(List<CardTransaction> transactions, BigDecimal priceThreshold, Optional<LocalDate> day) {
        // Filter out transactions that do not fall in same day and group by id and sum amount
        Map<String,BigDecimal> transactionsFilteredAndGroup = transactions.stream().filter(transaction -> {
            return DateUtil.fallsWithinSameDay.apply(transaction.getTimestamp(), day.orElse(LocalDate.now()));
        }).collect(Collectors.groupingBy(CardTransaction::getCreditCardNumber,
                Collectors.reducing(BigDecimal.ZERO, CardTransaction::getAmount, BigDecimal::add)));

        // Now filter transactions that is above price threshold
        List<String> fraudulentTransactions = transactionsFilteredAndGroup.entrySet().stream().filter(isDailyLimitExceeded(priceThreshold))
                .map(transaction -> transaction.getKey())
                .collect(Collectors.toList());

        return fraudulentTransactions;
    }

    /* Predicate to check if a BigDecimal value is greater than other */
    private static Predicate<Map.Entry<String, BigDecimal>> isDailyLimitExceeded(BigDecimal dailyThreshold) {
        return p -> p.getValue().compareTo(dailyThreshold) == 1;
    }

}
