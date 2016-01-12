package com.pesegato.mermaid;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.control.AbstractControl;

/**
 *
 */
public class Press3DButtonControl extends AbstractControl{

    float ts=-1;
    boolean inward=true;
    
    public Press3DButtonControl(boolean inward){
        this.inward=inward;
    }
    
    @Override
    protected void controlUpdate(float tpf) {
        if (ts==-1)
            ts=0;
        ts+=tpf;
        if (inward)
            getSpatial().move(0,0,-8*tpf);
        else
            getSpatial().move(0,0,8*tpf);
        if (ts>0.2)
            setEnabled(false);
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
