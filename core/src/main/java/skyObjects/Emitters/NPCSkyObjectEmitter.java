package skyObjects.Emitters;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import skyObjects.SkyObjects.NPCSkyObject;
import skyObjects.Pools.NPCSkyObjectPool;
import skyObjects.SkyObjects.types.NPCSkyObjectType;
import utils.math.Circle;
import utils.math.Rnd;

/**
 * Class emits {@link NPCSkyObject}s and sets parameters taken from {@link NPCSkyObjectType} list reference.
 *
 * {@link #emitNPCSkyObject(float)}  should be invoked within the world update.
 *
 * @author Raserei
 */
public class NPCSkyObjectEmitter {
    private Circle worldBounds; //todo: test respawn within world bounds
    private NPCSkyObjectPool npcSkyObjectPool;
    private ArrayList<NPCSkyObjectType> reference;
    private Map<String, Float> generateTimers;
    private float generateInterval = 4f;

    /**
     * Creates emitter
     *
     * @param worldBounds      not used yet. Reserved to emit NPCs within the world bounds
     * @param npcSkyObjectPool NPC dummy object pool
     * @param reference        NPC types with physical and movement characteristics retrieved by {@link utils.ReferenceHandler}
     */
    public NPCSkyObjectEmitter(Circle worldBounds,
                               NPCSkyObjectPool npcSkyObjectPool,
                               ArrayList<NPCSkyObjectType> reference) {
        this.worldBounds = worldBounds;
        this.npcSkyObjectPool = npcSkyObjectPool;
        this.reference = reference;
        generateTimers = new HashMap<>();
        for (NPCSkyObjectType objType : reference) {
            generateTimers.put(objType.getType(), 0f);
        }
    }

    /**
     * Emits objects using randomizing parameters from references
     *
     * @param delta - global server delta time
     */
    public void emitNPCSkyObject(float delta) {
        for (NPCSkyObjectType objType : reference) {
            if (npcSkyObjectPool.getActiveObjects().size() >= objType.getMaxAmount()) return;
            String type = objType.getType();
            float temp = generateTimers.get(type);
            temp += delta;
            generateTimers.put(type, temp);
            if (temp >= Rnd.nextFloat(objType.getMinGenerateTime(), objType.getMaxGenerateTime())) {
                generateTimers.put(type, 0f);
                NPCSkyObject npcSkyObject = npcSkyObjectPool.obtain();
                npcSkyObject.set(reference.get(0),
                        //Achtung! Not tested!
                        new Vector2(Rnd.nextFloat(0, worldBounds.getPos().x), Rnd.nextFloat(0, worldBounds.getPos().y)));
            }
        }
    }
}
