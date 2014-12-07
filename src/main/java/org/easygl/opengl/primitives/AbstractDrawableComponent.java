package org.easygl.opengl.primitives;

/**
 *
 * @author Renann
 */
public abstract class AbstractDrawableComponent 
{
    public final void draw()
    {
        this.beforeDraw();
        this.drawImpl();
        this.afterDraw();
    }
    
    protected abstract void beforeDraw();
    protected abstract void drawImpl();
    protected abstract void afterDraw();
}
