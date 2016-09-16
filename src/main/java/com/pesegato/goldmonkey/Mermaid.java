/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pesegato.goldmonkey;

import com.jme3.app.state.AppStateManager;
import com.jme3.scene.Node;
import com.pesegato.mermaid.PButton;
import model.builders.definitions.BuilderManager;

/**
 *
 * @author Pesegato
 */
public class Mermaid extends GM {

    public static PButton getWallButton(String id, AppStateManager stateManager) {
        return ((WallButtonBuilder) BuilderManager.getBuilder("com.pesegato.goldmonkey.WallButtonBuilder", id, WallButtonBuilder.class)).build(stateManager);
    }

    public static Node getLabel(String id) {
        return ((MermaidBuilder) BuilderManager.getBuilder("com.pesegato.goldmonkey.MermaidBuilder", id, MermaidBuilder.class)).build();
    }

}
