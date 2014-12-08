package org.easygl.opengl.primitives;

import java.nio.FloatBuffer;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

/**
 *
 * @author Renann
 */
public class VertexBufferObjectFloat extends AbstractVertexBufferObject<FloatBuffer>
{
    private final int floatsPerVertex;
    private final int dataFloatQuantity;
    private final int vertexQuantity;
    
    public VertexBufferObjectFloat(VertexBufferType vertexBufferType, DataUsageType drawType, FloatBuffer dataBuffer, int floatsPerVertex) 
    {
        super(vertexBufferType, drawType, dataBuffer, VertexBufferDataType.FLOAT);
        this.floatsPerVertex = floatsPerVertex;
        this.dataFloatQuantity = dataBuffer.remaining();
        
        if (this.dataFloatQuantity % this.floatsPerVertex != 0)
            throw new RuntimeException("The data float quantity is not divisible by floats per vertex. Probably the buffer is not flipped.");
     
        this.vertexQuantity = this.dataFloatQuantity / this.floatsPerVertex;
    }
    
    @Override
    protected void storeData() 
    {
        this.bind();
        GL15.glBufferData(this.vertexBufferType.getTypeValue(), this.dataBuffer, this.dataUsateType.getTypeValue());
        GL20.glVertexAttribPointer(this.attributeID, this.floatsPerVertex, this.vertexDataType.getTypeValue(), false, 0, 0);
        this.unbind();
    }

    @Override
    public int getDataElementsQuantity() 
    {
        return this.vertexQuantity;
    }
}
