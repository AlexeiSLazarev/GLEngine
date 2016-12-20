package EngineTester;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.system.MemoryUtil.NULL;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.opengl.GL;

public class GLWindow {
	private GLFWKeyCallback keyCallback;
	private int HEIGHT = 720;
	private int WIDTH = 1280;
	private int FPS = 120;
	private String TITLE; 
	private long window;
	
	public GLWindow(){
		glfwInit();
		
		glfwDefaultWindowHints(); 
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); 
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); 
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
		glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);

		window = glfwCreateWindow(WIDTH, HEIGHT, "Window2", NULL, NULL);
		glfwMakeContextCurrent(window);
		glfwSetKeyCallback(window, keyCallback = new KeyboardHandler());
		GL.createCapabilities();
		
		glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
		glfwShowWindow(window);
	}
	
	public long getWindow(){
		return this.window;
	}
	

}
