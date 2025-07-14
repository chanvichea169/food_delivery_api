package com.vicheaCoder.food_delivery_api.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Slf4j
public class DateTimeUtils {

    private DateTimeUtils() {
        // Private constructor to prevent instantiation
    }
    public static Date convertStringToDate(String dateString) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            log.error("Error parsing date: {}", dateString, e);
            throw new IllegalArgumentException("Invalid date format, expected yyyy-MM-dd");
        }
    }
}
