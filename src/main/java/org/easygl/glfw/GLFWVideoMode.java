package org.easygl.glfw;

import java.nio.ByteBuffer;
import org.lwjgl.glfw.GLFWvidmode;

/**
 *
 * @author Renann
 */
public class GLFWVideoMode 
{
    public final int height;
    public final int width;
    public final int redBits;
    public final int greenBits;
    public final int blueBits;
    public final int refreshRate;
    
    public GLFWVideoMode(ByteBuffer videoMode)
    {
        this.width = GLFWvidmode.width(videoMode);
        this.height = GLFWvidmode.height(videoMode);
        this.redBits = GLFWvidmode.redBits(videoMode);
        this.greenBits = GLFWvidmode.greenBits(videoMode);
        this.blueBits = GLFWvidmode.blueBits(videoMode);
        this.refreshRate = GLFWvidmode.refreshRate(videoMode);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getRedBits() {
        return redBits;
    }

    public int getGreenBits() {
        return greenBits;
    }

    public int getBlueBits() {
        return blueBits;
    }

    public int getRefreshRate() {
        return refreshRate;
    }
}
