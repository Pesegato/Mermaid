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
public class Move3DZPanelControl extends AbstractControl {

    float ts = -1;
    float totalTranslation = 0;
    float amount;
    float speed;
    float initialDelay;
    boolean negative = false;

    public Move3DZPanelControl(float amount, float speed, float initialDelay) {
        if (amount < 0) {
            negative = true;
            this.amount = -amount;
        } else {
            this.amount = amount;
        }
        this.speed = speed;
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
        float currentTranslation = speed * tpf;
        totalTranslation += currentTranslation;
        if (totalTranslation > amount) {
            setEnabled(false);
            currentTranslation -= (totalTranslation - amount);
        }
        if (negative) {
            getSpatial().move(0, 0, -currentTranslation);
        } else {
            getSpatial().move(0, 0, currentTranslation);
        }
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
    }

}
