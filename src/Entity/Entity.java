package Entity;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glUniform1f;
import static org.lwjgl.opengl.GL20.glUniform4f;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

import shaders.Shader;

public class Entity {
	
	private int VAO;
	private int vertexCount;
	Shader program;
	
	public Entity(Shader program1){
		program = program1;
		InjectModelToGL();
	}
	
	public void Render(float scale, float greenValue){
		glBindVertexArray(VAO);
		program.Use();
	    	int vertexColorLocation = glGetUniformLocation(program.Program, "ourColor");
	    	glUniform4f(vertexColorLocation, 0.0f, greenValue, 0.0f, 1.0f);

	    	int scaleID = glGetUniformLocation(program.Program, "scale");
	    	glUniform1f(scaleID, scale);

	    	glDrawArrays(GL_TRIANGLES, 0, 3);
	    glBindVertexArray(0);
	}
	
	private void InjectModelToGL(){
		int floatSize = 4;

		FloatBuffer vertices = BufferUtils.createFloatBuffer(3 * 6);
		vertices.put(-0.6f).put(-0.4f).put(0f).put(1f).put(0f).put(0f);
		vertices.put(0.6f).put(-0.4f).put(0f).put(0f).put(1f).put(0f);
		vertices.put(0f).put(0.6f).put(0f).put(0f).put(0f).put(1f);
		vertices.flip();
		
		VAO = glGenVertexArrays();
		glBindVertexArray(VAO);
			int vbo = glGenBuffers();
			glBindBuffer(GL_ARRAY_BUFFER, vbo);
			glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);			
			glEnableVertexAttribArray(0);
			glVertexAttribPointer(0, 3, GL_FLOAT, false, 6 * floatSize, 0);
		glBindVertexArray(0);
	}

}
