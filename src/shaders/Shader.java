package shaders;

import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.opengl.GL20.GL_COMPILE_STATUS;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_LINK_STATUS;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glCompileShader;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glGetProgramInfoLog;
import static org.lwjgl.opengl.GL20.glGetProgrami;
import static org.lwjgl.opengl.GL20.glGetShaderInfoLog;
import static org.lwjgl.opengl.GL20.glGetShaderi;
import static org.lwjgl.opengl.GL20.glLinkProgram;
import static org.lwjgl.opengl.GL20.glShaderSource;
import static org.lwjgl.opengl.GL30.glBindFragDataLocation;
import static org.lwjgl.opengl.GL20.glUseProgram;

import java.io.BufferedReader;
import java.io.FileReader;

public class Shader {

	public int Program;

	
	public Shader(String VERTEX_FILE, String FRAGMENT_FILE){
		
		StringBuilder vertexSource = loadShaderSourceFile(VERTEX_FILE);
				
		int vertexShaderID = glCreateShader(GL_VERTEX_SHADER);
		glShaderSource(vertexShaderID, vertexSource);
		glCompileShader(vertexShaderID);
		
		int status = glGetShaderi(vertexShaderID, GL_COMPILE_STATUS);
		System.out.println("VertexShader = " + status);
		if (status != GL_TRUE) {
		    throw new RuntimeException(glGetShaderInfoLog(vertexShaderID));
		}
		
		StringBuilder fragmentSource = loadShaderSourceFile(FRAGMENT_FILE);
		
		int fragmentShaderID = glCreateShader(GL_FRAGMENT_SHADER);
		glShaderSource(fragmentShaderID, fragmentSource);
		glCompileShader(fragmentShaderID);
		System.out.println("FragmentShader = " + status);
		status = glGetShaderi(fragmentShaderID, GL_COMPILE_STATUS);
		if (status != GL_TRUE) {
		    throw new RuntimeException(glGetShaderInfoLog(vertexShaderID));
		}
		
		Program = glCreateProgram();
		glAttachShader(Program, vertexShaderID);
		glAttachShader(Program, fragmentShaderID);
		//glBindFragDataLocation(Program, 0, "fragColor");
		glLinkProgram(Program);
		
		status = glGetProgrami(Program, GL_LINK_STATUS);
		System.out.println("Shader program status = " + status);
		if (status != GL_TRUE) {
		    throw new RuntimeException(glGetProgramInfoLog(Program));
		}
	}
	
	public void Use(){
		glUseProgram(this.Program);
	}
	
	private static StringBuilder loadShaderSourceFile(String file){
		StringBuilder shaderSource = new StringBuilder();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while((line = reader.readLine())!=null){
				shaderSource.append(line).append("//\n");
			}
			reader.close();
		} catch (Exception e) {
			System.err.println("Couldn't read shader file");
			e.printStackTrace();
			System.exit(-1);
			// TODO: handle exception
		}

		return shaderSource;
	}
	
}
