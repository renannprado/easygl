package org.easygl.opengl.primitives;

import org.lwjgl.opengl.GL15;

/**
 *
 * @author Renann
 */
public enum VertexBufferType 
{
    ARRAY_BUFFER(GL15.GL_ARRAY_BUFFER),
    ELEMENT_ARRAY_BUFFER(GL15.GL_ELEMENT_ARRAY_BUFFER);
    
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
