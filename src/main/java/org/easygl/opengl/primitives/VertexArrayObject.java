/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.easygl.opengl.primitives;

import java.util.ArrayList;
import java.util.List;
import org.easygl.opengl.primitives.OpenGLUtils;
import org.lwjgl.opengl.GL30;

/**
 *
 * @author Renann
 */
public class VertexArrayObject
{
    private final int id;
    private int lastVertexBufferObjectID;
    protected final List<AbstractVertexBufferObject<?>> vertexBufferObejctList;
    
    public VertexArrayObject()
    {
        this.id = OpenGLUtils.createVertexArrayObject();
        this.lastVertexBufferObjectID = 0;
        this.vertexBufferObejctList = new ArrayList<>();
    }
    
    protected final void bind()
    {
        GL30.glBindVertexArray(this.id);
    }
    
    protected final void unbind()
    {
        GL30.glBindVertexArray(0);
    }
    
    public final void addVertexBufferObject(AbstractVertexBufferObject<?> vbo)
    {
        this.bind();
        vbo.setAttributeID(this.lastVertexBufferObjectID++);
        this.vertexBufferObejctList.add(vbo);
        vbo.storeData();
        this.unbind();
    }
    
    public int getVertexBufferObjectQuantity()
    {
        return this.vertexBufferObejctList.size();
    }
}
