package attendance.enums;

public enum WarningRule {
    WARNING("경고", 2),
    INTERVIEW("면담", 3),
    EXPELLED("제적", 5);
    WarningRule(String name, int count) {
    }
}
