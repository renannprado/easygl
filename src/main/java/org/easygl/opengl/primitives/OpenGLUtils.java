package org.easygl.opengl.primitives;

import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

/**
 *
 * @author Renann
 */
public class OpenGLUtils 
{
    /**
     * 
     * @return the vertex array object ID 
     */
    public static int createVertexArrayObject()
    {
        return GL30.glGenVertexArrays();
    }
    
    public static int createVertexBufferObject()
    {
        return GL15.glGenBuffers();
    }
}
