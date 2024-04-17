package main;
import engine.maths.Vector3f;
import engine.objects.GameObject;
import helpers.ReferenceList;
import org.lwjgl.glfw.GLFW;
import engine.io.Display;
import engine.io.Input;
import engine.Renderer;
import org.lwjglx.test.spaceinvaders.Game;

public class Main implements Runnable {
    public Thread game;
    public Display display;
    public ReferenceList ref = new ReferenceList();


    public static void main(String[] args) {
        new Main().start();
    }
    public void start() {
        ref.main = this;
        game = new Thread(this, "game");
        game.start();
    }
    @Override
    public void run() {
        init();
        while(!display.shouldClose() && game.isAlive() && !Input.isKeyDown(GLFW.GLFW_KEY_ESCAPE)){
            update();
            render();
            if(Input.isKeyDown(GLFW.GLFW_KEY_F11)){ display.setFullscreen(!display.isFullscreen()); }
            if(Input.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)){ display.mouseState(true);}
        }
        close();
    }

    public void init() {
        display = new Display( "3D Engine", ref);
        display.setBackgroundColor(0.5f, 0.5f, 1.0f);
        ref.renderer = new Renderer(ref, ref.shader);
        display.create();
        ref.mesh.create();
        ref.shader.create();
//        for(int i = 0; i < ref.objects.length; i++) {
//            ref.objects[i] = new GameObject(ref.mesh, new Vector3f((float) (Math.random() * 50 - 25), (float) (Math.random() * 50 - 25), (float) (Math.random() * 50 - 25)),  new Vector3f(0, 0, 0),  new Vector3f(1, 1, 1));
//        }
    }

    public void update(){
        display.update();
        //ref.object.update();
        //ref.camera.update(ref.object);
        ref.camera.update();
    }

    public void render(){
        ref.renderer.renderMesh(ref.object);
//        for(int i = 0; i < ref.objects.length; i++) {
//            if(ref.objects[i] != null) {
//                ref.renderer.renderMesh(ref.objects[i]);
//            }
//        }
        display.swapBuffers();
    }

    public void close() {
        ref.mesh.destroy();
        display.destroy();
        ref.shader.destroy();
    }
}
