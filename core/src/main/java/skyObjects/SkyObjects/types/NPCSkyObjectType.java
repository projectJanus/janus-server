package skyObjects.SkyObjects.types;


import java.util.Map;

import skyObjects.Modes.*;

/**
 * Created by Raserei on 01.03.2018.
 */

public class NPCSkyObjectType extends SkyObjectType {
    private MovementMode movementMode;
    private Map<String, Float> movementParameters;

    private int minAmount;
    private int maxAmount;
    private float minGenerateTime;
    private float maxGenerateTime;

    public NPCSkyObjectType(String type, String displayedName, int weight, int density, int gravity, float velocity,
                            float radius, String movementMode, Map<String, Float> movementParameters,
                            int minAmount, int maxAmount, float minGenerateTime, float maxGenerateTime) {
        super(type, displayedName, weight, density, gravity, velocity, radius
        );
        switch (movementMode){
            case "ChaoticMode":
                this.movementMode = new ChaoticMovementMode();
                break;
            case "OrbitalMode":
                this.movementMode = new OrbitalMovementMode();
                break;
        }
        this.movementParameters = movementParameters;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.minGenerateTime = minGenerateTime;
        this.maxGenerateTime = maxGenerateTime;
    }


    public MovementMode getMovementMode() {
        return movementMode;
    }

    public Map<String, Float> getMovementParameters() {
        return movementParameters;
    }


    public int getMinAmount() {
        return minAmount;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public float getMinGenerateTime() {
        return minGenerateTime;
    }

    public float getMaxGenerateTime() {
        return maxGenerateTime;
    }

}
