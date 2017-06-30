package com.caraquri.hatamoto.bookmanager.util;

import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.format.DateTimeParseException;

import timber.log.Timber;

public class DateUtils {
    public static String getFormatedDate(String date) {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(date, DateTimeFormatter.RFC_1123_DATE_TIME);
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            return dateTime.format(outputFormatter);
        } catch (DateTimeParseException e) {
            Timber.e("can't change format: " + e.getMessage());
            return date;
        }
    }
}
