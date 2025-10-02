import java.util.List;
import java.util.Scanner;

public class ScheduleCLI {
    public static void main(String[] args){
        ScheduleManager mgr = ScheduleManager.getInstance();
        mgr.registerObserver(new ConsoleObserver());
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        System.out.println("Astronaut Daily Schedule Organizer");
        while(running){
            System.out.println("\nSelect: add | remove | view | viewprio | mark | exit");
            String cmd = sc.nextLine().trim();
            try {
                switch(cmd){
                    case "add":
                        System.out.print("Description: "); String desc = sc.nextLine();
                        System.out.print("Start (HH:mm): "); String s = sc.nextLine();
                        System.out.print("End (HH:mm): "); String e = sc.nextLine();
                        System.out.print("Priority (High/Medium/Low): "); String p = sc.nextLine();
                        Task t = TaskFactory.create(desc, s, e, p);
                        boolean ok = mgr.addTask(t);
                        System.out.println(ok ? "Task added." : "Conflict detected. Task not added.");
                        break;
                    case "remove":
                        System.out.print("Task id to remove: ");
                        int id = Integer.parseInt(sc.nextLine().trim());
                        System.out.println(mgr.removeTaskById(id) ? "Removed." : "Not found.");
                        break;
                    case "view":
                        List<Task> all = mgr.viewAllSorted();
                        if(all.isEmpty()) System.out.println("No tasks scheduled for the day.");
                        else all.forEach(System.out::println);
                        break;
                    case "viewprio":
                        System.out.print("Priority (High/Medium/Low): "); String pr = sc.nextLine();
                        List<Task> prTasks = mgr.viewByPriority(Priority.from(pr));
                        if(prTasks.isEmpty()) System.out.println("No tasks found.");
                        else prTasks.forEach(System.out::println);
                        break;
                    case "mark":
                        System.out.print("Task id to mark completed: "); int mid = Integer.parseInt(sc.nextLine().trim());
                        System.out.println(mgr.markCompleted(mid) ? "Marked completed." : "Not found.");
                        break;
                    case "exit":
                        running = false;
                        System.out.println("Goodbye.");
                        break;
                    default:
                        System.out.println("Unknown command.");
                }
            } catch(Exception ex){
                System.out.println("Error: " + ex.getMessage());
            }
        }
        sc.close();
    }
}
