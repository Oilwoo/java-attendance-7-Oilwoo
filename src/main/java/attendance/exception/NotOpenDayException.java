package attendance.exception;

public class NotOpenDayException extends CustomIllegalArgumentException {
    public NotOpenDayException(String date) {
        super(date +"요일은 등교일이 아닙니다.");
    }
}
