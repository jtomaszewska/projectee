package Domain;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.*;

public class ObjectPlusPlus extends ObjectPlus implements Serializable {

    private static Set<ObjectPlusPlus> allParts = new HashSet<>();

    private Map<String, Map<Object, ObjectPlusPlus>> links = new Hashtable<>();

    public ObjectPlusPlus() {
        super();
    }

    private void addLink(String roleName, String reverseRoleName, ObjectPlusPlus targetObject, Object qualifier, int counter) {
        Map<Object, ObjectPlusPlus> objectLinks;
        if (counter < 1) {
            return;
        }
        if (links.containsKey(roleName)) {
            objectLinks = links.get(roleName);
        } else {
            objectLinks = new HashMap<>();
            links.put(roleName, objectLinks);
        }
        if (!objectLinks.containsKey(qualifier)) {
            objectLinks.put(qualifier, targetObject);
            targetObject.addLink(reverseRoleName, roleName, this, qualifier, counter - 1);
        }
    }

    public void addLink(String roleName, String reverseRoleName, ObjectPlusPlus targetObject, Object qualifier) {
        addLink(roleName, reverseRoleName, targetObject, qualifier, 2);
    }

    protected void addLink(String roleName, String reverseRoleName, ObjectPlusPlus targetObject) {
        addLink(roleName, reverseRoleName, targetObject, targetObject);
    }

    protected void addPart(String roleName, String reverseRoleName, ObjectPlusPlus partObject) throws Exception {
        if (allParts.contains(partObject)) {
            throw new Exception("The part is already connected to a whole!");
        }
        addLink(roleName, reverseRoleName, partObject);
        allParts.add(partObject);
    }

    public ObjectPlusPlus[] getLinks(String roleName) throws RuntimeException {
        Map<Object, ObjectPlusPlus> objectLinks;
        if (!links.containsKey(roleName)) {
            throw new RuntimeException("No links for the role: " + roleName);
        }
        objectLinks = links.get(roleName);
        return (ObjectPlusPlus[]) objectLinks.values().toArray(new ObjectPlusPlus[0]);
    }

    public boolean objectHasNoLinks(String roleName) {
        return !links.containsKey(roleName);
    }

    public void showLinks(String roleName, PrintStream stream) throws RuntimeException {
        Map<Object, ObjectPlusPlus> objectLinks;
        if (!links.containsKey(roleName)) {
            throw new RuntimeException("No links for the role: " + roleName);
        }
        objectLinks = links.get(roleName);
        Collection<ObjectPlusPlus> col = objectLinks.values();
        stream.println(this.getClass().getSimpleName() + " links, role '" + roleName + "':");
        for (ObjectPlusPlus obj : col) {
            stream.println(" " + obj);
        }
    }

    public ObjectPlusPlus getLinkedObject(String roleName, Object qualifier) throws Exception {
        Map<Object, ObjectPlusPlus> objectLinks;
        if (!links.containsKey(roleName)) {
            throw new Exception("No links for the role: " + roleName);
        }
        objectLinks = links.get(roleName);
        if (!objectLinks.containsKey(qualifier)) {
            throw new Exception("No link for the qualifer: " + qualifier);
        }
        return objectLinks.get(qualifier);
    }

    @Override
    public void destroyObject() {
        super.destroyObject();

        //muszę usunąć powiązania do obiektu - poszukać wszystkich relacji do tego obiektu - w obie strony i wyrzucic
        //Map<Object, ObjectPlusPlus> objectsLinks = links.
    }

}
