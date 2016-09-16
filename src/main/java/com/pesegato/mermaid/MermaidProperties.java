/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pesegato.mermaid;

import com.jme3.scene.Node;
import com.simsilica.lemur.core.VersionedObject;
import com.simsilica.lemur.core.VersionedReference;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pesegato
 */
public class MermaidProperties extends Node implements VersionedObject<MermaidProperties>{
    private List<PAbstractProperty> properties = new ArrayList<PAbstractProperty>();
    private PAbstractProperty[] propertyArray;
    private long version;
    public void addProperty( PAbstractProperty p ) {
        properties.add(p);
        propertyArray = null;
    } 

    @Override
    public long getVersion() {
        return version;
    }

    @Override
    public MermaidProperties getObject() {
        return this;
    }

    @Override
    public VersionedReference<MermaidProperties> createReference() {
        return new VersionedReference<MermaidProperties>(this);
    }
    
    protected PAbstractProperty[] getArray() {
        if( propertyArray == null ) {
            propertyArray = new PAbstractProperty[properties.size()];
            propertyArray = properties.toArray(propertyArray);
        }
        return propertyArray;
    }
    
    @Override
    public void updateLogicalState( float tpf ) {
        super.updateLogicalState(tpf);
        for( PAbstractProperty p : getArray() ) {
            p.update();
        }
    }
    /**
     *  Call to force all property editors to refresh their values
     *  from the source object.  This is the only way to get the 
     *  UI to update if the values have been changed outside of the
     *  UI.
     */
    public void refresh() {
        for( PAbstractProperty p : getArray() ) {
            p.refresh();
        }
    }

}
