
import javax.swing.*;import javax.swing.table.*;import java.awt.*;import java.io.*;import java.text.*;import java.util.*;
public class VehicleRentalSystem {
    public Map<String,User> users=new HashMap<>();public Map<String,Vehicle> vehicles=new HashMap<>();
    public Map<String,Booking> bookings=new HashMap<>();JFrame mainFrame;public User currentUser;
    public static final double GST_RATE=0.18,SERVICE_CHARGE_RATE=0.02;DecimalFormat df=new DecimalFormat("0.00");
    public final String adminUser="admin",adminPass="admin123";
    public void loadAll(){try{users=(Map)Persistence.loadObject("users.ser",new HashMap());}catch(Exception e){}
        try{vehicles=(Map)Persistence.loadObject("vehicles.ser",new HashMap());}catch(Exception e){}
        try{bookings=(Map)Persistence.loadObject("bookings.ser",new HashMap());}catch(Exception e){}}
    public void saveUsers(){Persistence.saveObject("users.ser",users);}public void saveVehicles(){Persistence.saveObject("vehicles.ser",vehicles);}
    public void saveBookings(){Persistence.saveObject("bookings.ser",bookings);}
    public void buildDemoDataIfEmpty(){if(vehicles.isEmpty()){vehicles.put("C001",new Vehicle("C001","Honda City",VehicleType.CAR,600));
        vehicles.put("B001",new Vehicle("B001","Royal Enfield",VehicleType.BIKE,250));saveVehicles();}}
    public void buildUI(){ if(mainFrame!=null) try{ mainFrame.dispose(); }catch(Exception e){} mainFrame=new JFrame("Vehicle Rental System - Main Menu");mainFrame.setSize(900,600);
        JPanel p=new JPanel(new GridLayout(5,1,10,10));JButton r=new JButton("Register"),u=new JButton("User Login"),
        a=new JButton("Admin Login"),e=new JButton("Exit"),help=new JButton("Help");
        p.add(r);p.add(u);p.add(a);p.add(help);p.add(e);mainFrame.add(p);
        r.addActionListener(ev->UIHelpers.showRegisterDialog(mainFrame,this));
        u.addActionListener(ev->UIHelpers.showUserLogin(mainFrame,this));
        a.addActionListener(ev->UIHelpers.showAdminLogin(mainFrame,this));
        help.addActionListener(ev-> JOptionPane.showMessageDialog(mainFrame, "Vehicle Rental System\nUse admin to approve bookings."));
        e.addActionListener(ev->System.exit(0));mainFrame.setLocationRelativeTo(null);mainFrame.setVisible(true);}
    public String generateId(String p){return p+System.currentTimeMillis();}
    public String formatDate(Date d){return new SimpleDateFormat("dd-MM-yyyy HH:mm").format(d);}
}
