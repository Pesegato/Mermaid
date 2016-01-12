/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pesegato.goldmonkey;

import com.jme3.scene.Node;
import com.pesegato.mermaid.PLabel;
import model.builders.Builder;
import model.builders.definitions.DefElement;
import model.builders.definitions.Definition;
import static com.pesegato.goldmonkey.MermaidStatic.*;
import com.pesegato.mermaid.EnumProperty2;
import com.pesegato.mermaid.MermaidAppState;
import com.pesegato.mermaid.PBooleanProperty;
import com.pesegato.mermaid.PFloatProperty;
import com.pesegato.mermaid.PPropertyAccess;
import com.pesegato.mermaid.PPropertyPanel;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pesegato
 */
public class MermaidBuilder extends Builder {

    private String type = null;
    private String text = null;
    private double posx;
    private double posy;
    private double min;
    private double max;
    private double step;
    private String bean;
    private ArrayList<String> childID = new ArrayList<>();

    public MermaidBuilder(Definition def) {
        super(def);
        for (DefElement de : def.getElements()) {
            switch (de.name) {
                case CLASS:
                    type = de.getVal();
                case TEXT:
                    text = de.getVal();
                    break;
                case POSX:
                    posx = de.getDoubleVal();
                    break;
                case POSY:
                    posy = de.getDoubleVal();
                    break;
                case MIN:
                    min = de.getDoubleVal();
                    break;
                case MAX:
                    max = de.getDoubleVal();
                    break;
                case STEP:
                    step = de.getDoubleVal();
                    break;
                case BEAN:
                    bean = de.getVal();
                    break;
                case CHILDREN_LINK_LIST:
                    childID.add(de.getVal());
                    break;
            }
        }
    }

    public Node build() {
        try {
            switch (type) {
                case MERMAID_LABEL:
                    return new PLabel(text, (float) posx, (float) posy);
                case MERMAID_CHECK:
                    PBooleanProperty p = new PBooleanProperty(text, new PPropertyAccess<Boolean>(Class.forName("com.pesegato.mermaid.settings.B" + bean).newInstance(), bean));
                    p.initialize(null);
                    MermaidAppState.properties.addProperty(p);
                    return p.getWidget((float) posx, (float) posy);
                case MERMAID_SLIDER:
                    PFloatProperty pf = new PFloatProperty(text, new PPropertyAccess<Float>(Class.forName("com.pesegato.mermaid.settings.B" + bean).newInstance(), bean), (float) min, (float) max, (float) step);
                    pf.initialize(null);
                    MermaidAppState.properties.addProperty(pf);
                    return pf.getWidget((float) posx, (float) posy);
                case MERMAID_DROPDOWN:
                    EnumProperty2 px = new EnumProperty2(text, new PPropertyAccess<Enum>(Class.forName("com.pesegato.mermaid.settings.B" + bean).newInstance(), bean));
                    px.initialize(null);
                    MermaidAppState.properties.addProperty(px);
                    return px.getWidget((float) posx, (float) posy);
                case MERMAID_PANE:
                    PPropertyPanel panel = new PPropertyPanel(MermaidAppState.LEMURSTYLE);
                    Node nodes[] = new Node[childID.size()];
                    for (int i = 0; i < childID.size(); i++) {
                        nodes[i] = Mermaid.getLabel(childID.get(i));
                        panel.attachChild(nodes[i]);
                    }
                    return panel;
            }
        } catch (Exception ex) {
            Logger.getLogger(MermaidBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void readFinalizedLibrary() {
    }
}
