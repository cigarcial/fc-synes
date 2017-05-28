import remixlab.proscene.*;
import remixlab.bias.*;
import remixlab.bias.event.*;
import remixlab.dandelion.geom.*;
import remixlab.dandelion.core.*;

public class CustomFrame extends InteractiveFrame{
  
  private int id;
  public CustomFrame(Scene scn,int id,int x,int y){
    super(scn);
    this.id = id; 
    setPickingPrecision(InteractiveFrame.PickingPrecision.ADAPTIVE);
    setGrabsInputThreshold(scn.radius() / 4);
    translate(x,y);
  }
  
  
  @Override
  public boolean equals(Object o){
    CustomFrame c = (CustomFrame)o;
    return this.id == c.id;
  }
  
  void draw(){
    //println("OK");
    pushMatrix();
    applyTransformation();
    box(50);
    popMatrix();
  }
}