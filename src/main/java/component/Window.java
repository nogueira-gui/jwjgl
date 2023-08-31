package component;

import java.util.Objects;
import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWErrorCallbackI;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class Window {
    int width = 800;
    int height = 640;
    String title = "Teste";
    private long glfwWindow;
    private static Window window = null;
    private Triangle triangle;

    private Window(Triangle triangle) {
        this.triangle = triangle;
    }

    public static Window get(Triangle triangle) {
        if (Objects.isNull(window)) {
            window = new Window(triangle);
        }

        return window;
    }

    public void run() {
        System.out.println("Hello, LWJGL");
        this.init();
        this.loop();
        Callbacks.glfwFreeCallbacks(this.glfwWindow);
        GLFW.glfwDestroyWindow(this.glfwWindow);
        GLFW.glfwTerminate();
        GLFW.glfwSetErrorCallback((GLFWErrorCallbackI)null).free();
    }

    public void init() {
        GLFWErrorCallback.createPrint(System.err).set();
        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("unable to initialize GLFW");
        } else {
            GLFW.glfwDefaultWindowHints();
            GLFW.glfwWindowHint(131076, 0);
            GLFW.glfwWindowHint(131075, 1);
            GLFW.glfwWindowHint(131080, 1);
            this.glfwWindow = GLFW.glfwCreateWindow(this.width, this.height, this.title, 0L, 0L);
            if (Objects.isNull(this.glfwWindow)) {
                throw new IllegalStateException("Failed to create the glfw window");
            } else {
                GLFW.glfwMakeContextCurrent(this.glfwWindow);
                GLFW.glfwSwapInterval(1);
                GLFW.glfwShowWindow(this.glfwWindow);
                GL.createCapabilities();
            }
        }
    }

    public void loop() {
        while(!GLFW.glfwWindowShouldClose(this.glfwWindow)) {
            GLFW.glfwPollEvents();
            GL11.glClearColor(0.1F, 0.1F, 0.1F, 1.0F);
            GL11.glClear(16384);
            GL11.glBegin(4);
            GL11.glVertex2f(this.triangle.getPointA().getX(), this.triangle.getPointA().getY());
            GL11.glVertex2f(this.triangle.getPointB().getX(), this.triangle.getPointB().getY());
            GL11.glVertex2f(this.triangle.getPointC().getX(), this.triangle.getPointC().getY());
            GL11.glEnd();
            GLFW.glfwSwapBuffers(this.glfwWindow);
        }

    }
}
