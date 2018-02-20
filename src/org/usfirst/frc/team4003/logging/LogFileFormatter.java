package org.usfirst.frc.team4003.logging;

import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * Created by Kyle Flynn on 12/3/2017.
 */
public class LogFileFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {
        Date date = new Date();
        String timeStamp = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(date);
        return String.format("[%s][%s]%s \n", timeStamp, record.getLevel().getName(), record.getMessage());
    }
}