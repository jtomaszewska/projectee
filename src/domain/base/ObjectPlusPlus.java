package domain.base;

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

    public void addLink(String roleName, String reverseRoleName, ObjectPlusPlus targetObject) {
        addLink(roleName, reverseRoleName, targetObject, targetObject);
    }

    public void addPart(String roleName, String reverseRoleName, ObjectPlusPlus partObject) {
        if (allParts.contains(partObject)) {
            throw new DomainException("The part is already connected to a whole!");
        }
        addLink(roleName, reverseRoleName, partObject);
        allParts.add(partObject);
    }

    public ObjectPlusPlus[] getLinks(String roleName) {
        Map<Object, ObjectPlusPlus> objectLinks;
        if (!links.containsKey(roleName)) {
            throw new DomainException("No links for the role: " + roleName);
        }
        objectLinks = links.get(roleName);
        return (ObjectPlusPlus[]) objectLinks.values().toArray(new ObjectPlusPlus[0]);
    }

    public boolean objectHasNoLinks(String roleName) {
        return !links.containsKey(roleName);
    }

    public void showLinks(String roleName, PrintStream stream) {
        Map<Object, ObjectPlusPlus> objectLinks;
        if (!links.containsKey(roleName)) {
            throw new DomainException("No links for the role: " + roleName);
        }
        objectLinks = links.get(roleName);
        Collection<ObjectPlusPlus> col = objectLinks.values();
        stream.println(this.getClass().getSimpleName() + " links, role '" + roleName + "':");
        for (ObjectPlusPlus obj : col) {
            stream.println(" " + obj);
        }
    }

    public List<ObjectPlusPlus> getLinkedObjects(String roleName) {
        List<ObjectPlusPlus> linkedObjects = new ArrayList<>();
        Map<Object, ObjectPlusPlus> objectLinks;
        if (!links.containsKey(roleName)) {
            return null;
        }
        objectLinks = links.get(roleName);
        Collection<ObjectPlusPlus> col = objectLinks.values();
        linkedObjects.addAll(col);
        return linkedObjects;
    }

    public ObjectPlusPlus getLinkedObject(String roleName, Object qualifier) {
        Map<Object, ObjectPlusPlus> objectLinks;
        if (!links.containsKey(roleName)) {
            throw new DomainException("No links for the role: " + roleName);
        }
        objectLinks = links.get(roleName);
        if (!objectLinks.containsKey(qualifier)) {
            throw new DomainException("No link for the qualifer: " + qualifier);
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
