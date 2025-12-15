
import java.io.Serializable; import java.util.Date;
public class Booking implements Serializable{
    public String bookingId,userEmail,vehicleId; public Date requestedOn,approvedOn; public int days,hours; public double amount; public String paymentMethod; public BookingStatus status;
    public Booking(String id,String u,String v,Date d,int da,int h,double amt,String pm){ bookingId=id; userEmail=u; vehicleId=v; requestedOn=d; days=da; hours=h; amount=amt; paymentMethod=pm; status=BookingStatus.PENDING; }
}
