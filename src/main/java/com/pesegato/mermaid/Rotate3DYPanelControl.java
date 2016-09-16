/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pesegato.mermaid;

import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.control.AbstractControl;

/**
 *
 */
public class Rotate3DYPanelControl extends AbstractControl {

    float ts = -1;
    boolean versoDx = true;
    float totalRotation = 0;
    float amount = (float) (Math.PI / 2.5);

    public Rotate3DYPanelControl(boolean versoDx, float amount) {
        this.versoDx = versoDx;
        this.amount = amount;
    }

    @Override
    protected void controlUpdate(float tpf) {
        if (ts == -1) {
            ts = 0;
        }
        ts += tpf;
        float currentRotation = 8 * tpf;
        totalRotation += currentRotation;
        if (totalRotation > amount) {
            setEnabled(false);
            currentRotation -= (totalRotation - amount);
        }
        if (versoDx) {
            getSpatial().rotate(0, -currentRotation, 0);
        } else {
            getSpatial().rotate(0, currentRotation, 0);
        }
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
