
import java.awt.*;import java.awt.image.BufferedImage;import java.io.File;import javax.imageio.ImageIO;
public class ImageBuilders {
    public static BufferedImage build(Booking b,VehicleRentalSystem app) throws Exception{
        int w=900,h=1200;BufferedImage img=new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
        Graphics2D g=img.createGraphics();g.setColor(new Color(248,248,250));g.fillRect(0,0,w,h);
        g.setColor(new Color(20,87,153));g.fillRect(0,0,w,100);g.setColor(Color.WHITE);g.setFont(new Font("SansSerif",Font.BOLD,30));g.drawString("KLH Rentals",30,60);
        try{File logo=new File("klh_logo.png");if(logo.exists()){g.drawImage(ImageIO.read(logo),w-150,20,120,60,null);}}catch(Exception e){}
        int y=140;g.setColor(Color.BLACK);g.setFont(new Font("SansSerif",Font.PLAIN,16));User u=app.users.get(b.userEmail);Vehicle v=app.vehicles.get(b.vehicleId);
        g.drawString("Booking ID: "+b.bookingId,30,y);y+=25;g.drawString("Customer: "+u.name,30,y);y+=25;g.drawString("Vehicle: "+v.name,30,y);y+=25;
        double base=b.amount,svc=base*VehicleRentalSystem.SERVICE_CHARGE_RATE,gst=(base+svc)*VehicleRentalSystem.GST_RATE,total=base+svc+gst;
        y+=20;g.setFont(new Font("SansSerif",Font.BOLD,18));g.drawString("Amount Summary",30,y);y+=25;g.setFont(new Font("SansSerif",Font.PLAIN,16));
        g.drawString("Base: ₹"+app.df.format(base),30,y);y+=20;g.drawString("Service: ₹"+app.df.format(svc),30,y);y+=20;g.drawString("GST: ₹"+app.df.format(gst),30,y);y+=20;g.drawString("Total: ₹"+app.df.format(total),30,y+10);
        g.dispose();return img;
    }
}
