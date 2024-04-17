package engine.graphics;

import engine.maths.Vector2f;
import engine.maths.Vector3f;

public class Vertex {

    private Vector3f position, normal, color;
    private Vector2f textureCoord;

    public Vertex(Vector3f position, Vector3f normal,Vector2f textureCoord){
        this.position = position;
        //this.color = color;
        this.normal = normal;
        this.textureCoord = textureCoord;
    }

    public Vector3f getNormal() {return normal;}
    public Vector3f getPosition() {return position;}
    public void setPosition(Vector3f position) {this.position = position;}
//    public Vector3f getColor() {return color;}
//    public void setColor(Vector3f color) {this.color = color;}
    public void setTextureCoord(Vector2f textureCoord) {this.textureCoord = textureCoord;}
    public Vector2f getTextureCoord() {return this.textureCoord;}
}
