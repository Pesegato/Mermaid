/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pesegato.mermaid;

import com.pesegato.mermaid.PPropertyPanel.Access;
import com.pesegato.mermaid.PPropertyPanel.Property;
import com.simsilica.lemur.Container;

/**
 *
 * @author Pesegato
 */
public abstract class PAbstractProperty <T> implements Property<T> {
        private String name;
        private Access<T> access;
        
        protected PAbstractProperty( String name, Access<T> access ) {            
            this.name = name;
            this.access = access;
        }
 
        protected String getDisplayName() {
            return name;
        }
 
        @Override
        public void setValue( T value ) {
            access.setValue(value);
        }
        
        @Override
        public T getValue() {
            return access.getValue();
        }
 
        public abstract void initialize( Container container );
    
        public abstract void update();
        
        public abstract void refresh(); 
    }    
