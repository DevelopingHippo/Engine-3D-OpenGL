package engine.io;

import helpers.ReferenceList;
import org.lwjgl.glfw.*;

public class Input {
    private static final boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST];
    private static final boolean[] buttons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];
    private static double mouseX;
    private static double mouseY;
    private static double scrollX, scrollY;

    private final GLFWKeyCallback keyboard;
    private final GLFWCursorPosCallback mouseMove;
    private final GLFWMouseButtonCallback mouseButton;
    private final GLFWScrollCallback mouseScroll;
    private final ReferenceList ref;


    public Input(ReferenceList ref){
        this.ref = ref;
        this.ref.input = this;
        this.keyboard = new GLFWKeyCallback(){public void invoke(long window, int key, int scancode, int action, int mods) {keys[key] = (action != GLFW.GLFW_RELEASE);}};
        this.mouseMove = new GLFWCursorPosCallback(){public void invoke(long window, double xpos, double ypos) {mouseX = xpos;mouseY = ypos;}};
        this.mouseButton = new GLFWMouseButtonCallback(){public void invoke(long window, int button, int action, int mods) {buttons[button] = (action != GLFW.GLFW_RELEASE);}};
        this.mouseScroll = new GLFWScrollCallback() {
            @Override
            public void invoke(long window, double offsetx, double offsety) {scrollX += offsetx;scrollY += offsety;}};
    }

    public static double getMouseX() {return mouseX;}
    public static double getMouseY() {return mouseY;}
    public static void setMouseX(double mouseX) {Input.mouseX = mouseX;}
    public static void setMouseY(double mouseY) {Input.mouseY = mouseY;}

    public static double getScrollY() {return scrollY;}
    public static double getScrollX() {return scrollY;}

    public GLFWKeyCallback getKeyboardCallback() {return keyboard;}
    public GLFWCursorPosCallback getMouseMoveCallback() {return mouseMove;}
    public GLFWMouseButtonCallback getMouseButtonCallback() {return mouseButton;}
    public GLFWScrollCallback getScrollCallback(){return this.mouseScroll;}

    public static boolean isKeyDown(int key) {
        return keys[key];
    }
    public static boolean isButtonDown(int button) {
        return buttons[button];
    }


    public void destroy() {
        keyboard.free();
        mouseMove.free();
        mouseButton.free();
        mouseScroll.free();
    }

}
