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
  
  for(int i=0;i<5;++i){
    float x = 300*(new Random().nextFloat()-0.5);
    float y = 300*(new Random().nextFloat()-0.5);
    CustomFrame f = new CustomFrame(scene,i+1,x,y,0);
    frames.add(f);
  }
  
  
  /*
  * FRAME BINDING
  */
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
  box(20);
  pushMatrix();
  //frames.get(1).draw();
  scene.drawAxes(10);
  for(int i=1;i<frames.size();++i){
    frames.get(i).draw();
  }
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

public void doAction(InteractiveFrame frame, NoteEvent event){
  //println("do event");
  frames.get(idx).rotate(new Quat(0,0,1,10));
}

public void reAction(InteractiveFrame frame, NoteEvent event){
  //println("re event");
  frames.get(idx).rotate(new Quat(0,0,-1,10));
}

public void miAction(InteractiveFrame frame, NoteEvent event){
  //println("mi event");
  frames.get(idx).translate(10,0);
}

public void faAction(InteractiveFrame frame, NoteEvent event){
  //println("fa event");
  frames.get(idx).translate(-10,0);
}

public void slAction(InteractiveFrame frame, NoteEvent event){
  println("sol event");
  frames.get(idx).scale(0.5);
}

public void laAction(InteractiveFrame frame, NoteEvent event){
  //println("la event");
  idx = (idx+1)%frames.size();
}
public void siAction(InteractiveFrame frame, NoteEvent event){
  //println("si event");
  idx--;
  if( idx < 0){
    idx = frames.size()-1;
  }
}

//ONLY FOR CONTROL
public void noAction(InteractiveFrame frame, NoteEvent event){}

//Handle close event
void exit(){
  MusicAgent.stopRecording();
  println("Ok");
  super.exit();
}