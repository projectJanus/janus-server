package skyObjects.Pools;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import skyObjects.SkyObjects.SkyObject;

/**
 * Abstract class for Pools of different Sky Objects
 *
 * {@link #obtain()}  is used for returning the object from the pool
 * {@link #update(float)} invokes active objects for update
 * {@link #freeAllDestroyedActiveObjects()} is used to return destroyed objects to the pool
 * @param <T> should extend {@link SkyObject}
 *
 * @author Raserei
 */
public abstract class Pool<T extends SkyObject> {
    protected final List<T> activeObjects = new LinkedList<>(); //active objects list
    protected final List<T> freeObjects = new ArrayList<>();   // free/waiting objects list

    protected abstract T newObject();

    /**
     * Method moves (if any) one object from free list or add new one to active list
     * @return the skyObject. Set method MUST be applied before the use.
     */
    public T obtain() {
        T object;
        if (freeObjects.isEmpty()) {
            object = newObject();
        } else object = freeObjects.remove(freeObjects.size() - 1);
        activeObjects.add(object);
        return object;
    }

    /**
     * Invokes active objects update
     * @param delta - delta time
     */
    public void update(float delta) {
        for (T o : activeObjects) {
            o.update(delta);
        }
    }

    /**
     * Takes one object from active list and puts it to the free one
     * @param object
     */
    public void free(T object) {
        if (!activeObjects.remove(object)) {
            throw new RuntimeException("Non-existing object deletion");
        }
        freeObjects.add(object);
    }

    /**
     * @see #free(SkyObject) is invoked for every destroyed active object
     */
    public void freeAllDestroyedActiveObjects() {
        for (int i = 0; i < activeObjects.size(); i++) {
            T obj = activeObjects.get(i);
            if (obj.isDestroyed()){
                free(obj);
                i--;
                obj.setDestroyed(false);
            }
        }
    }

    /**
     * @return list of all active objects
     */
    public List<T> getActiveObjects() {
        return activeObjects;
    }
}
