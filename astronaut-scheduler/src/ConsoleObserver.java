public class ConsoleObserver implements TaskObserver {
    @Override public void notify(String message) {
        System.out.println("[ALERT] " + message);
    }
}
