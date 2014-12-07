package org.easygl.opengl.primitives;

import org.lwjgl.opengl.GL11;

/**
 *
 * @author Renann
 */
public enum MeshType 
{
    B_TRIANGLES(GL11.GL_TRIANGLES),
    B_LINES(GL11.GL_LINES),
    P_LINE(GL11.GL_LINE),
    B_QUADS(GL11.GL_QUADS);
    
    private final int typeValue;
    
    private MeshType(int typeValue)
    {
        this.typeValue = typeValue;
    }
    
    public int getTypeValue()
    {
        return this.typeValue;
    }
}
