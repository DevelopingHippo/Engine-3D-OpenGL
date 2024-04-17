package engine.objects;

import engine.maths.Vector3f;
import engine.graphics.Mesh;

public class GameObject {

    private Vector3f position, rotation, scale;
    private Mesh mesh;


    public GameObject(Mesh mesh, Vector3f position, Vector3f rotation, Vector3f scale) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
        this.mesh = mesh;
    }

    public void update() {

    }

    public Vector3f getPosition() {return position;}

    public Vector3f getRotation() {return rotation;}

    public Vector3f getScale() {return scale;}

    public Mesh getMesh() {return mesh;}
}
