package component;

import java.util.Objects;
import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWErrorCallbackI;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {
    int width = 800;
    int height = 640;
    String title = "Teste";
    private long glfwWindow;
    private static Window window = null;
    private final Triangle triangle;

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
        System.out.println("Init GLFW");
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
            GLFW.glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
            GLFW.glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
            GLFW.glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);
            this.glfwWindow = GLFW.glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);
            if (this.glfwWindow == NULL) {
                throw new IllegalStateException("Failed to create the glfw window");
            } else {
//                make the context current
                GLFW.glfwMakeContextCurrent(this.glfwWindow);
//                enable v-sync
                GLFW.glfwSwapInterval(1);

//                show the window
                GLFW.glfwShowWindow(this.glfwWindow);
                GL.createCapabilities();
            }
        }
    }

    public void loop() {
        while(!GLFW.glfwWindowShouldClose(this.glfwWindow)) {
            GLFW.glfwPollEvents();
            GL11.glClearColor(0.1F, 0.1F, 0.1F, 1.0F);
            GL11.glClear(GL_COLOR_BUFFER_BIT);

            GL11.glBegin(GL_TRIANGLES);

            GL11.glColor3f(1.0F, 0.0F, 0.0F);
            GL11.glVertex2f(this.triangle.getPointA().getX(), this.triangle.getPointA().getY());

            GL11.glColor3f(0.0F, 1.0F, 0.0F);
            GL11.glVertex2f(this.triangle.getPointB().getX(), this.triangle.getPointB().getY());

            GL11.glColor3f(0.0F, 0.0F, 1.0F);
            GL11.glVertex2f(this.triangle.getPointC().getX(), this.triangle.getPointC().getY());

            GL11.glEnd();
            GLFW.glfwSwapBuffers(this.glfwWindow);
        }

    }
}
