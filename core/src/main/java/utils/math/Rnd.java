package utils.math;

import java.security.SecureRandom;

/**
 * Random generator
 */
public class Rnd {
    private static final SecureRandom random = new SecureRandom();
    /**
     * Generate random float
     *
     * @param min min random value
     * @param max max random value
     * @return result
     */
    public static float nextFloat(float min, float max) {
        return random.nextFloat() * (max - min) + min;
    }
}
