import java.util.*;

import remixlab.proscene.*;
import remixlab.bias.*;
import remixlab.bias.event.*;
import remixlab.dandelion.geom.*;
import remixlab.dandelion.core.*;

private Scene scene;
private MusicAgent agente;
private LinkedList<InteractiveFrame> frames;
private int idx = 0;

void setup(){
  size(800,600,P3D);
  scene = new Scene(this);
  agente = new MusicAgent(scene,this);
  frames = new LinkedList<InteractiveFrame>();
  frames.add(scene.eyeFrame());
  
}


void draw(){
  
  
}