package helpers;

import engine.io.ModelLoader;
import engine.objects.Camera;
import main.Main;
import engine.maths.Vector2f;
import engine.maths.Vector3f;
import engine.objects.GameObject;
import engine.io.Display;
import engine.io.Input;
import engine.Renderer;
import engine.graphics.*;

public class ReferenceList {

    public Display display;
    public Main main;
    public Input input;
    public Renderer renderer;
    public int display_width = 1920;
    public int display_height = 1080;
    public Vector3f background = new Vector3f(50, 150, 200);
    public Shader shader = new Shader("/shaders/mainVertex.glsl", "/shaders/mainFragment.glsl");
    public Camera camera = new Camera(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), this);

    public GameObject[] objects = new GameObject[2];
    public Mesh mesh = ModelLoader.loadModel("resources/models/dragon.obj", "/textures/hippo_texture.png");
    public GameObject object = new GameObject(mesh, new Vector3f(0, 0, -1),  new Vector3f(0, 0, 0),  new Vector3f(1, 1, 1));
    public float moveSpeed = 0.02f, mouseSensitivity = 0.05f;
}
