package utils.helper;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;

public class DateConverter {

    public static LocalDate fromUtilDate(java.util.Date source) {
        return source.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static java.util.Date fromLocalDate(LocalDate source) {
        return java.util.Date.from(source.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
