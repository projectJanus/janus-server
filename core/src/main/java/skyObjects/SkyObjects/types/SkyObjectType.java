package skyObjects.SkyObjects.types;


public abstract class SkyObjectType {
    private String type;
    private String displayedName;
    private int weight;
    private int density;
    private int gravity;
    private float velocity;
    private float radius;

    public SkyObjectType(String type, String displayedName, int weight, int density,
                         int gravity, float velocity, float radius) {

        this.type = type;
        this.displayedName = displayedName;
        this.weight = weight;
        this.density = density;
        this.gravity = gravity;
        this.velocity = velocity;
        this.radius = radius;
    }


    public String getType() {
        return type;
    }

    public String getDisplayedName() {
        return displayedName;
    }

    public int getWeight() {
        return weight;
    }

    public int getDensity() {
        return density;
    }

    public int getGravity() {
        return gravity;
    }

    public float getVelocity() {
        return velocity;
    }

    public float getRadius() {
        return radius;
    }

}
