package org.easygl.opengl.shaders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

/**
 *
 * @author Renann
 */
public class ShaderProgram 
{
    private final List<Shader> shaderList;
    private final int programID;
    
    public ShaderProgram()
    {
        this.programID = GL20.glCreateProgram();        
        this.shaderList = new ArrayList<>();
    }
    
    public void bind()
    {
        GL20.glUseProgram(this.programID);
    }
    
    public void unbind()
    {    
        GL20.glUseProgram(0);
    }
    
    /**
     * Add and attache the shaders into the <code>ShaderProgram</code> 
     * @param newShader
     */
    public void addShader(Shader... newShader)
    {        
        this.shaderList.addAll(Arrays.asList(newShader));
        this.compileAndLink();
    }
    
    private void compileAndLink()
    {        
        //attach all the shaders to the program
        for (int i = 0; i < this.shaderList.size(); i++)
            GL20.glAttachShader(this.programID, this.shaderList.get(i).getShaderObjectID());
        
        //link the program
        GL20.glLinkProgram(this.programID);
        
        //check for errors
        if (GL20.glGetProgrami(this.programID, GL20.GL_LINK_STATUS) == GL11.GL_FALSE)
        {
            String errorMessage = GL20.glGetProgramInfoLog(this.programID);
            throw new ShaderCreationException(errorMessage);
        }
        
        //validate the program
        GL20.glValidateProgram(this.programID);
        
        //check for errors
        if (GL20.glGetProgrami(this.programID, GL20.GL_LINK_STATUS) == GL11.GL_FALSE)
        {
            String errorMessage = GL20.glGetProgramInfoLog(this.programID);
            throw new ShaderCreationException(errorMessage);
        }
    }
}
