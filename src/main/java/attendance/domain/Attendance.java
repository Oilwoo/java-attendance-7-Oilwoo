package attendance.domain;

import attendance.config.TimeFormatConfig;
import attendance.exception.InvalidFormException;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.Locale;
import net.bytebuddy.asm.Advice.Local;

public class Attendance {
    private LocalDateTime dateTime;

    public Attendance(String dateTime) {
        setDateTime(dateTime);
    }

    public void setDateTime(String input) {
        try {
            this.dateTime = LocalDateTime.parse(input, DateTimeFormatter.ofPattern(TimeFormatConfig.DATE_TIME_FORMAT));
        } catch (DateTimeParseException e) {
            throw new InvalidFormException();
        }
    }

    public boolean matchData(String day) {
        return this.dateTime.getDayOfMonth() == Integer.parseInt(day);
    }

    public String getDateTime() {
        return dateTime.format(DateTimeFormatter.ofPattern(TimeFormatConfig.DATE_TIME_FORMAT));
    }

    public String getPrintForm() {
        String monthDay = dateTime.format(DateTimeFormatter.ofPattern("MM월 dd일 "));
        String dayOfWeek = dateTime.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.KOREAN) + "요일 ";
        String time = dateTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        String status = "";
        LocalDateTime monday = LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(), dateTime.getDayOfMonth(),
                13, 0);
        LocalDateTime other = LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(), dateTime.getDayOfMonth(),
                10, 0);


        if (dateTime.getDayOfWeek() == DayOfWeek.MONDAY) {
            Duration duration = Duration.between(monday, dateTime);
            long diffrence = duration.getSeconds();
            if (diffrence < 300) {
                status = "출석";
            }
            if (diffrence > 300) {
                status = "지각";
            }
            if (diffrence > 3000) {
                status = "결석";
            }
        }
        if (dateTime.getDayOfWeek() != DayOfWeek.MONDAY) {
            Duration duration = Duration.between(other, dateTime);
            long diffrence = duration.getSeconds();
            if (diffrence < 300) {
                status = "출석";
            }
            if (diffrence > 300) {
                status = "지각";
            }
            if (diffrence > 3000) {
                status = "결석";
            }
        }

        return monthDay + dayOfWeek + time + " (" + status + ")";
    }

    public String printStatus() {
        String status = "";
        LocalDateTime monday = LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(), dateTime.getDayOfMonth(),
                13, 0);
        LocalDateTime other = LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(), dateTime.getDayOfMonth(),
                10, 0);


        if (dateTime.getDayOfWeek() == DayOfWeek.MONDAY) {
            Duration duration = Duration.between(monday, dateTime);
            long diffrence = duration.getSeconds();
            if (diffrence < 300) {
                status = "출석";
            }
            if (diffrence > 300) {
                status = "지각";
            }
            if (diffrence > 3000) {
                status = "결석";
            }
        }
        if (dateTime.getDayOfWeek() != DayOfWeek.MONDAY) {
            Duration duration = Duration.between(other, dateTime);
            long diffrence = duration.getSeconds();
            if (diffrence < 300) {
                status = "출석";
            }
            if (diffrence > 300) {
                status = "지각";
            }
            if (diffrence > 3000) {
                status = "결석";
            }
        }

        return status;
    }

}
