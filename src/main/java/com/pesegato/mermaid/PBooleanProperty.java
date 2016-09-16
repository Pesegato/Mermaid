/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pesegato.mermaid;

import com.jme3.scene.Node;
import com.pesegato.mermaid.PPropertyPanel.AbstractProperty;
import com.pesegato.mermaid.PPropertyPanel.Access;
import com.simsilica.lemur.Container;
import com.simsilica.lemur.core.VersionedReference;

/**
 *
 * @author Pesegato
 */
public class PBooleanProperty  extends PAbstractProperty<Boolean> {
        private PCheckbox check;
        private VersionedReference<Boolean> value;
        
        float posY=0;
        
        public PBooleanProperty( String name, Access<Boolean> access ) {
            super(name, access);
        }

        @Override
        public void initialize( Container container ) {
            check = new PCheckbox(getDisplayName(),0,posY);
            check.setChecked(getValue());
            value = check.getModel().createReference();
//            attachChild(check);
            posY-=.35f;
        }
        
        public Node getWidget(float posx, float posy){
            check.setLocalTranslation(posx, posy, 0);
            return check;
        }
        
        @Override
        public void update() {
            if( value.update() ) {
                super.setValue(check.isChecked());
            }
        }
        
        @Override
        public void refresh() {
            check.setChecked(getValue());
        }
    }
