/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pesegato.mermaid;

import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;

/**
 *video: info + opzioni
 * Game: ?new game, ?quit, difficulty? ?hour of day. z light pos?
 * Debug: ???
 * Input: invert mouse
 * Sound: enable sound enable fx, volume
 * Cosmetic: insert code, choose
 */
public class PrismaticPane extends Node {

    public PrismaticPane(Material mat, Node parent) {
        Prism p = new Prism(5, 3, 6);
        Geometry gp = new Geometry("p", p);
        gp.setMaterial(mat);
        setLocalTranslation(0, 0, -5);
        gp.rotate(0, (float) (Math.PI / 6), 0);
        attachChild(gp);
        parent.attachChild(this);

    }
    Vector3f tabPos = new Vector3f(-2, 2.8f, 4.34f);
    int tabNumber = 0;

    public void addFace(Node pane) {
        Node gameTab = new Node();
        gameTab.attachChild(pane);
        //pane.scale(0.01f);
        pane.setLocalTranslation(tabPos);
        gameTab.rotate(0, tabNumber * (float) (Math.PI / 3), 0);
        attachChild(gameTab);
        tabNumber++;
    }
    
}
