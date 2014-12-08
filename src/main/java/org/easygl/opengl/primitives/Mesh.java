package org.easygl.opengl.primitives;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

/**
 *
 * @author Renann
 */
public class Mesh extends AbstractDrawableComponent
{
    private final VertexArrayObject vertexArrayObject;
    private final MeshType meshType;
    
    public Mesh(final VertexArrayObject vertexArrayObject, MeshType meshType)
    {
        this.vertexArrayObject = vertexArrayObject;
        this.meshType = meshType;
    }
    
    public VertexArrayObject getVertexArrayObject()
    {
        return this.vertexArrayObject;
    }

    @Override
    protected void beforeDraw() 
    {
        this.vertexArrayObject.bind();
    }

    @Override
    protected void drawImpl() 
    {
        for (int i = 0; i < this.vertexArrayObject.vertexBufferObejctList.size(); i++)
        {
            AbstractVertexBufferObject<?> vbo = this.vertexArrayObject.vertexBufferObejctList.get(i);
            GL20.glEnableVertexAttribArray(vbo.getAttributeID());
            GL11.glDrawArrays(this.meshType.getTypeValue(), 0, vbo.getDataElementsQuantity());
            GL20.glDisableVertexAttribArray(vbo.getAttributeID());
        }
    }

    @Override
    protected void afterDraw() 
    {
        this.vertexArrayObject.unbind();
    }
}