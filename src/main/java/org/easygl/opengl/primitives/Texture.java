package org.easygl.opengl.primitives;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author Renann
 */
public class Texture 
{
    private final ByteBuffer pixelData;
    private final boolean hasAlphaChannel;
    private final int pixelWidth;
    private final int pixelHeight;
    
    private Texture(ByteBuffer pixelData, boolean hasAlphaChannel, int pixelWidth, int pixelHeight)
    {
        this.pixelData = pixelData;
        this.hasAlphaChannel = hasAlphaChannel;
        this.pixelWidth = pixelWidth;        
        this.pixelHeight = pixelHeight;
        
        GL11.glTexImage2D();
    }
    
    public static Texture loadTexture(final String filePath)
    {
        try 
        {
            final BufferedImage texture = ImageIO.read(new File(filePath));
            final boolean hasAlphaChannel = texture.getAlphaRaster()!= null;
            final byte[] pixels = ((DataBufferByte) texture.getRaster().getDataBuffer()).getData();
            final int width = texture.getWidth();
            final int height = texture.getHeight();
            
            
//        GL11.GL_TEXTURE_2D;
//        int x = GL11.glGenTextures();
//        GL11.glBindTexture(x, x);
//        GL11.glTexImage2D();
          return null;
        }
        catch (IOException ex) 
        {
            throw new RuntimeException(String.format("Failed to load image %s.", filePath), ex);
        }
    }
    
    public
}
