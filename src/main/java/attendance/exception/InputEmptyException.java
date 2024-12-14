package attendance.exception;

public class InputEmptyException extends CustomIllegalArgumentException{
    public InputEmptyException() {
        super("이름이 빈값으로 입력되었습니다.");
    }
}
