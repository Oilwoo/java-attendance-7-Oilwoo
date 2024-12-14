package attendance.view;

import attendance.domain.Attendance;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class OutputView {
    public void printWelcomeMessage() {
        LocalDateTime now = DateTimes.now();
        String monthDay = now.format(DateTimeFormatter.ofPattern("MM월-dd일 "));
        String dayOfWeek = now.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.KOREAN);
        System.out.println("오늘은 " + monthDay + dayOfWeek + "요일입니다. 기능을 선택해 주세요.");
        System.out.println("1. 출석 확인");
        System.out.println("2. 출석 수정");
        System.out.println("3. 크루별 출석 기록 확인");
        System.out.println("4. 제적 위험자 확인");
        System.out.println("Q. 종료");
    }

    public void printInputCrewName() {
        System.out.println("닉네임을 입력해 주세요.");
    }

    public void printInputAttendanceTime() {
        System.out.println("등교 시간을 입력해 주세요.");
    }


    public void printInputModifyDay() {
        System.out.println("수정하려는 날짜(일)를 입력해 주세요.");
    }

    public void printInputModifyTime() {
        System.out.println("언제로 변경하겠습니까?");
    }

    public void printAttendance(Attendance attendance) {
        System.out.print(attendance.getPrintForm());
    }
 }
