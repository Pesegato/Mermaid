/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pesegato.mermaid;

import com.jme3.input.MouseInput;
import com.jme3.input.event.MouseButtonEvent;
import com.jme3.input.event.MouseMotionEvent;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Spatial;
import com.pesegato.goldmonkey.GM;
import com.simsilica.lemur.event.DefaultMouseListener;
import com.simsilica.lemur.event.MouseEventControl;

/**
 *
 */
public class PButton2 extends Geometry{

    PrismAction action;
    
    public PButton2(Mesh m, Material mat, PrismAction action) {
        super("ps",m);
        setMaterial(mat);
        setShadowMode(RenderQueue.ShadowMode.CastAndReceive);
        MouseEventControl.addListenersToSpatial(this, new PBMouseListener());
        this.action=action;
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
