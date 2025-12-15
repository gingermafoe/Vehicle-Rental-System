
import java.awt.*;import java.awt.image.BufferedImage;import javax.swing.*;import javax.imageio.ImageIO;import java.io.File;
public class InvoiceGenerator {
    public static void generate(Booking b,VehicleRentalSystem app,Component parent) throws Exception{
        BufferedImage img=ImageBuilders.build(b,app);
        String name="Bill_"+b.bookingId+".png";File f=new File(name);ImageIO.write(img,"png",f);
        try{if(Desktop.isDesktopSupported())Desktop.getDesktop().open(f);}catch(Exception e){}
        ImageIcon ic=new ImageIcon(img.getScaledInstance(600,-1,Image.SCALE_SMOOTH));
        JOptionPane.showMessageDialog(parent,new JScrollPane(new JLabel(ic)),"Invoice Preview",JOptionPane.PLAIN_MESSAGE);
    }
}
