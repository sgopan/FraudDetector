package com.afterpay.creditcard.transaction;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Converts a stream of credit card transaction to CardTransaction java object
 */
public class CardTransactionReader {

    /**
     * Converts a stream of string transaction in to CardTransaction object
     * @param transactions - Stream of transactions as string
     * @return List of CardTransaction
     */
    public static List<CardTransaction> read(Stream<String> transactions) {
        return transactions.map( transaction -> {
            String[] parts = transaction.split(",");
            if(parts.length != 3 )
                throw new RuntimeException("Invalid card transaction format");

            return new CardTransaction(parts[0], parts[1], parts[2]);
        }).collect(Collectors.toList());
    }
}
