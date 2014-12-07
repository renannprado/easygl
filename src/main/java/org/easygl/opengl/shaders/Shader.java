package org.easygl.opengl.shaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glShaderSource;

/**
 *
 * @author Renann
 */
public class Shader
{
    public static final String baseShaderFolder = "/shaders";
    public static final InputStream basicVertexShaderInputStream = Shader.class.getResourceAsStream(baseShaderFolder + "/basic_shader.vs");
    public static final InputStream basicFragmentShaderInputStream = Shader.class.getResourceAsStream(baseShaderFolder + "/basic_shader.fs");
    
    private final ShaderType shaderType;
    private final int shaderObjectID;
    
    private Shader(ShaderType shaderType, int shaderObjectID)
    {
        this.shaderType = shaderType;
        this.shaderObjectID = shaderObjectID;        
    }
    
    public static Shader loadShader(String filePath, ShaderType shaderType)
    {
        try 
        {
            StringBuilder shaderSourceCode = new StringBuilder();
            Files.lines(Paths.get(filePath)).parallel().forEach(s -> shaderSourceCode.append(s));

            return internal_loadShader(shaderSourceCode, shaderType);
        } 
        catch (IOException ex)
        {
            throw new RuntimeException("Failed to read the shader source code.", ex);
        }
    }
    
    public static Shader loadShader(InputStream fileInputStream, ShaderType shaderType) 
    {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream)))
        {
            StringBuilder shaderSourceCode = new StringBuilder();
            
            br.lines().parallel().forEach(s -> shaderSourceCode.append(s).append('\n'));            
            
            System.out.println(shaderSourceCode.toString());
            
            return internal_loadShader(shaderSourceCode, shaderType);
        }
        catch (IOException ex) 
        {
            throw new RuntimeException("Failed to read the shader source code.", ex);
        }
    }
    
    private static Shader internal_loadShader(StringBuilder shaderSourceCode, ShaderType shaderType)
    {
        //create new shader
        int shaderObjectID = glCreateShader(shaderType.getTypeValue());
        
        if (shaderObjectID == 0)
            throw new RuntimeException("glCreateShader method returned 0");
        
        //attach the source code to the shader
        glShaderSource(shaderObjectID, shaderSourceCode);
        
        //compile the shader
        GL20.glCompileShader(shaderObjectID);
        
        //check for errors
        if (GL20.glGetShaderi(shaderObjectID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE)
        {
            String errorMessage = GL20.glGetShaderInfoLog(shaderObjectID);
            throw new ShaderCreationException(errorMessage);
        }
        
        return new Shader(shaderType, shaderObjectID);
    }

    public ShaderType getShaderType() {
        return shaderType;
    }

    public int getShaderObjectID() {
        return shaderObjectID;
    }
}
