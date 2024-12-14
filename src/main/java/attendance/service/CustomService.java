package attendance.service;

import attendance.config.TimeFormatConfig;
import attendance.domain.Attendance;
import attendance.domain.Crew;
import attendance.exception.InvalidFormException;
import attendance.exception.NotOpenDayException;
import attendance.exception.NotOpenTimeException;
import attendance.exception.NotOurCrewException;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

public class CustomService {
    public void makeAttendance(List<Crew> crews, String name, String time) {
        Crew crew = getCrew(crews, name);
        if (crew == null) {
            throw new NotOurCrewException();
        }
        validateCenterIsNotOpenDay();
        validateCenterIsNotOpenTime(time);
        LocalDateTime now = DateTimes.now();
        String monthDay = now.format(DateTimeFormatter.ofPattern(TimeFormatConfig.DATE_FORMAT));
        crew.addAttendance(new Attendance(monthDay + " " + time));
    }

    public Attendance modifyAttendance(List<Crew> crews, String name, String day, String time) {
        Crew crew = getCrew(crews, name);
        if (crew == null) {
            throw new NotOurCrewException();
        }
        validateCanModifyAttendance(time);
        List<Attendance> attendances = crew.getAttendances();

        Attendance attendance  = attendances.stream().filter(a -> a.matchData(day)).findFirst().orElse(null);
        if(attendance == null) {
            throw new InvalidFormException();
        }
        return attendance;
    }

    private void validateCanModifyAttendance(String input) {
        LocalDateTime now = DateTimes.now();
        String[] inputs = input.split(":");
        LocalDateTime attendanceTime = LocalDateTime.of(now.getYear(), now.getMonth(), now.getMinute(),
                Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));

        if (now.isAfter(attendanceTime)) {
            throw new InvalidFormException();
        }
    }

    private void validateCenterIsNotOpenDay() {
        LocalDateTime now = DateTimes.now();
        if (now.getDayOfWeek() == DayOfWeek.SATURDAY || now.getDayOfWeek() == DayOfWeek.SUNDAY) {
            String monthDay = now.format(DateTimeFormatter.ofPattern("MM월 dd일 "));
            String dayOfWeek = now.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.KOREAN);
            throw new NotOpenDayException(monthDay + dayOfWeek);
        }
    }

    private void validateCenterIsNotOpenTime(String input) {
        String[] inputs = input.split(":");
        if (Integer.parseInt(inputs[0]) < 8 || Integer.parseInt(inputs[0]) == 23) {
            throw new NotOpenTimeException();
        }
    }

    private Crew getCrew(List<Crew> crews, String name) {
        Crew crew = crews.stream().filter(c -> c.getName().equals(name)).findFirst().orElse(null);
        return crew;
    }


}
