
import java.io.PrintWriter;import java.text.SimpleDateFormat;import java.util.*;
public class Exporter {
    public static void exportBookingsToCSV(VehicleRentalSystem app, Date from, Date to) throws Exception{
        List<Booking> list=new ArrayList<>();
        for(Booking b: app.bookings.values()){ Date d=b.requestedOn; if(d!=null && !d.before(from) && !d.after(to)) list.add(b); }
        try(PrintWriter pw=new PrintWriter("bookings_export.csv")){ pw.println("BookingID,UserEmail,VehicleID,RequestedOn,Days,Hours,Amount,Status,ApprovedOn"); SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy HH:mm");
            for(Booking b: list){ pw.printf("%s,%s,%s,%s,%d,%d,%.2f,%s,%s%n", b.bookingId,b.userEmail,b.vehicleId,sdf.format(b.requestedOn),b.days,b.hours,b.amount, b.status==null?"":b.status.toString(), b.approvedOn==null?"":sdf.format(b.approvedOn)); }
        }
    }
}
