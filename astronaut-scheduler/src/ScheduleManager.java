import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ScheduleManager {
    private static volatile ScheduleManager instance;
    private final List<Task> tasks = new CopyOnWriteArrayList<>();
    private final List<TaskObserver> observers = new CopyOnWriteArrayList<>();
    private final Logger logger = Logger.getLogger(ScheduleManager.class.getName());

    private ScheduleManager(){}

    public static ScheduleManager getInstance(){
        if(instance == null){
            synchronized(ScheduleManager.class){
                if(instance == null) instance = new ScheduleManager();
            }
        }
        return instance;
    }

    public void registerObserver(TaskObserver o){ observers.add(o); }
    public void unregisterObserver(TaskObserver o){ observers.remove(o); }

    private void notifyAllObservers(String msg){
        observers.forEach(o -> {
            try { o.notify(msg); } catch(Exception ex){ logger.warning("Observer failed: "+ex.getMessage()); }
        });
    }

    public boolean addTask(Task t){
        Objects.requireNonNull(t);
        Optional<Task> conflict = tasks.stream().filter(existing ->
            t.getStart().isBefore(existing.getEnd()) && existing.getStart().isBefore(t.getEnd())
        ).findFirst();

        if(conflict.isPresent()){
            String msg = String.format("Task conflicts with existing task [%d] %s", conflict.get().getId(), conflict.get().getDescription());
            notifyAllObservers(msg);
            logger.info("Conflict: " + msg);
            return false;
        }
        tasks.add(t);
        logger.info("Added task " + t.getId());
        notifyAllObservers("Task added: " + t.getDescription());
        return true;
    }

    public boolean removeTaskById(int id){
        Optional<Task> opt = tasks.stream().filter(x->x.getId()==id).findFirst();
        if(opt.isPresent()){
            tasks.remove(opt.get());
            logger.info("Removed task " + id);
            notifyAllObservers("Task removed: id="+id);
            return true;
        } else {
            return false;
        }
    }

    public List<Task> viewAllSorted(){
        return tasks.stream().sorted(Comparator.comparing(Task::getStart)).collect(Collectors.toList());
    }

    public List<Task> viewByPriority(Priority p){
        return tasks.stream().filter(t->t.getPriority()==p).sorted(Comparator.comparing(Task::getStart)).collect(Collectors.toList());
    }

    public Optional<Task> findById(int id){ return tasks.stream().filter(t->t.getId()==id).findFirst(); }

    public boolean markCompleted(int id){
        Optional<Task> op = findById(id);
        if(op.isPresent()){
            op.get().setCompleted(true);
            notifyAllObservers("Task marked completed: id=" + id);
            return true;
        }
        return false;
    }
}
