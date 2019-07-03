package com.afterpay.creditcard.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.function.BiFunction;

public class DateUtil {

    private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;


    /**
     * Checks if the date time fall within the same day
     */
    public static BiFunction<LocalDateTime, LocalDate, Boolean> fallsWithinSameDay =
            (dateTime, dateWindow) ->
                    dateTime.toLocalDate().isEqual(dateWindow);

    /**
     * Converts a dateTime string to LocalDateTime Object
     * @param dateTimeAsString The dateTime as String which needs to be converted to LocalDateTime
     * @param formatter The dateTimeformatter to be used. Use Optional.empty() if default ISO_LOCAL_DATE_TIME formatter needs to be used
     * @return The formatted LocalDateTime object
     */
    public static LocalDateTime formatDateTime(String dateTimeAsString, Optional<DateTimeFormatter> formatter) {
        return LocalDateTime.parse(dateTimeAsString, formatter.orElse(DEFAULT_FORMATTER));
    }
}
