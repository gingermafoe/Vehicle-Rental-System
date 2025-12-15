
import javax.swing.*;import javax.swing.table.*;import java.awt.*;import java.text.SimpleDateFormat;import java.util.Date;
import java.util.*;
public class AdminUI {
    public static void showAdminDashboard(VehicleRentalSystem app,Component parent){
        JFrame f=new JFrame("Admin Console - KLH");f.setSize(1000,600);f.setLocationRelativeTo(parent);
        JPanel root=new JPanel(new BorderLayout(10,10));JLabel header=new JLabel("<html><h2 style='margin:0'>Admin Console</h2><small>Manage bookings, vehicles & reports</small></html>");
        header.setBorder(BorderFactory.createEmptyBorder(6,6,6,6));root.add(header,BorderLayout.NORTH);
        JSplitPane split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);DefaultTableModel bm=new DefaultTableModel(new String[]{"BookingID","User","Veh","ReqOn","Days","Hours","Amt","Status"},0);
        JTable tbl=new JTable(bm);refreshBookingsTable(app,bm);split.setLeftComponent(new JScrollPane(tbl));
        JPanel right=new JPanel();right.setLayout(new BoxLayout(right,BoxLayout.Y_AXIS));right.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        JPanel addPanel=new JPanel(new GridLayout(5,2,6,6));addPanel.setBorder(BorderFactory.createTitledBorder("Add Vehicle"));
        JTextField tfId=new JTextField(),tfName=new JTextField(),tfRate=new JTextField();JComboBox<String> cbType=new JComboBox<>(new String[]{"CAR","BIKE"});
        addPanel.add(new JLabel("Vehicle ID:"));addPanel.add(tfId);addPanel.add(new JLabel("Name:"));addPanel.add(tfName);addPanel.add(new JLabel("Type:"));addPanel.add(cbType);
        addPanel.add(new JLabel("Rate/day (INR):"));addPanel.add(tfRate);JButton btnAdd=new JButton("Add Vehicle");addPanel.add(btnAdd);right.add(addPanel);
        JPanel expPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));expPanel.setBorder(BorderFactory.createTitledBorder("Export Bookings (by requested date range)"));
        expPanel.add(new JLabel("From:"));SpinnerDateModel fromModel=new SpinnerDateModel(new Date(System.currentTimeMillis()-7L*24*3600*1000),null,null,java.util.Calendar.DAY_OF_MONTH);
        JSpinner spFrom=new JSpinner(fromModel);spFrom.setEditor(new JSpinner.DateEditor(spFrom,"dd-MM-yyyy"));expPanel.add(spFrom);
        expPanel.add(new JLabel("To:"));SpinnerDateModel toModel=new SpinnerDateModel(new Date(),null,null,java.util.Calendar.DAY_OF_MONTH);
        JSpinner spTo=new JSpinner(toModel);spTo.setEditor(new JSpinner.DateEditor(spTo,"dd-MM-yyyy"));expPanel.add(spTo);JButton btnExport=new JButton("Export CSV");expPanel.add(btnExport);right.add(expPanel);
        JPanel act=new JPanel(new FlowLayout(FlowLayout.LEFT));JButton btnApprove=new JButton("Approve Selected"),btnReject=new JButton("Reject Selected"),btnMain=new JButton("Return to Main Menu");
        act.add(btnApprove);act.add(btnReject);act.add(btnMain);right.add(act);split.setRightComponent(right);split.setDividerLocation(620);root.add(split,BorderLayout.CENTER);
        f.setContentPane(root);f.setVisible(true);
        btnAdd.addActionListener(ev->{try{String id=tfId.getText().trim(),name=tfName.getText().trim();double rate=Double.parseDouble(tfRate.getText().trim());if(id.isEmpty()||name.isEmpty()){JOptionPane.showMessageDialog(f,"Fill details");return;}Vehicle v=new Vehicle(id,name,VehicleType.valueOf((String)cbType.getSelectedItem()),rate);app.vehicles.put(id,v);app.saveVehicles();refreshBookingsTable(app,bm);JOptionPane.showMessageDialog(f,"Vehicle added.");}catch(Exception ex){JOptionPane.showMessageDialog(f,ex.getMessage());}});
        btnApprove.addActionListener(ev->{int r=tbl.getSelectedRow();if(r<0){JOptionPane.showMessageDialog(f,"Select booking");return;}String bid=(String)tbl.getValueAt(r,0);Booking b=app.bookings.get(bid);if(b!=null){b.status=BookingStatus.APPROVED;b.approvedOn=new Date();app.saveBookings();tbl.setValueAt("APPROVED",r,7);} });
        btnReject.addActionListener(ev->{int r=tbl.getSelectedRow();if(r<0){JOptionPane.showMessageDialog(f,"Select booking");return;}String bid=(String)tbl.getValueAt(r,0);Booking b=app.bookings.get(bid);if(b!=null){b.status=BookingStatus.REJECTED;app.saveBookings();tbl.setValueAt("REJECTED",r,7);} });
        btnExport.addActionListener(ev->{try{Date from=(Date)spFrom.getValue();Date to=(Date)spTo.getValue();SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");Date fromD=sdf.parse(sdf.format(from));Date toD=sdf.parse(sdf.format(to));toD=new Date(toD.getTime() + (24L*3600*1000)-1);Exporter.exportBookingsToCSV(app,fromD,toD);JOptionPane.showMessageDialog(f,"CSV saved to bookings_export.csv");}catch(Exception ex){JOptionPane.showMessageDialog(f,ex.getMessage());}});
        btnMain.addActionListener(ev->{f.dispose();app.buildUI();});
    }
    private static void refreshBookingsTable(VehicleRentalSystem app, DefaultTableModel bm){bm.setRowCount(0);for(Booking b:app.bookings.values()){bm.addRow(new Object[]{b.bookingId,b.userEmail,b.vehicleId,app.formatDate(b.requestedOn),b.days,b.hours,app.df.format(b.amount),b.status});}}
}
