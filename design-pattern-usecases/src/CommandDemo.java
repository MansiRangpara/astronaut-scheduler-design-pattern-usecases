// Command Pattern Demo
interface Command { void execute(); }
class Rover {
    public void move(){ System.out.println("Rover moves forward"); }
}
class MoveCommand implements Command {
    private Rover rover;
    public MoveCommand(Rover r){ this.rover = r; }
    public void execute(){ rover.move(); }
}
public class CommandDemo {
    public static void main(String[] args){
        Rover r = new Rover();
        Command cmd = new MoveCommand(r);
        cmd.execute();
    }
}
