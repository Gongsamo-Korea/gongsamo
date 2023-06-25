package org.project.gongsamo.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Formatable {
    public static final String datetimeFormat = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(datetimeFormat);

    protected LocalDateTime datetime(String datetime) {
        if (datetime == null || datetime.equals("")) return null;
        return LocalDateTime.parse(datetime, dateTimeFormatter);
    }
}
