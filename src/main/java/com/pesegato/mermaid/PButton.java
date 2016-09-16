/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pesegato.mermaid;

import com.jme3.font.BitmapText;
import com.jme3.input.MouseInput;
import com.jme3.input.event.MouseButtonEvent;
import com.jme3.input.event.MouseMotionEvent;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.pesegato.goldmonkey.GM;
import com.simsilica.lemur.event.DefaultMouseListener;
import com.simsilica.lemur.event.MouseEventControl;

/**
 *
 */
public class PButton extends Node{

    PrismAction action;
    
    public PButton(String label, int x, int y, PrismAction action) {
        Geometry geometry=new Geometry("ps",new Box(1, 0.5f, 1));
        geometry.setShadowMode(RenderQueue.ShadowMode.CastAndReceive);
        //geom.setLocalTranslation(-6 + i * 3, 4, -4);
        setLocalTranslation(x, y, -4);
        MouseEventControl.addListenersToSpatial(geometry, new PBMouseListener());
        this.action=action;
        
        BitmapText text=new BitmapText(MermaidAppState.font1);
        //font.getPage(0).setFloat("AlphaDiscardThreshold",0.1f);
        text.setText(label);
        text.setQueueBucket(RenderQueue.Bucket.Transparent);
        text.scale(0.004f);
        text.setLocalTranslation(-.6f,0.4f,1.1f);
        attachChild(text);
        attachChild(geometry);
    }
    
    public void setMaterial(Material mat){
        getChild(1).setMaterial(mat);
    }
    
    class PBMouseListener extends DefaultMouseListener {

        @Override
        protected void click(MouseButtonEvent event, Spatial target, Spatial capture) {
            Material m = ((Geometry) target).getMaterial();
            m.setColor("Diffuse", MermaidAppState.getColorC());
            m.setColor("Ambient", MermaidAppState.getColorC());
            if (event.getButtonIndex() == MouseInput.BUTTON_LEFT) {
                action.action();
            }
        }

        @Override
        public void mouseEntered(MouseMotionEvent event, Spatial target, Spatial capture) {
            Material m = ((Geometry) target).getMaterial();
            m.setColor("Diffuse", MermaidAppState.getColorC());
            m.setColor("Ambient", MermaidAppState.getColorC());
        }

        @Override
        public void mouseExited(MouseMotionEvent event, Spatial target, Spatial capture) {
            Material m = ((Geometry) target).getMaterial();
            m.setColor("Diffuse", MermaidAppState.getColorA());
            m.setColor("Ambient", MermaidAppState.getColorA());
        }
    }
}
