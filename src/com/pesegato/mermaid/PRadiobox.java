/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pesegato.mermaid;

import com.jme3.font.BitmapText;
import com.jme3.input.event.MouseButtonEvent;
import com.jme3.input.event.MouseMotionEvent;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import com.simsilica.lemur.CheckboxModel;
import com.simsilica.lemur.DefaultCheckboxModel;
import com.simsilica.lemur.core.VersionedReference;
import com.simsilica.lemur.event.DefaultMouseListener;
import com.simsilica.lemur.event.MouseEventControl;

/**
 *
 * Triangolo invece di quadrato per radio checkbox
 */
public class PRadiobox extends Node {

    Geometry g;
    private CheckboxModel model=new DefaultCheckboxModel();
    private VersionedReference<Boolean> state;
    
    public PRadiobox(String label, int x, float y) {
        g=new Geometry("",new Prism(.1f, .1f, 3));
        g.setMaterial(MermaidAppState.matMermaid);
        g.setShadowMode(RenderQueue.ShadowMode.CastAndReceive);
        attachChild(g);
        //geom.setLocalTranslation(-6 + i * 3, 4, -4);
        setLocalTranslation(x, y, 0);
        BitmapText text=new BitmapText(MermaidAppState.font1);
        //font.getPage(0).setFloat("AlphaDiscardThreshold",0.1f);
        text.setText(label);
        text.setQueueBucket(RenderQueue.Bucket.Transparent);
        text.scale(0.0025f);
        text.setLocalTranslation(0.4f,0.2f,.1f);
        attachChild(text);
        MouseEventControl.addListenersToSpatial(this, new PBMouseListener());
        addControl(new PCheckboxControl());
        
        this.state = model.createReference();
    }
    
    public CheckboxModel getModel() {
        return model;
    }
    
    public void setChecked( boolean b ) {
        model.setChecked(b);
    }

    public boolean isChecked() {
        return model.isChecked();
    }
    
    class PBMouseListener extends DefaultMouseListener {

        @Override
        protected void click(MouseButtonEvent event, Spatial target, Spatial capture) {
            setChecked(!isChecked());
            Material m = ((Geometry)((Node) target).getChild(0)).getMaterial();
            m.setColor("Diffuse", ColorRGBA.Blue.clone());
            m.setColor("Ambient", ColorRGBA.Blue.clone());
        }

        @Override
        public void mouseEntered(MouseMotionEvent event, Spatial target, Spatial capture) {
            Material m = ((Geometry)((Node) target).getChild(0)).getMaterial();
            m.setColor("Diffuse", ColorRGBA.Blue.clone());
            m.setColor("Ambient", ColorRGBA.Blue.clone());
        }

        @Override
        public void mouseExited(MouseMotionEvent event, Spatial target, Spatial capture) {
            Material m = ((Geometry)((Node) target).getChild(0)).getMaterial();
            m.setColor("Diffuse", ColorRGBA.White.clone());
            m.setColor("Ambient", ColorRGBA.White.clone());
        }
    }
    
    class PCheckboxControl extends AbstractControl{

        @Override
        protected void controlUpdate(float tpf) {
            if (isChecked())
                g.rotate(0, 0, 15*tpf);
        }

        @Override
        protected void controlRender(RenderManager rm, ViewPort vp) {
        }
    }
}
