package skyObjects.Modes;

import skyObjects.SkyObjects.SkyObject;

/**
 * Interface for movement strategy.
 *
 * @param <T> is the {@link SkyObject} to be moved. *
 *
 * @author Raserei
 */
public interface MovementMode<T extends SkyObject> {
    /**
     * Retreives, counts and sets params
     *
     * @param object SkyObject that should be moved
     */
    void move(T object, float delta);
}
