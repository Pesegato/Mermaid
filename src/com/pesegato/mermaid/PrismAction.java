package com.pesegato.mermaid;

import com.jme3.app.state.AppStateManager;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pesegato
 */
public abstract class PrismAction {
    public AppStateManager stateManager;
    public abstract void action();
}
