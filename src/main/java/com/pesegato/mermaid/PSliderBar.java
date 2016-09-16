/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pesegato.mermaid;

import com.jme3.font.BitmapText;
import com.jme3.input.MouseInput;
import com.jme3.input.event.MouseButtonEvent;
import com.jme3.input.event.MouseMotionEvent;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Sphere;
import com.simsilica.lemur.RangedValueModel;
import com.simsilica.lemur.core.VersionedReference;
import com.simsilica.lemur.event.CursorButtonEvent;
import com.simsilica.lemur.event.CursorEventControl;
import com.simsilica.lemur.event.CursorMotionEvent;
import com.simsilica.lemur.event.DefaultCursorListener;
import com.simsilica.lemur.event.DefaultMouseListener;
import com.simsilica.lemur.event.MouseEventControl;

/**
 *
 * @author Pesegato
 */
public class PSliderBar extends Node {

    private PButton2 increment;
    private PButton2 decrement;
    private Geometry cursor;
    double scaleBut;

    Geometry g;

    private RangedValueModel model;
    private double delta;
    private VersionedReference<Double> state;

    public PSliderBar(String label, int x, float y, RangedValueModel model) {
        float width=1.5f;
        g = new Geometry("ps", new Box(width, .2f, .1f));
        g.setMaterial(MermaidAppState.matMermaid);
        g.setShadowMode(RenderQueue.ShadowMode.CastAndReceive);
        attachChild(g);
        g.setLocalTranslation(width, 0, 0);
        BitmapText text = new BitmapText(MermaidAppState.font1);
        //font.getPage(0).setFloat("AlphaDiscardThreshold",0.1f);
        text.setText(label);
        text.setQueueBucket(RenderQueue.Bucket.Transparent);
        text.scale(0.0025f);
        text.setLocalTranslation(0.4f, 0.2f, .11f);
        attachChild(text);
        increment = new PButton2(new Prism(.17f, .1f, 3), MermaidAppState.matMermaid, new PrismAction() {
            @Override
            public void action() {
                //model.setValue(model.getValue() + delta * scaleBut);
            }
        });
        increment.rotate((float) Math.PI/2, 0, (float) Math.PI/2);
        increment.setLocalTranslation(2.7f, 0, -.11f);
        MouseEventControl.addListenersToSpatial(increment, new PIncMouseListener());
        attachChild(increment);

        decrement = new PButton2(new Prism(.17f, .1f, 3), MermaidAppState.matMermaid, new PrismAction() {
            @Override
            public void action() {
                //model.setValue(model.getValue() + delta * scaleBut);
            }
        });
        decrement.rotate((float) Math.PI/2, 0, -(float) Math.PI/2);
        decrement.setLocalTranslation(0.3f, 0, -.11f);
        MouseEventControl.addListenersToSpatial(decrement, new PDecMouseListener());
        attachChild(decrement);

        cursor = new Geometry("", new Sphere(32, 32, 0.15f));
        cursor.setMaterial(MermaidAppState.matMermaid);
        cursor.setLocalTranslation(1.f, 0, -.11f);
        MouseEventControl.addListenersToSpatial(cursor, new PBMouseListener());
        CursorEventControl.addListenersToSpatial(cursor, new ButtonDragger());
        attachChild(cursor);

        Geometry mouseBounds = new Geometry("", new Box(2.2f, .3f, .2f));
        mouseBounds.setMaterial(MermaidAppState.matDebug);
        mouseBounds.setQueueBucket(RenderQueue.Bucket.Translucent);
        mouseBounds.setLocalTranslation(2, 0, 0);
        attachChild(mouseBounds);
        mouseBounds.setCullHint(CullHint.Always);
        MouseEventControl.addListenersToSpatial(mouseBounds, new PBMouseListener());
        addControl(new ExitControl());
        this.model = model;
    }

    public RangedValueModel getModel() {
        return model;
    }

    public void setDelta(double delta) {
        this.delta = delta;
    }

    public double getDelta() {
        return delta;
    }

    long exitTime = 0;

    class ExitControl extends AbstractControl {

        @Override
        protected void controlUpdate(float tpf) {
            if (currentlyFacingActive) {
                if (exitTime != 0) {
                    if (!shouldBeActive) {
                        if (System.currentTimeMillis() - exitTime > 500) {
                            addControl(new Rotate3DXPanelControl(true, (float) ((Math.PI)),0));
                            currentlyFacingActive = false;
                            exitTime = 0;
                        }
                    }
                }
            } else {
                if (shouldBeActive) {
                    addControl(new Rotate3DXPanelControl(true, (float) ((Math.PI)),0));
                    currentlyFacingActive = true;
                }
            }
        }

