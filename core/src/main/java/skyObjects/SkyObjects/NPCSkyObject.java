package skyObjects.SkyObjects;

import com.badlogic.gdx.math.Vector2;

import java.util.Map;

import skyObjects.Modes.MovementMode;
import skyObjects.SkyObjects.types.NPCSkyObjectType;

/**
 * Class represents NPC
 *
 * @author Raserei
 */
public class NPCSkyObject extends SkyObject<NPCSkyObjectType> {
    private MovementMode movementMode;
    private Map<String, Float> movementParameters;

    /**
     * used for updates of the object
     *
     * @param delta
     */
    @Override
    public void update(float delta) {
        movementMode.move(this, delta);
    }

    /**
     * Method sets up the object parameters
     *
     * @param npcSkyObjectType contains all the type-related basic parameters
     * @param position sets the object in the particular place in the world
     */
    @Override
    public void set(NPCSkyObjectType npcSkyObjectType, Vector2 position) {
        super.set(npcSkyObjectType, position);
        this.movementMode = npcSkyObjectType.getMovementMode();
        this.movementParameters = npcSkyObjectType.getMovementParameters();
    }

    /**
     * Updates object direction
     *
     * @param x x
     * @param y y
     */
    public void setDirection(float x, float y) {
        this.direction.add(x, y).nor();
    }

    /**
     *
     * @return Movement parameters of this object. Currently equal to the basic type parameters
     */
    public Map<String, Float> getMovementParameters() {
        return movementParameters;
    }

}
