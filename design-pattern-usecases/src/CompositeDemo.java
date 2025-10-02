// Composite Pattern Demo
import java.util.*;
interface SmartEntity { void turnOn(); }
class Device implements SmartEntity {
    private String name;
    public Device(String n){ name=n; }
    public void turnOn(){ System.out.println(name + " turned on"); }
}
class Room implements SmartEntity {
    private List<SmartEntity> children = new ArrayList<>();
    public void add(SmartEntity e){ children.add(e); }
    public void turnOn(){ for(SmartEntity e:children) e.turnOn(); }
}
public class CompositeDemo {
    public static void main(String[] args){
        Device light = new Device("Light");
        Device fan = new Device("Fan");
        Room room = new Room();
        room.add(light); room.add(fan);
        room.turnOn();
    }
}
