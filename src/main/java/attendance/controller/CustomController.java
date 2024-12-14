package attendance.controller;

import attendance.domain.Attendance;
import attendance.domain.Crew;
import attendance.exception.CustomIllegalArgumentException;
import attendance.service.CustomService;
import attendance.service.DataLoader;
import attendance.view.InputView;
import attendance.view.OutputView;
import java.util.List;

public class CustomController extends ExceptionLoopController {
    private final InputView input;
    private final OutputView output;
    private final CustomService service;

    private final List<Crew> crews;

    public CustomController(InputView input, OutputView output, CustomService service,
                            List<Crew> crews) {
        this.input = input;
        this.output = output;
        this.service = service;
        this.crews = crews;
    }

    public void run() {
        while (true) {
            int menuNumber = repeatUntilValid(this::welcomeMenu);
            if (menuNumber == 0) {
                break;
            }
            if (menuNumber == 1) {
                makeAttendance();
            }
            if (menuNumber == 2) {
                modifyAttendance();
            }
        }
    }

    private int welcomeMenu() {
        output.printWelcomeMessage();
        return input.getMenuNumber();
    }

    private void makeAttendance() {
        try {
            output.printInputCrewName();
            String name = input.getCrewName();
            output.printInputAttendanceTime();
            String dateTime = input.getTime();
            service.makeAttendance(crews, name, dateTime);
        } catch (CustomIllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void modifyAttendance() {
        try {
            output.printInputCrewName();
            String name = input.getCrewName();
            output.printInputModifyDay();
            String day = input.getDay();
            output.printInputModifyTime();
            String time = input.getTime();
            Attendance attendance = service.modifyAttendance(crews, name, day, time);
            output.printAttendance(attendance);
            String[] splits = attendance.getDateTime().split(" ");
            attendance.setDateTime(splits[0] + " " + time);
            System.out.print(" -> " + time + " (" + attendance.printStatus() + ")");
            System.out.println(" 수정 완료!");
        } catch (CustomIllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
