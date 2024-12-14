package attendance.exception;

public class InvalidFormException extends CustomIllegalArgumentException{
    public InvalidFormException() {
        super("잘못된 형식을 입력하였습니다.");
    }
}
