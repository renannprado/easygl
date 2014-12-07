package org.easygl.opengl.shaders;

import org.lwjgl.opengl.GL20;

/**
 *
 * @author Renann
 */
public enum ShaderType 
{
    VERTEX(GL20.GL_VERTEX_SHADER),
    FRAGMENT(GL20.GL_FRAGMENT_SHADER);
    
    private final int typeValue;
    
    private ShaderType(int typeValue)
    {
        this.typeValue = typeValue;
    }

    public int getTypeValue() {
        return typeValue;
    }
}
