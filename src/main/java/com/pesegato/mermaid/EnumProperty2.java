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
    public class EnumProperty2 extends PAbstractProperty<Enum> {
        private BitmapText valueText;
        private PSlider2 slider;
        public RangedValueModel model;
        private VersionedReference<Double> value;
        private Class type;
        private Enum[] values;
        
        public EnumProperty2( String name, PPropertyPanel.Access<Enum> access ) {
            super(name, access);
 
            this.type = access.getType();
            this.values = (Enum[])type.getEnumConstants();  
 
            this.model = new DefaultRangedValueModel( 0, values.length - 1, 0 );
        }
        
        public void setValues(Enum[] values){
            this.values=values;
            this.model = new DefaultRangedValueModel( 0, values.length - 1, 0 );
        }
        
        @Override
        public void initialize( Container container ) {
            slider = new PSlider2(getDisplayName(), model,true);
            refresh();
            valueText = slider.getText();
            updateText();
                        
            dropDown=new PDropDown(getDisplayName(), model, values, this);
            value = dropDown.getModel().createReference();
        }

        PDropDown dropDown;
        
        protected void updateText() {
            int index = (int)model.getValue();
            valueText.setText(String.valueOf(values[index]));        
        }
        
        public Node getWidget(float posX, float posY){
            dropDown.setLocalTranslation(posX, posY, 0);
            return dropDown;
        }
        
        @Override
        public void update() {
            if( value.update() ) {
                int i = (int)model.getValue();                
                super.setValue(values[i]);
                updateText();
            }
        }
        
        @Override
        public void refresh() {
            Enum current = getValue();
            int index = current.ordinal();
            model.setValue(index);
        }
    }

