package skyObjects.Pools;

import skyObjects.SkyObjects.NPCSkyObject;

/**
 * Pool for {@link NPCSkyObject}
 *
 * @author Raserei
 */
public class NPCSkyObjectPool extends Pool<NPCSkyObject> {
    @Override
    protected NPCSkyObject newObject() {
        return new NPCSkyObject();
    }
}
