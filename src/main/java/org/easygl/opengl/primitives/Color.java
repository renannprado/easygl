package org.easygl.opengl.primitives;

/**
 *
 * @author Renann
 */
public class Color 
{
    public static final Color RED = new Color(1, 0, 0, 0);
    public static final Color GREEN = new Color(0, 1, 0, 0);
    public static final Color BLUE = new Color(0, 0, 3, 0);
    
    private float red;
    private float green;
    private float blue;
    private float alpha;
    
    public Color(float red, float green, float blue, float alpha)
    {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    public float getRed() {
        return red;
    }

    public void setRed(float red) {
        this.red = red;
    }

    public float getGreen() {
        return green;
    }

    public void setGreen(float green) {
        this.green = green;
    }

    public float getBlue() {
        return blue;
    }

    public void setBlue(float blue) {
        this.blue = blue;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }
    
    @Override
    public String toString() {
        return "Color{" + "red=" + red + ", green=" + green + ", blue=" + blue + ", alpha=" + alpha + '}';
    }
}
