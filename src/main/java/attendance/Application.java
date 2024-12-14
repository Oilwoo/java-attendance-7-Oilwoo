package attendance;

import attendance.controller.CustomController;
import attendance.domain.Crew;
import attendance.service.CustomService;
import attendance.service.DataLoader;
import attendance.view.InputView;
import attendance.view.OutputView;
import java.util.List;
import javax.xml.crypto.Data;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        CustomService customService = new CustomService();
        DataLoader dataLoader = new DataLoader();

        List<Crew> crews = dataLoader.loadAttendanceData();

        CustomController controller = new CustomController(inputView, outputView, customService, crews);
        controller.run();
    }
}
