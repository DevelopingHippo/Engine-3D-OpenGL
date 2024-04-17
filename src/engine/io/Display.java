package engine.io;
import engine.maths.Matrix4f;
import helpers.ReferenceList;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;


public class Display {
    private final String title;
    private long window;
    private int frameCounter;
    private long time;
    private final ReferenceList ref;
    public Input input;

    private GLFWWindowSizeCallback sizeCallback;
    private boolean isResized;
    private boolean isFullscreen;
    private int[] windowPosX = new int[1], windowPosY = new int[1];
    private Matrix4f projection;

    public Display(String title, ReferenceList refs) {
        this.title = title;
        this.ref = refs;
        ref.display = this;
        projection = Matrix4f.projection(70.0f, (float) ref.display_width / (float) ref.display_height, 0.1f, 1000.0f);
    }

    public void create() {
        if(!GLFW.glfwInit()){
            System.err.println("ERROR: GLFW wasn't initialized!");
            return;
        }
        window = GLFW.glfwCreateWindow(ref.display_width, ref.display_height, title, isFullscreen ? GLFW.glfwGetPrimaryMonitor(): 0, 0);

        if(window == 0) {
            System.err.println("ERROR: Window wasn't created!");
            return;
        }

        input = new Input(ref);
        GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());

        assert videoMode != null;

        windowPosX[0] = ((videoMode.width() - ref.display_width) / 2);
        windowPosY[0] = ((videoMode.height() - ref.display_height) / 2);

        GLFW.glfwSetWindowPos(window, windowPosX[0], windowPosY[0]);
        GLFW.glfwMakeContextCurrent(window);
        GL.createCapabilities();
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        createCallbacks();

        GLFW.glfwShowWindow(window);
        GLFW.glfwSwapInterval(1);
        time = System.currentTimeMillis();
    }

    private void createCallbacks() {
        sizeCallback = new GLFWWindowSizeCallback() {
            @Override
            public void invoke(long window, int width, int height) {
                ref.display_width = width;
                ref.display_height = height;
                isResized = true;
            }
        };
        GLFW.glfwSetKeyCallback(window, input.getKeyboardCallback());
        GLFW.glfwSetCursorPosCallback(window, input.getMouseMoveCallback());
        GLFW.glfwSetMouseButtonCallback(window, input.getMouseButtonCallback());
        GLFW.glfwSetWindowSizeCallback(window, sizeCallback);
        GLFW.glfwSetScrollCallback(window, input.getScrollCallback());
    }


    public void update() {
        if(isResized){
            GL11.glViewport(0,0, ref.display_width, ref.display_height);
            isResized = false;
        }
        GL11.glClearColor(ref.background.getX(), ref.background.getY(), ref.background.getZ(), 1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

        GLFW.glfwPollEvents();
        frameCounter++;
        if(System.currentTimeMillis() > time + 1000){
            GLFW.glfwSetWindowTitle(window, title + " | FPS: " + frameCounter);
            time = System.currentTimeMillis();
            frameCounter = 0;
        }
    }

    public void swapBuffers() {
        GLFW.glfwSwapBuffers(window);
    }
    public boolean shouldClose() {
        return GLFW.glfwWindowShouldClose(window);
    }
    public void destroy() {
        ref.input.destroy();
        GLFW.glfwWindowShouldClose(window);
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }
    public void setBackgroundColor(float r, float g, float b) {
        ref.background.set(r, b, g);
    }

    public boolean isFullscreen() {
        return this.isFullscreen;
    }
    public void setFullscreen(boolean isFullscreen) {
        this.isFullscreen = isFullscreen;
        isResized = true;

        if(isFullscreen) {
            GLFW.glfwGetWindowPos(window, windowPosX, windowPosY);
            GLFW.glfwSetWindowMonitor(window, GLFW.glfwGetPrimaryMonitor(), 0, 0, ref.display_width, ref.display_height, 0);
        }
        else {
            GLFW.glfwSetWindowMonitor(window, 0, windowPosX[0], windowPosY[0], ref.display_width, ref.display_height, 0);
        }
    }

    public void mouseState(boolean lock) {
        GLFW.glfwSetInputMode(window, GLFW.GLFW_CURSOR, lock ? GLFW.GLFW_CURSOR_DISABLED : GLFW.GLFW_CURSOR_NORMAL);
    }

    public Matrix4f getProjectionMatrix() {return projection;}
}
