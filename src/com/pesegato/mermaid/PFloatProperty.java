/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pesegato.mermaid;

import com.jme3.font.BitmapText;
import com.jme3.scene.Node;
import com.simsilica.lemur.Container;
import com.simsilica.lemur.DefaultRangedValueModel;
import com.simsilica.lemur.RangedValueModel;
import com.simsilica.lemur.core.VersionedReference;

/**
 *
 * @author Pesegato
 */
public class PFloatProperty extends PAbstractProperty<Float> {
        //private Label label;
        private BitmapText valueText;
        private PSlider2 slider;
        private RangedValueModel model;
        private float step;        
        private VersionedReference<Double> value;
        private String format = "%14.3f";
        
        public PFloatProperty( String name, PPropertyPanel.Access<Float> access, float min, float max, float step ) {
            super(name, access);
 
            this.model = new DefaultRangedValueModel( min, max, 0 );
            this.step = step;
        }
        
        @Override
        public void initialize( Container container ) {
            //label = new Label(getDisplayName() + ":", getElementId().child("float.label"), getStyle());
            //label.setTextHAlignment(HAlignment.Right); 
            slider = new PSlider2( getDisplayName(), model,false);
            slider.setDelta(step);
            
            //Float current = getValue();
            //model.setValue(current);
            refresh();
            valueText=slider.getText();
            //valueText = new Label("", getElementId().child("value.label"), getStyle());
            updateText();
                        
            value = slider.getModel().createReference();
            //container.addChild(label);
            //container.addChild(valueText, 1); 
            //attachChild(slider); 
            //posY-=.35f;
        }

        public Node getWidget(float posx, float posy){
            slider.setLocalTranslation(posx, posy, 0);
            return slider;
        }
        
        protected void updateText() {
            valueText.setText(String.format(format, model.getValue()));
        }

        @Override
        public void update() {
            if( value.update() ) {
                super.setValue((float)model.getValue());
                updateText();
            }
        }
        
        @Override
        public void refresh() {
            Float current = getValue();
            model.setValue(current);
        }
    }