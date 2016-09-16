/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pesegato.mermaid;

import java.util.ArrayList;

/**
 *
 */
public class PRadioGroup {

    ArrayList<PSwitch> pSwitchs = new ArrayList();
    PrismaticPane pp;
    int currentlyPressed=0;

    public void addSwitch(PSwitch pswitch) {
        pSwitchs.add(pswitch);
        pswitch.setRadioGroup(this);
    }
    
    public void setPrismaticPane(PrismaticPane pp){
        this.pp=pp;
    }

    void pressed(int number) {
        for (int i = 0; i < pSwitchs.size(); i++) {
            if (i != number) {
                pSwitchs.get(i).release();
            }
        }
        int numGiri=0;
        if (number<currentlyPressed)
            numGiri=6-currentlyPressed+number;
        else
            numGiri=number-currentlyPressed;
            currentlyPressed=number;
        pp.addControl(new Rotate3DYPanelControl(true, (float)((Math.PI/3)*numGiri)));
    }
}
