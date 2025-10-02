import java.time.LocalTime;
public class Task {
    private final int id;
    private String description;
    private LocalTime start;
    private LocalTime end;
    private Priority priority;
    private boolean completed;

    public Task(int id, String description, LocalTime start, LocalTime end, Priority priority){
        if(start == null || end == null) throw new IllegalArgumentException("Start/end required");
        if(end.isBefore(start) || end.equals(start)) throw new IllegalArgumentException("End must be after start");
        this.id=id; this.description=description; this.start=start; this.end=end; this.priority=priority; this.completed=false;
    }

    public int getId(){ return id; }
    public String getDescription(){ return description; }
    public LocalTime getStart(){ return start; }
    public LocalTime getEnd(){ return end; }
    public Priority getPriority(){ return priority; }
    public boolean isCompleted(){ return completed; }

    public void setDescription(String d){ this.description=d; }
    public void setStart(LocalTime s){ this.start=s; }
    public void setEnd(LocalTime e){ this.end=e; }
    public void setPriority(Priority p){ this.priority=p; }
    public void setCompleted(boolean c){ this.completed=c; }

    @Override
    public String toString(){
        return String.format("%02d) %s - %s: %s [%s]%s",
            id, start.toString(), end.toString(), description, priority,
            completed ? " (Completed)" : "");
    }
}
