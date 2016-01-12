/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pesegato.mermaid;

import com.jme3.font.BitmapText;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.simsilica.lemur.RangedValueModel;
import com.simsilica.lemur.core.VersionedReference;

/**
 *
 * @author Pesegato
 */
public class PSlider2 extends Node {

    private PButton2 increment;
    private PButton2 decrement;
    private Geometry cursor;
    double scaleBut;

    Geometry g;

    private RangedValueModel model;
    private VersionedReference<Double> state;
    
    PSliderBar sliderBar;
    BitmapText textVal;

    public PSlider2(String label, RangedValueModel model, boolean text) {
        sliderBar=new PSliderBar(label, 0, 0, model);
        attachChild(sliderBar);
        g = new Geometry("ps", new Box(.5f, .2f, .1f));
        g.setMaterial(MermaidAppState.matMermaid);
        g.setShadowMode(RenderQueue.ShadowMode.CastAndReceive);
        attachChild(g);
        g.setLocalTranslation(3.6f, 0, 0);

        textVal = new BitmapText(MermaidAppState.font1);
        //font.getPage(0).setFloat("AlphaDiscardThreshold",0.1f);
        textVal.setQueueBucket(RenderQueue.Bucket.Transparent);
        textVal.scale(0.0025f);
        //textVal.setSize(new Vector3f(50000000,50000000,50000000));
        //textVal.setTextHAlignment(HAlignment.Center);
        //textVal.setBox(new Rectangle(0, 0, 500, 50));
        textVal.setLocalTranslation(text?3.2f:2.4f, 0.2f, .11f);
        //textVal.setPreferredSize(new Vector3f(500,0,0));
        attachChild(textVal);

        this.model = model;
    }

    public BitmapText getText(){
        return textVal;
    }
    
    public RangedValueModel getModel() {
        return model;
    }

    public void setDelta(double delta) {
        sliderBar.setDelta(delta);
    }

    public double getDelta() {
        return sliderBar.getDelta();
    }


}
