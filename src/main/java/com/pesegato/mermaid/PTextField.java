/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pesegato.mermaid;

import com.jme3.font.BitmapText;
import com.jme3.input.KeyInput;
import com.jme3.input.event.MouseButtonEvent;
import com.jme3.input.event.MouseMotionEvent;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.simsilica.lemur.GuiGlobals;
import com.simsilica.lemur.TextField;
import com.simsilica.lemur.component.TextEntryComponent;
import com.simsilica.lemur.core.GuiControl;
import com.simsilica.lemur.event.DefaultMouseListener;
import com.simsilica.lemur.event.KeyAction;
import com.simsilica.lemur.event.KeyActionListener;
import com.simsilica.lemur.event.MouseEventControl;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pesegato
 */
public class PTextField extends Node {

    String labelFront;

    TextField textField;

    Geometry g;
    TextListener listener;

    public PTextField(String labelFront, String hint, int x, int y, TextListener listener) {
        g = new Geometry("ps", new Box(2f, .2f, .1f));
        g.setMaterial(MermaidAppState.matMermaid);
        g.setShadowMode(RenderQueue.ShadowMode.CastAndReceive);
        attachChild(g);
        g.setLocalTranslation(2, 0, 0);
        setLocalTranslation(x, y, 0);
        BitmapText text = new BitmapText(MermaidAppState.font1);
        //font.getPage(0).setFloat("AlphaDiscardThreshold",0.1f);
        text.setText(labelFront);
        text.setQueueBucket(RenderQueue.Bucket.Transparent);
        text.scale(0.0025f);
        text.setLocalTranslation(0.4f, 0.2f, .11f);
        attachChild(text);
        textField = new TextField(hint);
        textField.setPreferredWidth(320);
        textField.setQueueBucket(RenderQueue.Bucket.Opaque);
        textField.scale(0.01f);
        textField.rotate((float) Math.PI, 0, 0);
        textField.setLocalTranslation(0.4f, -0.1f, -.11f);
        attachChild(textField);
        textField.getActionMap().put(new KeyAction(KeyInput.KEY_RETURN), new Enter());
        textField.getActionMap().put(new KeyAction(KeyInput.KEY_V, KeyAction.CONTROL_DOWN), new Paste());
        /*
         BitmapText text2=new BitmapText(Enlightenment.font1);
         //font.getPage(0).setFloat("AlphaDiscardThreshold",0.1f);
         text2.setText(labelBack);
         text2.setQueueBucket(RenderQueue.Bucket.Transparent);
         text2.scale(0.0025f);
         text2.rotate((float)Math.PI, 0, 0);
         text2.setLocalTranslation(0.4f,-0.2f,-.11f);
         attachChild(text2);
         */
        Geometry mouseBounds = new Geometry("", new Box(2.2f, .3f, .2f));
        mouseBounds.setMaterial(MermaidAppState.matDebug);
        mouseBounds.setQueueBucket(RenderQueue.Bucket.Translucent);
        mouseBounds.setLocalTranslation(2, 0, 0);
        attachChild(mouseBounds);
        mouseBounds.setCullHint(CullHint.Always);
        MouseEventControl.addListenersToSpatial(mouseBounds, new PBMouseListener());

        this.listener=listener;
    }

    public void close(){
        textField.getControl(GuiControl.class).focusLost();    
    }
    
    private class Enter implements KeyActionListener {

        @Override
        public void keyAction(TextEntryComponent source, KeyAction key) {
            listener.textInserted(source.getText());
        }
    }

    private static class Paste implements KeyActionListener {

        @Override
        public void keyAction(TextEntryComponent source, KeyAction key) {
            try {
                String data = (String) Toolkit.getDefaultToolkit()
                        .getSystemClipboard().getData(DataFlavor.stringFlavor);
                source.setText(data);
            } catch (UnsupportedFlavorException | IOException ex) {
                Logger.getLogger(PTextField.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    class PBMouseListener extends DefaultMouseListener {

        @Override
        protected void click(MouseButtonEvent event, Spatial target, Spatial capture) {
        }

        @Override
        public void mouseEntered(MouseMotionEvent event, Spatial target, Spatial capture) {
            addControl(new Rotate3DXPanelControl(true, (float) ((Math.PI)), 0));
            GuiGlobals.getInstance().requestFocus(textField);
        }

        @Override
        public void mouseExited(MouseMotionEvent event, Spatial target, Spatial capture) {
            addControl(new Rotate3DXPanelControl(true, (float) ((Math.PI)), 0));
        }
    }
}
