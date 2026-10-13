<div align="center"><img alt="Static Badge" src="https://img.shields.io/badge/🐵jme3-Mermaid-yellow?style=for-the-badge"  height="100" align="center">
    
# 3D GUI widgets for JMonkeyEngine 3
    
[![Maven Central](https://img.shields.io/maven-central/v/io.github.pesegato/Mermaid?style=for-the-badge)](https://central.sonatype.com/artifact/io.github.pesegato/Mermaid)
[![GitHub Packages](https://img.shields.io/github/v/tag/Pesegato/Mermaid?label=GitHub%20Packages&style=for-the-badge)](https://github.com/Pesegato/Mermaid/packages/3308296)
[![javadoc](https://javadoc.io/badge2/io.github.pesegato/Mermaid/javadoc.svg?style=for-the-badge)](https://javadoc.io/doc/io.github.pesegato/Mermaid) 

</div>

<div align="center">
<img src="mermaid-logo.png" alt="Mermaid logo"/>
</div>

## Descrizione

**Mermaid** è una libreria per [jMonkeyEngine 3](https://jmonkeyengine.org/) che fornisce widget di interfaccia utente 3D nello spazio tridimensionale (diegetici/in-game), integrati e compatibili con il sistema di eventi e modelli dati di [Lemur](https://github.com/jMonkeyEngine-Community/Lemur).

Consente di creare pulsanti, slider, checkbox, controlli a rotazione e pannelli posizionati direttamente nella scena 3D, beneficiando di ombre, illuminazione, materiali e interazioni del cursore/mouse di jME3.
    
---
    
## Caratteristiche & Componenti
    
- **Integrazione Lemur**: sfrutta il sistema di eventi input (`MouseEventControl`, `CursorEventControl`) e modelli dati come `RangedValueModel`.
- **Widget 3D inclusi**:
  - `PButton` / `PButton2`: pulsanti 3D interattivi con callback (`PrismAction`).
  - `PSliderBar` / `PSlider2`: barre di scorrimento 3D collegate a valori numerici.
  - `PCheckbox` / `PSwitch`: interruttori e caselle di spunta nello spazio 3D.
  - `PRadioGroup` / `PRadiobox`: gruppi di selezione esclusiva 3D.
  - `PDropDown` / `PPopup`: menu a comparsa 3D.
  - `PLabel` / `PTextField`: testi ed elementi testuali 3D.
  - `PrismaticPane` / `PPropertyPanel`: pannelli per raggruppare controlli e proprietà.
- **Controlli di animazione 3D**:
  - `Press3DButtonControl`: animazione di pressione pulsante lungo l'asse Z.
  - `Rotate3DXPanelControl` / `Rotate3DYPanelControl`: rotazioni dinamiche di pannelli e widget.
  - `Move3DZPanelControl`: animazioni di traslazione in profondità.

---

## Installazione

Aggiungi la dipendenza nel tuo file `build.gradle`:

```groovy
repositories {
    mavenCentral()
}

dependencies {
    implementation 'io.github.pesegato:Mermaid:0.1.0'
}
```

Oppure con Maven (pom.xml):

```
<dependency>
    <groupId>io.github.pesegato</groupId>
    <artifactId>Mermaid</artifactId>
    <version>0.1.0</version>
</dependency>
```
──────
## Esempi d'uso

### 1. Inizializzazione di MermaidAppState

Prima di creare i widget, registra MermaidAppState nella tua applicazione jME3:

```
import com.pesegato.mermaid.MermaidAppState;

@Override
public void simpleInitApp() {
    // Inizializza lo stato dell'applicazione per configurare Lemur, stili e font di Mermaid
    stateManager.attach(new MermaidAppState());
}
```

### 2. Creazione di un pulsante 3D (PButton)

```
import com.pesegato.mermaid.PButton;
import com.pesegato.mermaid.PrismAction;

// Creazione di un pulsante 3D posizionato alle coordinate (x=0, y=2)
PButton button = new PButton("Start Game", 0, 2, new PrismAction() {
    @Override
    public void action() {
        System.out.println("Pulsante 3D premuto!");
    }
});

// Aggiungi il pulsante alla scena
rootNode.attachChild(button);
```

### 3. Creazione di uno slider 3D (PSliderBar)

```
import com.pesegato.mermaid.PSliderBar;
import com.simsilica.lemur.DefaultRangedValueModel;

// Modello con intervallo [0.0, 100.0] e valore iniziale 50.0
DefaultRangedValueModel model = new DefaultRangedValueModel(0, 100, 50);

PSliderBar slider = new PSliderBar("Volume", 0, 1.0f, model);
rootNode.attachChild(slider);
```
──────
## Riferimenti

• Discussione sul forum jMonkeyEngine:
https://hub.jmonkeyengine.org/t/mermaid-lemur-compatible-3d-widgets/36898
