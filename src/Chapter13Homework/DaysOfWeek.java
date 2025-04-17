package Chapter13Homework;

import java.time.DayOfWeek;
import java.time.LocalDate;

public enum DaysOfWeek {
    MONDAY("Lu", false),
    TUESDAY("Ma", false),
    WEDNESDAY("Mi", false),
    THURSDAY("Jo", false),
    FRIDAY("Vi", false),
    SATURDAY("Sa", true),
    SUNDAY("Du", true);

    private final String weekName;
    private final boolean isWeekend;

    DaysOfWeek(String weekName, boolean isWeekend) {
        this.weekName = weekName;
        this.isWeekend = isWeekend;
    }

    public static void main(String[] args) {
        DayOfWeek ziuaCurenta = LocalDate.now().getDayOfWeek();
        DaysOfWeek day = convertDay(ziuaCurenta);

        System.out.println("Ziua curentă este: " + day + " (" + day.getWeekName() + ")");

        if (!day.isWeekend()) {
            System.out.println("Este zi lucrătoare. Programul de lucru este 09:00 - 17:00.");
        } else {
            System.out.println("Este weekend!");
        }
    }

    public String getWeekName() {
        return weekName;
    }

    public boolean isWeekend() {
        return isWeekend;
    }

    public static DaysOfWeek convertDay(DayOfWeek day) {
        switch (day) {
            case MONDAY:
                return DaysOfWeek.MONDAY;
            case TUESDAY:
                return DaysOfWeek.TUESDAY;
            case WEDNESDAY:
                return DaysOfWeek.WEDNESDAY;
            case THURSDAY:
                return DaysOfWeek.THURSDAY;
            case FRIDAY:
                return DaysOfWeek.FRIDAY;
            case SATURDAY:
                return DaysOfWeek.SATURDAY;
            case SUNDAY:
                return DaysOfWeek.SUNDAY;
            default:
                return null;
        }
    }


}