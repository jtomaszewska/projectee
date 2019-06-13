package domain.base;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectPlus {

    protected static Map<Class, List<ObjectPlus>> allExtents = new HashMap<>();

    public ObjectPlus() {
        List<ObjectPlus> extent = null;
        Class theClass = this.getClass();
        if(allExtents.containsKey(theClass)) {
            extent = allExtents.get(theClass);
        }
        else {
            extent = new ArrayList<>();
            allExtents.put(theClass, extent);
        }
        extent.add(this);
    }

    public static void writeExtents(ObjectOutputStream stream) throws IOException {
        stream.writeObject(allExtents);
    }
    public static void readExtents(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        Object readObject = stream.readObject();
        if(!(readObject instanceof Map)) {
            throw new IOException("Unexpected object type: " + readObject.getClass().getName());
        }
        allExtents = (Map<Class, List<ObjectPlus>>) stream.readObject();
    }

    public void destroyObject(){
        if(allExtents.get(this.getClass()).remove(this)){
            System.out.println("Object "+this.toString()+" destroyed");
        }
    }

    public static void logExtents() {
        for (Class classEntity: allExtents.keySet()) {
            for (ObjectPlus obj : allExtents.get(classEntity)) {
                System.out.println(obj);
            }
        }
    }
}
