package attendance.exception;

public class NotOpenTimeException extends CustomIllegalArgumentException {
    public NotOpenTimeException( ) {
        super("캠퍼스 운영 시간에만 출석이 가능합니다.");
    }
}
