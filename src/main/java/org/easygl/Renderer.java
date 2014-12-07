package org.easygl;

import java.util.ArrayList;
import java.util.List;
import org.easygl.opengl.primitives.AbstractDrawableComponent;
import org.easygl.opengl.primitives.Color;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import org.lwjgl.opengl.GLContext;

/**
 *
 * @author Renann
 */
public class Renderer 
{
    private Color clearColor;
    private GLContext glContext;
    private List<AbstractDrawableComponent> objectsToDraw;
    
    public Renderer(Color clearColor)
    {
        if (clearColor == null)
            throw new NullPointerException("clearColor");
        
        this.clearColor = clearColor;
        this.objectsToDraw = new ArrayList<>();
    }
    
    public void createGLContext()
    {
        this.glContext = GLContext.createFromCurrent();
    }
    
    public void beforeRender()
    {
        GL11.glClearColor(this.clearColor.getRed(), this.clearColor.getGreen(), this.clearColor.getBlue(), this.clearColor.getAlpha());
        GL11.glClear(GL_COLOR_BUFFER_BIT);
    }
    
    public void render()
    {
        for (int i = 0; i < this.objectsToDraw.size(); i++)
            this.objectsToDraw.get(i).draw();
    }
    
    public GLContext getGLContext()
    {
        return this.glContext;
    }
    
    public void addNewDrawableComponent(AbstractDrawableComponent newComponent)
    {
        this.objectsToDraw.add(newComponent);
    }
}
