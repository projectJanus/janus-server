package skyObjects.SkyObjects;

import com.badlogic.gdx.math.Vector2;

import skyObjects.SkyObjects.types.SkyObjectType;
import utils.math.Circle;

/**
 * Class is the abstraction of every player and non-player object in the game
 *
 * @param <T> shows the type of the object referred to {@link SkyObjectType}
 * @author Raserei
 */

public abstract class SkyObject<T extends SkyObjectType> extends Circle {
    protected String type;
    protected String displayedName;
    protected int weight;
    protected int density;
    protected int gravity;
    protected float velocity;
    protected float radius;
    protected Vector2 direction;
    protected boolean isDestroyed;

    /**
     * Object is created empty.
     * @see #set MUST be invoked before the first update
     */
    public SkyObject() {
        pos = new Vector2();
        direction = new Vector2();
    }

    /**
     * Updates object
     */
    public abstract void update(float delta);

    /**
     * Sets the parameters to the object
     *
     * @param skyObjectType - reference type for all the common parameters
     * @param position - object initial position
     */
    public void set(T skyObjectType, Vector2 position) {
        this.type = skyObjectType.getType();
        this.displayedName = skyObjectType.getDisplayedName();
        this.weight = skyObjectType.getWeight();
        this.density = skyObjectType.getDensity();
        this.gravity = skyObjectType.getGravity();
        this.velocity = skyObjectType.getVelocity();
        this.radius = skyObjectType.getRadius();
        this.pos.set(position);
    }

    /**
     * @return Object move direction
     */
    public Vector2 getDirection() {
        return direction;
    }

    /**
     *
     * @return Object velocity
     */
    public float getVelocity() {
        return velocity;
    }

    /**
     *
     * @return true if object is destroyed, otherwise false
     */
    public boolean isDestroyed() {
        return isDestroyed;
    }

    /**
     *
     * @param destroyed sets isDestroyed flag for the object
     */
    public void setDestroyed(boolean destroyed) {
        isDestroyed = destroyed;
    }
}
