package attendance.exception;

public class NotOurCrewException extends CustomIllegalArgumentException{
    public NotOurCrewException() {
        super("등록되지 않은 닉네임입니다.");
    }
}
