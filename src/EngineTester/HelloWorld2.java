package EngineTester;


import org.joml.Matrix4f;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import org.lwjgl.*;
//
import org.lwjgl.opengl.*;

import Entity.Entity;
//
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL20.*;

import shaders.Shader;



public class HelloWorld2 {

	private GLWindow window;
	private KeyboardHandler kbd;
	private Shader shaderProgram, shaderProgram2;
	private Entity triangle;
	ArrayList <Entity> triangles;
	private int vao, vao2;
	private double scale;
	boolean prog_flag;
	long space_counter;
	Matrix4f transformationMatrix;

	private void Run(){
		Init();
		Loop();
	}
	
	private void Init(){

		space_counter = 0;
		scale = 0.0;
		triangles = new ArrayList<Entity>();
		window = new GLWindow();
		shaderProgram = new Shader("src/shaders/vertexShader.txt", "src/shaders/fragmentShader.txt");
		shaderProgram2 = new Shader("src/shaders/vertexShader2.txt", "src/shaders/fragmentShader.txt");

		triangles.add(new Entity(shaderProgram));
		triangles.add(new Entity(shaderProgram2));
		//shaderProgram = new Shader("src/shaders/vertexShader2.txt", "src/shaders/fragmentShader.txt");	
		//triangles.add(new Entity(shaderProgram));
	}
	
	
	
	private void Loop(){
		
				while( !glfwWindowShouldClose(window.getWindow()) ){
					transformationMatrix = new Matrix4f();

					glClear(GL_COLOR_BUFFER_BIT);
					// Update the uniform color
					double timeValue = glfwGetTime();
					double greenValue = (Math.sin(timeValue) / 2) + 0.5;
					transformationMatrix.translate((float)greenValue, 0.0f, 0.0f);
					scale += 0.0001;

					for (Entity triangle : triangles){
						triangle.Render((float)scale, (float)greenValue);
					}
					glfwSwapBuffers(window.getWindow());
					glfwPollEvents();
					update();
			}

	}
	public void update(){
		if(KeyboardHandler.isKeyDown(GLFW_KEY_SPACE)) {
			System.out.println("Space Key Pressed" + space_counter);
		}
		if(KeyboardHandler.isKeyDown(GLFW_KEY_UP)) {
			space_counter++;
			System.out.println("Space Key Pressed" + space_counter);
		}
	}

	public static void main(String[] args) {
		new HelloWorld2().Run();
	}

}
