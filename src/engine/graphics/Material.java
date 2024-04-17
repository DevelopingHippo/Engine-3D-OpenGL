package engine.graphics;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.IOException;
import java.util.Objects;


public class Material {

    private Texture texture;
    private float width, height;
    private int textureID;
    private final String filePath;
    public Material(String filePath){
        this.filePath = filePath;
    }

    public void create() {
        try {
            texture = TextureLoader.getTexture(filePath.split("[.]")[1], Objects.requireNonNull(Material.class.getResourceAsStream(filePath)), GL11.GL_NEAREST);
            width = texture.getWidth();
            height = texture.getHeight();
            textureID = texture.getTextureID();
        } catch(IOException e) {
            System.err.println("Couldn't find texture at " + filePath);
        }
    }

    public void destroy(){
        GL13.glDeleteTextures(textureID);
    }

    public float getWidth() {return width;}
    public void setWidth(float width) {this.width = width;}
    public float getHeight() {return height;}
    public void setHeight(float height) {this.height = height;}
    public int getTextureID() {return textureID;}
    public void setTextureID(int textureID) {this.textureID = textureID;}
}
