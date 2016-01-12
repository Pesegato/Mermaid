/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pesegato.goldmonkey;

import com.jme3.app.state.AppStateManager;
import com.pesegato.mermaid.PButton;
import com.pesegato.mermaid.PrismAction;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.builders.Builder;
import model.builders.definitions.DefElement;
import model.builders.definitions.Definition;
import static com.pesegato.goldmonkey.MermaidStatic.*;

/**
 *
 * @author Pesegato
 */
public class WallButtonBuilder extends Builder {

    private String text = null;
    private int posx;
    private int posy;
    private PrismAction prismAction;

    public WallButtonBuilder(Definition def) {
        super(def);
        for (DefElement de : def.getElements()) {
            switch (de.name) {
                case TEXT:
                    text = de.getVal();
                    break;
                case POSX:
                    posx = de.getIntVal();
                    break;
                case POSY:
                    posy = de.getIntVal();
                    break;
                case ACTION: {
                    try {
                        prismAction = (PrismAction) Class.forName(de.getVal()).newInstance();
                    } catch (Exception ex) {
                        Logger.getLogger(WallButtonBuilder.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            }
        }
    }

    public PButton build(AppStateManager stateManager) {
        prismAction.stateManager = stateManager;
        return new PButton(text, posx, posy, prismAction);
    }

    @Override
    public void readFinalizedLibrary() {
    }

}
