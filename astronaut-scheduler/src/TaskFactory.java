import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskFactory {
    private static final AtomicInteger IDGEN = new AtomicInteger(1);
    private static final DateTimeFormatter F = DateTimeFormatter.ofPattern("HH:mm");

    public static Task create(String description, String startStr, String endStr, String priorityStr) {
        try {
            LocalTime start = LocalTime.parse(startStr, F);
            LocalTime end = LocalTime.parse(endStr, F);
            Priority p = Priority.from(priorityStr);
            return new Task(IDGEN.getAndIncrement(), description, start, end, p);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid time format. Use HH:mm");
        }
    }
}
