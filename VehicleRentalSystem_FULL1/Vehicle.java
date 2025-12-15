
import java.io.Serializable;
public class Vehicle implements Serializable{ public String id,name; public VehicleType type; public double ratePerDay; public Vehicle(String i,String n,VehicleType t,double r){ id=i; name=n; type=t; ratePerDay=r; } }
