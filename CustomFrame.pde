import remixlab.proscene.*;
import remixlab.bias.*;
import remixlab.bias.event.*;
import remixlab.dandelion.geom.*;
import remixlab.dandelion.core.*;

public class CustomFrame extends InteractiveFrame{
  
  private int id;
  private int type;
  
  public CustomFrame(Scene scn,int id,float x,float y,int t){
    super(scn);
    this.id = id; 
    setPickingPrecision(InteractiveFrame.PickingPrecision.ADAPTIVE);
    setGrabsInputThreshold(scn.radius() / 4);
    translate(x,y);
    type = t;
  }
  
  @Override
  public boolean equals(Object o){
    CustomFrame c = (CustomFrame)o;
    return this.id == c.id;
  }
  
  void draw(){
    pushMatrix();
    applyTransformation();
    box(20);
    popMatrix();
  }
}