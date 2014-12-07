package org.easygl.opengl.primitives;

import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;

/**
 *
 * @author Renann
 */
public enum VertexBufferType 
{
    ARRAY_BUFFER(GL_ARRAY_BUFFER);
    
    private final int typeValue;
    
    private VertexBufferType(int typeValue)
    {
        this.typeValue = typeValue;
    }
    
    public int getTypeValue()
    {
        return this.typeValue;
    }
}
