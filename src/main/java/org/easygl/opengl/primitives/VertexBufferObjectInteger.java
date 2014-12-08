package org.easygl.opengl.primitives;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

/**
 *
 * @author Renann
 */
public class VertexBufferObjectInteger extends AbstractVertexBufferObject<IntBuffer>
{
    private final int dataElementsQuantity;
    
    public VertexBufferObjectInteger(VertexBufferType vertexBufferType, DataUsageType drawType, IntBuffer dataBuffer, int floatsPerVertex) 
    {
        super(vertexBufferType, drawType, dataBuffer, VertexBufferDataType.INTEGER);
        this.dataElementsQuantity = dataBuffer.remaining();
    }
    
    @Override
    protected void storeData() 
    {
        this.bind();
        GL15.glBufferData(this.vertexBufferType.getTypeValue(), this.dataBuffer, this.dataUsateType.getTypeValue());
        this.unbind();
    }

    @Override
    public int getDataElementsQuantity() 
    {
        return this.dataElementsQuantity;
    }
}
