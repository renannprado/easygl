package org.easygl.opengl.primitives;

import java.nio.Buffer;
import org.easygl.opengl.primitives.OpenGLUtils;
import org.lwjgl.opengl.GL15;

/**
 *
 * @author Renann
 * @param <T>
 */
public abstract class AbstractVertexBufferObject<T extends Buffer>
{
    protected final int id;
    protected int attributeID;
    protected final VertexBufferType vertexBufferType;
    protected final VertexBufferDataType vertexDataType;
    protected final DataUsageType dataUsateType;
    protected final T dataBuffer;
    
    public AbstractVertexBufferObject(VertexBufferType vertexBufferType, DataUsageType dataUsageType, T dataBuffer, VertexBufferDataType vertexDataType)
    {
        this.vertexBufferType = vertexBufferType;
        this.vertexDataType = vertexDataType;
        this.dataUsateType = dataUsageType;
        this.id = OpenGLUtils.createVertexBufferObject();
        this.dataBuffer = dataBuffer;
        this.attributeID = -1;
    }
    
    protected abstract void storeData();
    public abstract int getDataElementsQuantity();
    
    public int getId() 
    {
        return id;
    }

    public VertexBufferType getVertexBufferType() 
    {
        return vertexBufferType;
    }
    
    public DataUsageType getDrawType()
    {
        return this.dataUsateType;
    }
    
    public T getDataBuffer()
    {
        return this.dataBuffer;
    }
    
    protected void bind()
    {
        GL15.glBindBuffer(this.vertexBufferType.getTypeValue(), this.id);
    }
    
    protected void unbind()
    {
        GL15.glBindBuffer(this.vertexBufferType.getTypeValue(), 0);
    }

    protected void setAttributeID(int attributeID) 
    {
        this.attributeID = attributeID;
    }

    public int getAttributeID() {
        return attributeID;
    }

    public VertexBufferDataType getVertexDataType() {
        return vertexDataType;
    }
}
