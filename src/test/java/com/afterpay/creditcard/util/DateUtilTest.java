package com.afterpay.creditcard.util;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.Optional;


public class DateUtilTest {

    @Test
    public void testFallsWithinSameDayWithCurrentDate() {
        Assert.assertEquals(true,  DateUtil.fallsWithinSameDay.apply(LocalDateTime.now(), LocalDate.now()));
    }

    @Test
    public void testFallsWithinSameDayWithPastDates() {
        LocalDateTime dateTime = LocalDateTime.parse("2019-06-05T13:15:54");
        LocalDate date  = LocalDate.of(2019,06, 05 );
        Assert.assertEquals(true,  DateUtil.fallsWithinSameDay.apply(dateTime, date));
    }

    @Test
    public void testFallsWithinSameDayWithSameDayButDifferentYear() {
        LocalDateTime dateTime = LocalDateTime.parse("2018-06-05T13:15:54");
        LocalDate date  = LocalDate.of(2019,06, 05 );
        Assert.assertEquals(false,  DateUtil.fallsWithinSameDay.apply(dateTime, date));
    }

    @Test
    public void testFormatDateTime() {
        LocalDateTime dateTime = DateUtil.formatDateTime("2019-06-10T13:15:54", Optional.empty());
        System.out.println(dateTime.toString());
        Assert.assertEquals(dateTime.toString(), "2019-06-10T13:15:54");
    }
}
