
import javax.swing.*;import java.awt.*;
public class UIHelpers {
    public static void showRegisterDialog(JFrame parent,VehicleRentalSystem app){
        JDialog d=new JDialog(parent,"Register",true);d.setSize(350,260);d.setLocationRelativeTo(parent);
        JPanel p=new JPanel(new GridLayout(6,2,6,6));JTextField n=new JTextField(),e=new JTextField(),ph=new JTextField(),dl=new JTextField();
        JPasswordField pw=new JPasswordField();
        p.add(new JLabel("Name:"));p.add(n);p.add(new JLabel("Email (Gmail only):"));p.add(e);p.add(new JLabel("Pass:"));p.add(pw);
        p.add(new JLabel("Phone:"));p.add(ph);p.add(new JLabel("DL (mandatory):"));p.add(dl);
        JButton ok=new JButton("Register"),c=new JButton("Cancel");p.add(ok);p.add(c);d.add(p);
        ok.addActionListener(ev->{String email=e.getText().trim(); if(!email.endsWith("@gmail.com")){ JOptionPane.showMessageDialog(d,"Please provide a Gmail address (example@gmail.com)"); return; }
            if(n.getText().isEmpty()||email.isEmpty()||new String(pw.getPassword()).isEmpty()||dl.getText().isEmpty()){ JOptionPane.showMessageDialog(d,"Fill all fields"); return; }
            if(app.users.containsKey(email)){ JOptionPane.showMessageDialog(d,"Email already registered"); return; }
            User u=new User(n.getText(),email,new String(pw.getPassword()),ph.getText(),dl.getText());
            app.users.put(email,u);app.saveUsers();JOptionPane.showMessageDialog(d,"Registered");d.dispose();});
        c.addActionListener(ev->d.dispose());d.setVisible(true);
    }
    public static void showUserLogin(JFrame parent,VehicleRentalSystem app){
        JDialog d=new JDialog(parent,"Login",true);d.setSize(300,160);d.setLocationRelativeTo(parent);
        JPanel p=new JPanel(new GridLayout(3,2,6,6));JTextField e=new JTextField();JPasswordField pw=new JPasswordField();
        p.add(new JLabel("Email:"));p.add(e);p.add(new JLabel("Pass:"));p.add(pw);
        JButton ok=new JButton("Login"),c=new JButton("Cancel");p.add(ok);p.add(c);d.add(p);
        ok.addActionListener(ev->{User u=app.users.get(e.getText().trim()); if(u!=null && u.password.equals(new String(pw.getPassword()))){ app.currentUser=u; d.dispose(); UserUI.showUserDashboard(app,parent);} else JOptionPane.showMessageDialog(d,"Invalid credentials");});
        c.addActionListener(ev->d.dispose());d.setVisible(true);
    }
    public static void showAdminLogin(JFrame parent,VehicleRentalSystem app){
        JDialog d=new JDialog(parent,"Admin",true);d.setSize(300,160);d.setLocationRelativeTo(parent);
        JPanel p=new JPanel(new GridLayout(3,2,6,6));JTextField u=new JTextField();JPasswordField pw=new JPasswordField();
        p.add(new JLabel("User:"));p.add(u);p.add(new JLabel("Pass:"));p.add(pw);
        JButton ok=new JButton("Login"),c=new JButton("Cancel");p.add(ok);p.add(c);d.add(p);
        ok.addActionListener(ev->{if(app.adminUser.equals(u.getText().trim())&&app.adminPass.equals(new String(pw.getPassword()))){ d.dispose(); AdminUI.showAdminDashboard(app,parent);} else JOptionPane.showMessageDialog(d,"Invalid credentials");});
        c.addActionListener(ev->d.dispose());d.setVisible(true);
    }
}
