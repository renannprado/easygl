/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.easygl.opengl.primitives;

import org.lwjgl.opengl.GL15;

/**
 *
 * @author Renann
 */
public enum DataUsageType 
{
    STATIC_DRAW(GL15.GL_STATIC_DRAW);
    
    private final int typeValue;
    
    private DataUsageType(int typeValue)
    {
        this.typeValue = typeValue;
    }

    public int getTypeValue() {
        return typeValue;
    }
}
