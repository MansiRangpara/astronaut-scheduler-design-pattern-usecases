// Observer Pattern Demo
import java.util.*;

interface Observer { void update(String msg); }
class ConsoleObserver implements Observer {
    public void update(String msg){ System.out.println("Notification: " + msg); }
}
class Subject {
    private List<Observer> observers = new ArrayList<>();
    public void addObserver(Observer o){ observers.add(o); }
    public void notifyAll(String msg){ for(Observer o:observers) o.update(msg); }
}
public class ObserverDemo {
    public static void main(String[] args){
        Subject schedule = new Subject();
        schedule.addObserver(new ConsoleObserver());
        schedule.notifyAll("Task conflict detected!");
    }
}
