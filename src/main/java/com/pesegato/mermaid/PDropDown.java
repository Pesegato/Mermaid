/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pesegato.mermaid;

import com.jme3.font.BitmapText;
import com.jme3.input.event.MouseButtonEvent;
import com.jme3.input.event.MouseMotionEvent;
import com.jme3.material.Material;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.simsilica.lemur.RangedValueModel;
import com.simsilica.lemur.core.VersionedReference;
import com.simsilica.lemur.event.DefaultMouseListener;
import com.simsilica.lemur.event.MouseEventControl;

/**
 *
 * @author Pesegato
 */
public class PDropDown extends Node{
    
    PPopup popup;
    BitmapText text;
    Geometry arrow,g;
    Enum options[];
    private VersionedReference<Double> state;
    
    EnumProperty2 prop;
    
    boolean closed=true;
    
    public PDropDown(String label, RangedValueModel model, Enum options[], EnumProperty2 prop){
        g=new Geometry("ps",new Box(2f, .2f, .1f));
        g.setMaterial(MermaidAppState.matMermaid.clone());
        g.setShadowMode(RenderQueue.ShadowMode.CastAndReceive);
        attachChild(g);
        g.setLocalTranslation(2, 0, 0);
        arrow=new Geometry("",new Prism(.17f, .1f, 3));
        arrow.setMaterial(MermaidAppState.matMermaid.clone());
        arrow.setShadowMode(RenderQueue.ShadowMode.CastAndReceive);
        arrow.rotate((float) Math.PI/2, 0, 0);
        arrow.setLocalTranslation(3.7f, 0, .11f);
        attachChild(arrow);
        text=new BitmapText(MermaidAppState.font1);
        //font.getPage(0).setFloat("AlphaDiscardThreshold",0.1f);
        //text.setText(options[(int)model.getValue()].toString());
        text.setQueueBucket(RenderQueue.Bucket.Transparent);
        text.scale(0.0025f);
        text.setLocalTranslation(0.4f,0.2f,.2f);
        attachChild(text);
        MouseEventControl.addListenersToSpatial(this, new PBMouseListener());
        popup=new PPopup(this);
        //popup.setModel(options);
        popup.setLocalTranslation(0, -.4f, -3.1f);
        popup.rotate((float)-Math.PI/2, 0, 0);
        attachChild(popup);
        attachChild(new PLabel(label, 0, .4f));
        //this.options=options;
        this.prop=prop;
        setModel(options);
    }
    
    public void setModel(Enum options[]){
        text.setText(options[(int)prop.model.getValue()].toString());
        popup.setModel(options);
        
        this.options=options;
        
    }
    
    public RangedValueModel getModel() {
        return prop.model;
    }
    
    public Enum getSelected(){
        return options[(int)prop.model.getValue()];
    }
    
    public void setSelected(int selectedOption){
        prop.model.setValue(selectedOption);
        text.setText(options[selectedOption].toString());
        addControl(new Move3DZPanelControl(-.35f,1,0));
        popup.addControl(new Rotate3DXPanelControl(true, (float)Math.PI/2,0));
        popup.addControl(new Move3DZPanelControl(-3f,8,.2f));
        closed=true;
    }
    
    class PBMouseListener extends DefaultMouseListener {

        @Override
        protected void click(MouseButtonEvent event, Spatial target, Spatial capture) {
            if (closed){
                addControl(new Move3DZPanelControl(.35f,1,0));
                popup.addControl(new Move3DZPanelControl(3f,8,0));
                popup.addControl(new Rotate3DXPanelControl(false, (float)Math.PI/2,.375f));
                closed=false;
            }
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
