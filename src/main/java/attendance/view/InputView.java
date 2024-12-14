package attendance.view;

import attendance.domain.Crew;
import attendance.exception.InvalidFormException;
import camp.nextstep.edu.missionutils.Console;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class InputView {
    public int getMenuNumber() {
        String input = Console.readLine();
        return validateAndMenuInput(input);
    }

    public String getCrewName() {
        return Console.readLine();
    }

    public String getTime() {
        String time = Console.readLine();
        validate(time);
        return time;
    }

    public void validate(String time) {
        try {
            String[] splits = time.split(":");
            LocalTime timdde = LocalTime.of(Integer.parseInt(splits[0]), Integer.parseInt(splits[1]));
        } catch (DateTimeParseException e) {
            throw new InvalidFormException();
        }
    }


    public String getDay() {
        return Console.readLine();
    }


    private int validateAndMenuInput(String input) {
        int menuNumber;
        if (input == null || input.isBlank()) {
            throw new InvalidFormException();
        }
        if (input.equalsIgnoreCase("Q")) {
            return 0;
        }
        try {
            menuNumber = Integer.parseInt(input);
            if (menuNumber < 1 || menuNumber > 4){
                throw new InvalidFormException();
            }
        } catch (NumberFormatException e) {
            throw new InvalidFormException();
        }
            return menuNumber;
    }



}
