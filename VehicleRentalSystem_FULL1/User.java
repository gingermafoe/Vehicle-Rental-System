
import java.io.Serializable;
public class User implements Serializable{
    public String name,email,password,phone,dlNumber;
    public User(String n,String e,String p,String ph,String dl){ name=n; email=e; password=p; phone=ph; dlNumber=dl; }
}
