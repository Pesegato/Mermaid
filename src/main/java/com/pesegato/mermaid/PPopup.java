/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pesegato.mermaid;

import com.jme3.font.BitmapText;
import com.jme3.input.event.MouseButtonEvent;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.simsilica.lemur.event.DefaultMouseListener;
import com.simsilica.lemur.event.MouseEventControl;

/**
 *
 * @author Pesegato
 */
public class PPopup extends Node {

    BitmapText text[];

    PDropDown dropDown;

    Geometry g;

    public PPopup(PDropDown dropDown) {
        this.dropDown = dropDown;
    }

    public void setModel(Enum options[]) {
        detachAllChildren();
        text = new BitmapText[options.length];
        g = new Geometry("ps", new Box(2f, .1f + .15f * options.length, .01f));
        g.setMaterial(MermaidAppState.matMermaid.clone());
        g.setShadowMode(RenderQueue.ShadowMode.CastAndReceive);
        attachChild(g);
        g.setLocalTranslation(2, .1f + -.15f * options.length, 0);
        //setLocalTranslation(x, y, 0);
        for (int i = 0; i < options.length; i++) {
            text[i] = new BitmapText(MermaidAppState.font1);
            //font.getPage(0).setFloat("AlphaDiscardThreshold",0.1f);
            text[i].setText(options[i].toString());
            text[i].setQueueBucket(RenderQueue.Bucket.Transparent);
            text[i].scale(0.0025f);
            text[i].setLocalTranslation(0.4f, 0.15f + .0f * options.length - 0.3f * i, .11f);
            attachChild(text[i]);
            Geometry mouseBounds = new Geometry("", new Box(2.2f, .15f, .2f));
            mouseBounds.setMaterial(MermaidAppState.matDebug);
            mouseBounds.setQueueBucket(RenderQueue.Bucket.Translucent);
            mouseBounds.setLocalTranslation(2, -.3f * i, 0);
            mouseBounds.setUserData("index", i);
            attachChild(mouseBounds);
            mouseBounds.setCullHint(Spatial.CullHint.Always);
            MouseEventControl.addListenersToSpatial(mouseBounds, new PPMouseListener(i));
        }
    }

    public void setVisible(boolean visible) {
        setCullHint(visible ? CullHint.Always : CullHint.Inherit);
    }

    class PPMouseListener extends DefaultMouseListener {

        int index;

        public PPMouseListener(int index) {
            this.index = index;
        }

        @Override
        protected void click(MouseButtonEvent event, Spatial target, Spatial capture) {
            dropDown.setSelected(index);
        }
    }
}
