import remixlab.bias.Agent;
import remixlab.proscene.Scene;
import processing.core.PApplet;
import java.util.*;
import fcsynec.core.*;

public class MusicAgent extends Agent {

  public static final int NoteShortcut_NO = NoteShortcut.registerID(0, "NoteShortcut_NO");
  public static final int NoteShortcut_DO = NoteShortcut.registerID(1, "NoteShortcut_DO");
  public static final int NoteShortcut_RE = NoteShortcut.registerID(2, "NoteShortcut_RE");
  public static final int NoteShortcut_MI = NoteShortcut.registerID(3, "NoteShortcut_MI");
  public static final int NoteShortcut_FA = NoteShortcut.registerID(4, "NoteShortcut_FA");
  public static final int NoteShortcut_SL = NoteShortcut.registerID(5, "NoteShortcut_SL");
  public static final int NoteShortcut_LA = NoteShortcut.registerID(6, "NoteShortcut_LA");
  public static final int NoteShortcut_SI = NoteShortcut.registerID(7, "NoteShortcut_SI");

  private Scene scene;
  private PApplet applet;
  private Input input;

  public MusicAgent(Scene scn, PApplet p) {
    super(scn.inputHandler());
    scene = scn;
    register();
    addGrabber(scene.eyeFrame());
    setDefaultGrabber(scene.eyeFrame());
    applet = p;
    input = new AudioInput();
  }

  public void register() {
    scene.inputHandler().registerAgent(this);
  }

  public void unregister() {
    scene.inputHandler().unregisterAgent(this);
  }

  //iframes no saben seleccionarse a partir de un NoteEvent 
  //checkIfGrabsInput
  //escribir mis propios iFrames
  //reflexion para check...
  @Override
    public NoteEvent feed() {
    if ( applet.frameCount % 60 == 0) {
      int nx = input.next();
      if (nx != 0)
        System.out.println("Event gathered by MusicAgent is: " + nx);
      if ( nx == 1) {
        return new NoteEvent(NoteShortcut_DO);
      } else if ( nx == 2) {
        return new NoteEvent(NoteShortcut_RE);
      } else if ( nx == 3) {
        return new NoteEvent(NoteShortcut_MI);
      } else if ( nx == 4) {
        return new NoteEvent(NoteShortcut_FA);
      } else if ( nx == 5) {
        return new NoteEvent(NoteShortcut_SL);
      } else if ( nx == 6) {
        return new NoteEvent(NoteShortcut_LA);
      } else if ( nx == 7) {
        return new NoteEvent(NoteShortcut_SI);
      }
      return new NoteEvent(NoteShortcut_NO);
    }
    return new NoteEvent(NoteShortcut_NO);
  }
  
  public void stopRecording(){
    //input.stopRec();
  }
}