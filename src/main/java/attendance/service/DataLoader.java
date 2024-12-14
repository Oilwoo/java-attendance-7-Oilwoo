package attendance.service;

import static java.nio.file.Files.lines;

import attendance.config.FileLocationConfig;
import attendance.domain.Attendance;
import attendance.domain.Crew;
import attendance.exception.FIleLoadException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {
    private final String DELIMITER = ",";
    public List<Crew> loadAttendanceData() {
        List<String> lines = readFileLines(FileLocationConfig.ATTENDANCE_FILE_LOCATION);
        List<String> names = new ArrayList<>();
        for(String line : lines) {
            String[] input  = line.split(",");
            if(!names.contains(input[0])){
                names.add(input[0]);
            }
        }
        List<Crew> crews = new ArrayList<>();
        for(String name : names) {
            crews.add(new Crew(name));
        }
        for(String line : lines) {
            setAttendances(crews, line);
        }
        return crews;
    }

    private List<Crew> setAttendances(List<Crew> crews, String line) {
        String[] input = line.split(",");
        for(Crew crew : crews) {
            if(crew.getName().equals(input[0])){
                crew.addAttendance(new Attendance(input[1]));
            }
        }
        return crews;
    }

    private List<String> readFileLines(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            List<String> data = new ArrayList<>();
            List<String> lines = br.lines().toList();
            for(int i = 1; i < lines.size(); i++) {
                data.add(lines.get(i));
            }
            return data;
        } catch (IOException e) {
            throw new FIleLoadException();
        }
    }
}
