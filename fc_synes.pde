import java.util.*;

import remixlab.proscene.*;
import remixlab.bias.*;
import remixlab.bias.event.*;
import remixlab.dandelion.geom.*;
import remixlab.dandelion.core.*;

private Scene scene;
private MusicAgent MusicAgent;
private LinkedList<InteractiveFrame> frames;
private int idx = 0;

void setup(){
  size(800,600,P3D);
  scene = new Scene(this);
  MusicAgent = new MusicAgent(scene,this);
  frames = new LinkedList<InteractiveFrame>();
  frames.add(scene.eyeFrame());
  
  scene.eyeFrame().setBinding(new NoteShortcut(BogusEvent.NO_MODIFIER_MASK, MusicAgent.NoteShortcut_NO), "noAction");
  scene.eyeFrame().setBinding(new NoteShortcut(BogusEvent.NO_MODIFIER_MASK, MusicAgent.NoteShortcut_DO), "doAction");
  scene.eyeFrame().setBinding(new NoteShortcut(BogusEvent.NO_MODIFIER_MASK, MusicAgent.NoteShortcut_RE), "reAction");
  scene.eyeFrame().setBinding(new NoteShortcut(BogusEvent.NO_MODIFIER_MASK, MusicAgent.NoteShortcut_MI), "miAction");
  scene.eyeFrame().setBinding(new NoteShortcut(BogusEvent.NO_MODIFIER_MASK, MusicAgent.NoteShortcut_FA), "faAction");
  scene.eyeFrame().setBinding(new NoteShortcut(BogusEvent.NO_MODIFIER_MASK, MusicAgent.NoteShortcut_SL), "slAction");
  scene.eyeFrame().setBinding(new NoteShortcut(BogusEvent.NO_MODIFIER_MASK, MusicAgent.NoteShortcut_LA), "laAction");
  scene.eyeFrame().setBinding(new NoteShortcut(BogusEvent.NO_MODIFIER_MASK, MusicAgent.NoteShortcut_SI), "siAction");

}

void draw(){
  background(255);
  box(30);
  pushMatrix();
  //frames.get(1).draw();
  scene.drawAxes(20);
  popMatrix();
}


/**
*
*
*
*
*
*/
public boolean checkIfGrabsInput(InteractiveFrame frame, NoteEvent event){
  return true;
}

public void noAction(InteractiveFrame frame, NoteEvent event){
  //println("no event");
}

public void doAction(InteractiveFrame frame, NoteEvent event){
  println("do event");
}

public void reAction(InteractiveFrame frame, NoteEvent event){
  println("re event");
}

public void miAction(InteractiveFrame frame, NoteEvent event){
  println("mi event");
}

public void faAction(InteractiveFrame frame, NoteEvent event){
  println("fa event");
}

public void slAction(InteractiveFrame frame, NoteEvent event){
  println("sol event");
}

public void laAction(InteractiveFrame frame, NoteEvent event){
  println("la event");
}
public void siAction(InteractiveFrame frame, NoteEvent event){
  println("si event");
}