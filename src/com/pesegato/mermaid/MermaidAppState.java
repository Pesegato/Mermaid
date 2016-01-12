/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pesegato.mermaid;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.font.BitmapFont;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.math.ColorRGBA;
import com.jme3.system.JmeVersion;
import com.pesegato.goldmonkey.GM;
import com.simsilica.lemur.GuiGlobals;
import com.simsilica.lemur.Label;
import com.simsilica.lemur.style.Attributes;
import com.simsilica.lemur.style.Styles;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

/**
 *
 * @author Pesegato
 */
public class MermaidAppState extends BaseAppState {

    public static final String LEMURSTYLE = "mermaid";
    public static Material matMermaid, matDebug;
    public static BitmapFont font1;

    public PPropertyPanel miscSettings;

    public static MermaidProperties properties = new MermaidProperties();

    @Override
    protected void initialize(Application app) {
        GuiGlobals.initialize(app);
        Styles styles = GuiGlobals.getInstance().getStyles();
        Attributes attrs = styles.getSelector(Label.ELEMENT_ID, "prism");
        font1 = app.getAssetManager().loadFont("Interface/Fonts/"+GM.getString("FontA"));
        font1.getPage(0).setFloat("AlphaDiscardThreshold", 0.1f);
        attrs.set("font", font1);
        attrs.set("fontSize", GM.getInt("FontA"));

        a = GM.getColorRGBA("ColorA");
        b = GM.getColorRGBA("ColorB");
        c = GM.getColorRGBA("ColorC");

        ((SimpleApplication) app).getRootNode().attachChild(properties);

        matMermaid = new Material(app.getAssetManager(), "Common/MatDefs/Light/Lighting.j3md");
        matMermaid.setBoolean("UseMaterialColors", true);
        matMermaid.setColor("Diffuse", ColorRGBA.White.clone());
        matMermaid.setColor("Ambient", ColorRGBA.White.clone());
        matDebug = new Material(app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        matDebug.setColor("Color", new ColorRGBA(1, 0, 0, 0.17f));
        matDebug.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Alpha);

        String oglVersion = GL11.glGetString(GL11.GL_VERSION);

        int split = oglVersion.indexOf(' ');
        if (split >= 0) {
            oglVersion = oglVersion.substring(0, split);
        }
        String glslVersion = GL11.glGetString(GL20.GL_SHADING_LANGUAGE_VERSION);

        split = glslVersion.indexOf(' ');
        if (split >= 0) {
            glslVersion = glslVersion.substring(0, split);
        }
        miscSettings = new PPropertyPanel(LEMURSTYLE);
        miscSettings.attachChild(new PLabel(GL11.glGetString(GL11.GL_RENDERER), 0, -.5f));
        miscSettings.attachChild(new PLabel("Ver. " + Display.getVersion(), 0, -1));
        miscSettings.attachChild(new PLabel("OpenGL " + oglVersion + " GLSL " + glslVersion, 0, -1.5f));
        miscSettings.attachChild(new PLabel(System.getProperty("os.name") + " ver." + System.getProperty("os.version"), 0, -2));
        miscSettings.attachChild(new PLabel("Java " + System.getProperty("java.version") + " " + System.getProperty("os.arch"), 0, -2.5f));
        miscSettings.attachChild(new PLabel("Jme " + JmeVersion.FULL_NAME.substring(14), 0, -3));
        miscSettings.attachChild(new PLabel("hash " + JmeVersion.GIT_SHORT_HASH + " " + JmeVersion.BUILD_DATE, 0, -3.5f));

    }

    static ColorRGBA a, b, c;

    public static ColorRGBA getColorA() {
        return a;
    }

    public static ColorRGBA getColorB() {
        return b;
    }

    public static ColorRGBA getColorC() {
        return c;
    }

    @Override
    protected void cleanup(Application app) {
    }

    @Override
    protected void onEnable() {
    }

    @Override
    protected void onDisable() {
    }

}
