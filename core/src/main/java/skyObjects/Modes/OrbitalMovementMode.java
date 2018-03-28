package skyObjects.Modes;

import java.util.Map;

import skyObjects.SkyObjects.NPCSkyObject;

/**
 * Class for Orbital NPC movement
 *
 * @author Raserei
 */
public class OrbitalMovementMode implements MovementMode<NPCSkyObject> {
    private static final String[] PARAMETER_LIST = new String[]{"RadiusCoefficient"};
    private Map<String, Float> movementParameters;

    /**
     * All the orbital parameters will be taken from and set to the specified object.
     *
     * @param object - SkyObject that should be moved
     */
    @Override
    public void move(NPCSkyObject object, float delta) {

    }
}
