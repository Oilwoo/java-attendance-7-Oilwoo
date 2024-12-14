package attendance.controller;

import attendance.exception.CustomIllegalArgumentException;
import java.util.function.Supplier;

public abstract class ExceptionLoopController {
    protected <T> T repeatUntilValid(Supplier<T> function) {
        while(true) {
            try {
                return function.get();
            } catch (CustomIllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
