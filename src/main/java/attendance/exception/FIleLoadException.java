package attendance.exception;

public class FIleLoadException extends CustomIIllegalStateException{
    public FIleLoadException() {
        super("출석기록파일을 정상적으로 불러오지 못했습니다.");
    }
}
