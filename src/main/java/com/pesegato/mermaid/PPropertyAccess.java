/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pesegato.mermaid;

import com.pesegato.mermaid.PPropertyPanel.Access;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author Pesegato
 */
public class PPropertyAccess<T> implements Access<T> {
        private Object bean;
        private PropertyDescriptor pd;
        
        public PPropertyAccess( Object bean, String propertyName ) {
            this.bean = bean;
            this.pd = findProperty(bean, propertyName);
            if( this.pd == null ) {
                throw new IllegalArgumentException("Property not found:" + propertyName + " on:" + bean);
            }
        }
                    
        @Override
        public void setValue( T value ) {
            setPropertyValue(pd, bean, value);
        }
        
        @Override
        public T getValue() {
            return getPropertyValue(pd, bean);
        }

        @Override        
        public Class getType() {
            return pd.getPropertyType();
        }
        
    protected <T> T getPropertyValue( PropertyDescriptor pd, Object bean ) {
        try {
            return (T)pd.getReadMethod().invoke(bean);
        } catch( IllegalAccessException e ) {
            throw new RuntimeException("Error getting value", e);
        } catch( InvocationTargetException e ) {
            throw new RuntimeException("Error getting value", e);
        }
    }

    protected void setPropertyValue( PropertyDescriptor pd, Object bean, Object value ) {
        try {
            pd.getWriteMethod().invoke(bean, new Object[] { value });
//            version++;
        } catch( IllegalAccessException e ) {
            throw new RuntimeException("Error setting value", e);
        } catch( InvocationTargetException e ) {
            throw new RuntimeException("Error setting value", e);
        }
    }
    
    protected PropertyDescriptor findProperty( Object bean, String propertyName ) {
        try {
            BeanInfo info = Introspector.getBeanInfo(bean.getClass());
 
            for( PropertyDescriptor pd : info.getPropertyDescriptors() ) {
                if( pd.getName().equals(propertyName) ) {
                    if( pd.getReadMethod() == null ) {
                        throw new RuntimeException("Property has no read method:" + propertyName + " on:" + bean.getClass());
                    }
                    if( pd.getWriteMethod() == null ) {
                        throw new RuntimeException("Property has no write method:" + propertyName + " on:" + bean.getClass());
                    }
                    return pd;
                }
            }
            throw new RuntimeException("No suche property:" + propertyName + " on:" + bean.getClass());
        } catch( IntrospectionException e ) {
            throw new RuntimeException("Error introspecting object", e);
        }
        //return null;        
    }

}
