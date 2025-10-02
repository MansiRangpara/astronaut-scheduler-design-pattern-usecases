// Singleton Pattern Demo
class ScheduleManager {
    private static ScheduleManager instance;
    private ScheduleManager(){}
    public static ScheduleManager getInstance(){
        if(instance==null){ instance = new ScheduleManager(); }
        return instance;
    }
    public void show(){ System.out.println("Only one ScheduleManager instance"); }
}
public class SingletonDemo {
    public static void main(String[] args){
        ScheduleManager m1 = ScheduleManager.getInstance();
        ScheduleManager m2 = ScheduleManager.getInstance();
        m1.show();
        System.out.println("Same instance? " + (m1==m2));
    }
}
