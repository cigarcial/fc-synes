import remixlab.bias.*;

public class NoteEvent extends BogusEvent {

  public NoteEvent(int id) {
    super(BogusEvent.NO_MODIFIER_MASK, id);
  }
  
  @Override
  public NoteShortcut shortcut() {
    return new NoteShortcut(modifiers(), id());
  }
  
}