/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pesegato.mermaid;

import com.jme3.font.BitmapText;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;

/**
 *
 */
public class PLabel extends Node {

    Geometry g;
    
    public PLabel(String label, float x, float y) {
        setLocalTranslation(x, y, 0);
        BitmapText text=new BitmapText(MermaidAppState.font1);
        //font.getPage(0).setFloat("AlphaDiscardThreshold",0.1f);
        text.setText(label);
        text.setQueueBucket(RenderQueue.Bucket.Transparent);
        text.scale(0.0025f);
        text.setLocalTranslation(0.4f,0.2f,.1f);
        attachChild(text);
        
    }
    
}
