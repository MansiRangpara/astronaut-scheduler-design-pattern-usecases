// Adapter Pattern Demo
class LegacyBooking {
    public void legacyReserve(){ System.out.println("Legacy system reservation"); }
}
interface BookingService { void book(); }
class LegacyAdapter implements BookingService {
    private LegacyBooking legacy;
    public LegacyAdapter(LegacyBooking l){ this.legacy = l; }
    public void book(){ legacy.legacyReserve(); }
}
public class AdapterDemo {
    public static void main(String[] args){
        BookingService s = new LegacyAdapter(new LegacyBooking());
        s.book();
    }
}
