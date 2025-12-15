
import java.io.*;
public class Persistence {
    public static Object loadObject(String f, Object def) throws Exception { File x=new File(f); if(!x.exists()) return def; ObjectInputStream o=new ObjectInputStream(new FileInputStream(x)); Object r=o.readObject(); o.close(); return r; }
    public static void saveObject(String f, Object obj) { try { ObjectOutputStream o=new ObjectOutputStream(new FileOutputStream(f)); o.writeObject(obj); o.close(); } catch(Exception e) {} }
}
