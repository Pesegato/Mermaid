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
public class Rotate3DXPanelControl extends AbstractControl {

    float ts = -1;
    boolean versoSu = true;
    float totalRotation = 0;
    float amount = (float) (Math.PI / 2.5);
    float initialDelay;

    public Rotate3DXPanelControl(boolean versoSu, float amount, float initialDelay) {
        this.versoSu = versoSu;
        this.amount = amount;
        this.initialDelay=initialDelay;
    }

    @Override
    protected void controlUpdate(float tpf) {
        if (ts == -1) {
            ts = 0;
        }
        if (initialDelay>0){
            initialDelay-=tpf;
            return;
        }
        ts += tpf;
        float currentRotation = 8 * tpf;
        totalRotation += currentRotation;
        if (totalRotation > amount) {
            setEnabled(false);
            currentRotation -= (totalRotation - amount);
        }
        if (versoSu) {
            getSpatial().rotate(-currentRotation, 0, 0);
        } else {
            getSpatial().rotate(currentRotation, 0, 0);
        }
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
    }

}
