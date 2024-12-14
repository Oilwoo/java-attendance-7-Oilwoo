package attendance.domain;

import attendance.exception.InputEmptyException;
import java.util.ArrayList;
import java.util.List;

public class Crew {
    private final String name;
    private int lateCount;
    private int absentCount;

    private List<Attendance> attendances = new ArrayList<>();

    public Crew(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if(name == null || name.isBlank()) {
            throw new InputEmptyException();
        }
    }

    public String getName() {
        return name;
    }

    public void addAttendance(Attendance attendance) {
        this.attendances.add(attendance);
    }

    public List<Attendance> getAttendances() {
        return attendances;
    }
}
