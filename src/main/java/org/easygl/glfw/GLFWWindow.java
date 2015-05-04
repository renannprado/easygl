package org.easygl.glfw;

import com.sun.scenario.effect.Effect;
import org.lwjgl.glfw.*;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDefaultWindowHints;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;
import static org.lwjgl.glfw.GLFW.glfwSetWindowSize;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.GL_TRUE;
import org.lwjgl.opengl.GLContext;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 *
 * @author Renann
 */
public class GLFWWindow
{
    /**
     * A simple call back that just checks wheter the user has pressed ESC and if so, it calls glfwSetWindowShouldClose
     */
    public static final GLFWKeyCallback defaultGLFWKeyCallback = new GLFWKeyCallback() 
    {
        @Override
        public void invoke(long window, int key, int scancode, int action, int mods) 
        {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                glfwSetWindowShouldClose(window, GL_TRUE);
        }
    };
    
    public static final GLFWErrorCallback defaultGLFWErrorCallback = new GLFWErrorCallback() {

        @Override
        public void invoke(int error, long description) 
        {
            System.err.println(org.lwjgl.system.MemoryUtil.memDecodeUTF8(description));
        }
    };
    
    /**
     * The window's memory address, window's handle or window's pointer
     */
    private long windowMemoryAddress;
    private String windowTitle;
    private boolean resizeable;
    private boolean visible;
    private int windowWidth;
    private int windowHeight;
    private boolean vSync;
    private long monitorMemoryAddress;
    private GLFWKeyCallback glfwKeyCallbackInstance;
    
    public GLFWWindow(int windowWidth, int windowHeight, String windowTitle, boolean isResizeable, boolean isFullScreen, boolean vSync)
    {
        this.windowTitle = windowTitle;
        this.resizeable = isResizeable;
        this.windowHeight = windowHeight;
        this.windowWidth = windowWidth;
        this.vSync = vSync; 
        this.glfwKeyCallbackInstance = null;
        
        //set the default callback for error handling in GLFW
        glfwSetErrorCallback(org.lwjgl.glfw.Callbacks.errorCallbackPrint());
        
        //init the GLFW library
        if (glfwInit() != GL_TRUE) 
            throw new IllegalStateException("Unable to initialize GLFW");
        
        //set the monitor 
        this.monitorMemoryAddress = glfwGetPrimaryMonitor();
        
        //set default values to all windows hints
        glfwDefaultWindowHints();
        
        this.setResizeable(resizeable);
        
        if (isFullScreen)
            this.windowMemoryAddress = glfwCreateWindow(this.windowWidth, this.windowHeight, this.windowTitle, this.monitorMemoryAddress, NULL); //creates a full screen display
        else
            this.windowMemoryAddress = glfwCreateWindow(this.windowWidth, this.windowHeight, this.windowTitle, NULL, NULL); //creates the window in windowed mode
        
        if (this.windowMemoryAddress == NULL) 
            throw new IllegalStateException("Failed to create window");
        
        //make this the current context
        glfwMakeContextCurrent(this.windowMemoryAddress);
        
        //v-sync
        if (this.vSync)
            glfwSwapInterval(1); // How many draws to swap the buffer
    }
    
    public final void setVisibility(boolean visible)
    {
        //set the window visible hint
        glfwWindowHint(GLFW_VISIBLE, visible ? GL_TRUE : GL_FALSE);
        this.visible = visible;
        if (this.visible) 
            glfwShowWindow(this.windowMemoryAddress);
    }
    
    public final void setResizeable(boolean resizeable)
    {
        //set the resizeable hint
        glfwWindowHint(GLFW_RESIZABLE, resizeable ? GL_TRUE : GL_FALSE);
        this.resizeable = resizeable;
    }
    
    public final void setGLFWKeyCallback(GLFWKeyCallback glfwKeyCallbackInstance)
    {
        if (glfwKeyCallbackInstance == null)
            throw new NullPointerException("windowCallbackAdapter");
        
        this.glfwKeyCallbackInstance = glfwKeyCallbackInstance;
        
        GLFW.glfwSetKeyCallback(this.windowMemoryAddress, this.glfwKeyCallbackInstance);
    }

    public String getWindowTitle() {
        return windowTitle;
    }

    public void setWindowTitle(String windowTitle) {
        this.windowTitle = windowTitle;
    }

    public boolean isResizeable() {
        return resizeable;
    }

    public boolean isVisible() {
        return visible;
    }
    
    public void setWindowSize(int width, int height)
    {
        this.windowWidth = width;
        this.windowHeight = height;
        glfwSetWindowSize(this.windowMemoryAddress, width, height);
    }
    
    public boolean isCloseRequested()
    {
        return glfwWindowShouldClose(this.windowMemoryAddress) == GL_TRUE;
    }
    
    public void destroyWindow()
    {
        glfwDestroyWindow(this.windowMemoryAddress);
        this.windowMemoryAddress = NULL;
        this.monitorMemoryAddress = NULL;
    }
    
    public void destroyCursor()
    {
        throw new UnsupportedOperationException("Method not implemented.");
    }
    
    public GLFWVideoMode getVideoMode()
    {
        return new GLFWVideoMode(glfwGetVideoMode(this.monitorMemoryAddress));
    }
    
    public static void destroyDisplays()
    {
        glfwTerminate();
    }
    
    public void swapBuffers()
    {
        glfwSwapBuffers(this.windowMemoryAddress);
    }
    
    public static void poolEvents()
    {
        glfwPollEvents();
    }
    
    public GLContext createContextFromCurrent()
    {
        glfwMakeContextCurrent(this.windowMemoryAddress);
        return GLContext.createFromCurrent();
    }
}