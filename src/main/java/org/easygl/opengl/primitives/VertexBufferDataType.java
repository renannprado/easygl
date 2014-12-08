package org.easygl.opengl.primitives;

import org.lwjgl.opengl.GL11;

/**
 *
 * @author Renann
 */
public enum VertexBufferDataType 
{
    FLOAT(GL11.GL_FLOAT),
    INTEGER(GL11.GL_INT);
    
    private final int typeValue;
    
    private VertexBufferDataType(int typeValue)
    {
        this.typeValue = typeValue;
    }
    
    public int getTypeValue()
    {
        return this.typeValue;
    }
}
