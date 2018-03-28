package skyObjects.Modes;

import java.util.Map;

import skyObjects.SkyObjects.NPCSkyObject;
import utils.math.Rnd;

/**
 * Class for random NPC movement.
 *
 * @author Raserei
 */
public class ChaoticMovementMode implements MovementMode<NPCSkyObject> {
    private static final String[] PARAMETER_LIST = new String[]{"DirectionChangePossibilityThreshold"};
    private Map<String, Float> movementParameters;

    /**
     * Method moves the object randomly.
     * All the random parameters will be taken from specified object.
     *
     * @param object {@link NPCSkyObject} that should be moved
     */
    @Override
    public void move(NPCSkyObject object, float delta) {
        movementParameters = object.getMovementParameters();
        if (!checkParams(movementParameters))
            throw new RuntimeException("Incorrect movement parameters");
        if (Rnd.nextFloat(0, 1) > movementParameters.get(PARAMETER_LIST[0])) {
            object.setDirection(Rnd.nextFloat(-1, 1), Rnd.nextFloat(-1, 1));
        }
        object.getPos().mulAdd(object.getDirection(), object.getVelocity() * delta);
    }

    /**
     * Checks the movement parameters of the object
     *
     * @param movementParameters parameters
     * @return true if params are correct, otherwise false
     */
    private boolean checkParams(Map<String, Float> movementParameters) {
        for (String param : PARAMETER_LIST) {
            if (!movementParameters.containsKey(param)) return false;
        }
        return true;
    }
}
