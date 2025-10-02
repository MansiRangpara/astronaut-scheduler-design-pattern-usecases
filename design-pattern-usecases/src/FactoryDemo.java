// Factory Pattern Demo
class Task {
    String description;
    Task(String d){ description = d; }
}
class TaskFactory {
    public static Task create(String desc){
        return new Task(desc);
    }
}
public class FactoryDemo {
    public static void main(String[] args){
        Task t = TaskFactory.create("Experiment");
        System.out.println("Task created: " + t.description);
    }
}