        @Override
        protected void controlRender(RenderManager rm, ViewPort vp) {
        }

    }

    boolean shouldBeActive = false;
    boolean currentlyFacingActive = false;

    class PBMouseListener extends DefaultMouseListener {

        @Override
        protected void click(MouseButtonEvent event, Spatial target, Spatial capture) {
        }

        @Override
        public void mouseEntered(MouseMotionEvent event, Spatial target, Spatial capture) {
            shouldBeActive = true;
        }

        @Override
        public void mouseExited(MouseMotionEvent event, Spatial target, Spatial capture) {
            shouldBeActive = false;
            exitTime = System.currentTimeMillis();
        }
    }

    class PIncMouseListener extends DefaultMouseListener {

        @Override
        protected void click(MouseButtonEvent event, Spatial target, Spatial capture) {
            model.setValue(model.getValue() + delta);
        }

        @Override
        public void mouseEntered(MouseMotionEvent event, Spatial target, Spatial capture) {
            shouldBeActive = true;
        }

        @Override
        public void mouseExited(MouseMotionEvent event, Spatial target, Spatial capture) {
        }
    }

    class PDecMouseListener extends DefaultMouseListener {

        @Override
        protected void click(MouseButtonEvent event, Spatial target, Spatial capture) {
            model.setValue(model.getValue() - delta);
        }

        @Override
        public void mouseEntered(MouseMotionEvent event, Spatial target, Spatial capture) {
            shouldBeActive = true;
        }

        @Override
        public void mouseExited(MouseMotionEvent event, Spatial target, Spatial capture) {
        }
    }

    @Override
    public void updateLogicalState(float tpf) {
        super.updateLogicalState(tpf);

        if (state == null || state.update()) {
            resetStateView();
        }
    }

    protected void resetStateView() {
        if (state == null) {
            state = model.createReference();
        }

        //Vector3f pos = range.getLocalTranslation();
        Vector3f rangeSize = new Vector3f(2.9f, 0, 0);
        Vector3f thumbSize = cursor.getLocalScale();
        //Vector3f size = getSize();

        double visibleRange;
        double x;
        double y;

        visibleRange = rangeSize.x - thumbSize.x;

        // Calculate where the thumb center should be
        x = 0.54f + visibleRange * model.getPercent();
                //y = pos.y - rangeSize.y * 0.5;

                // We cheated and included the half-thumb spacing in x already which
        // is why this is axis-specific.
        cursor.setLocalTranslation((float) x,
                0,-0.11f);

    }

    private class ButtonDragger extends DefaultCursorListener {

        private Vector2f drag = null;
        private double startPercent;

        @Override
        public void cursorButtonEvent(CursorButtonEvent event, Spatial target, Spatial capture) {
            if (event.getButtonIndex() != MouseInput.BUTTON_LEFT) {
                return;
            }

            //if( capture != null && capture != target )
            //    return;
            event.setConsumed();
            if (event.isPressed()) {
                drag = new Vector2f(event.getX(), event.getY());
                startPercent = model.getPercent();
            } else {
                // Dragging is done.
                drag = null;
            }
        }

        @Override
        public void cursorMoved(CursorMotionEvent event, Spatial target, Spatial capture) {
            if (drag == null) {
                return;
            }

            // Need to figure out how our mouse motion projects
            // onto the slider axis.  Easiest way is to project
            // the end points onto the screen to create a vector
            // against which we can do dot products.
            Vector3f v1 = null;
            Vector3f v2 = null;
            v1 = new Vector3f(cursor.getLocalScale().x * 1.f, 0, 0);
            //100 = dimensione del range parent hardcoded :(
            v2 = v1.add(3 - cursor.getLocalScale().x * 1.f, 0, 0);

            v1 = event.getRelativeViewCoordinates(PSliderBar.this, v1);
            v2 = event.getRelativeViewCoordinates(PSliderBar.this, v2);

            Vector3f dir = v2.subtract(v1);
            float length = dir.length();
            dir.multLocal(1 / length);

            Vector3f cursorDir = new Vector3f(event.getX() - drag.x, event.getY() - drag.y, 0);

            float dot = cursorDir.dot(dir);

            // Now, the actual amount is then dot/length
            float percent = dot / length;
            model.setPercent(startPercent + percent);

            event.setConsumed();
        }
    }

}
