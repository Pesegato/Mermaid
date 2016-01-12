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
public class PSwitch extends Node{

    int tabNumber;
    boolean pressed;
    public PSwitch(String label, float x, int y, Material mat, int tabNumber, PRadioGroup radio) {
        super("ps");
        Geometry box=new Geometry("ds",new Box(.7f, 0.4f, 1));
        attachChild(box);
        this.tabNumber=tabNumber;

        BitmapText text=new BitmapText(MermaidAppState.font1);
        //font.getPage(0).setFloat("AlphaDiscardThreshold",0.1f);
        text.setText(label);
        text.setQueueBucket(RenderQueue.Bucket.Transparent);
        text.scale(0.004f);
        text.setLocalTranslation(-.6f,0.4f,1.1f);
        attachChild(text);
        
        box.setMaterial(mat);
        setShadowMode(RenderQueue.ShadowMode.CastAndReceive);
        //geom.setLocalTranslation(-6 + i * 3, 4, -4);
        setLocalTranslation(x, y, -4);
        MouseEventControl.addListenersToSpatial(this, new PSwitchMouseListener());
        radio.addSwitch(this);

        if (tabNumber==0){
            move(0,0,-1.6f);
            pressed=true;
        }
    }
    
    PRadioGroup group;
    void setRadioGroup(PRadioGroup group){
        this.group=group;
    }
    
    void press(){
        if (!pressed){
            addControl(new Press3DButtonControl(true));
            pressed=true;
            group.pressed(tabNumber);
        }
    }
    void release(){
        if (pressed){
            addControl(new Press3DButtonControl(false));
            pressed=false;
        }
    }

    class PSwitchMouseListener extends DefaultMouseListener {

        @Override
        protected void click(MouseButtonEvent event, Spatial target, Spatial capture) {
            Material m = ((Geometry)((Node) target).getChild(0)).getMaterial();
            m.setColor("Diffuse", MermaidAppState.getColorC());
            m.setColor("Ambient", MermaidAppState.getColorC());
            if (event.getButtonIndex() == MouseInput.BUTTON_LEFT) {
                press();
            }
                /*roller.addControl(new Press3DButtonControl(false));
                target.addControl(new Press3DButtonControl(true));
            } else {
                sm.getState(SettingsPanelState.class).press();
                target.addControl(new Rotate3DPanelControl(true, (float) (Math.PI / 2.5)));
                roller.addControl(new Rotate3DPanelControl(true, (float) (Math.PI / 2.5)));
            }*/
        }

        @Override
        public void mouseEntered(MouseMotionEvent event, Spatial target, Spatial capture) {
            Material m = ((Geometry)((Node) target).getChild(0)).getMaterial();
            m.setColor("Diffuse", MermaidAppState.getColorC());
            m.setColor("Ambient", MermaidAppState.getColorC());
        }

        @Override
        public void mouseExited(MouseMotionEvent event, Spatial target, Spatial capture) {
            Material m = ((Geometry)((Node) target).getChild(0)).getMaterial();
            m.setColor("Diffuse", MermaidAppState.getColorA());
            m.setColor("Ambient", MermaidAppState.getColorA());
        }
    }
}
