import remixlab.bias.*;

public class NoteShortcut extends Shortcut {
  public NoteShortcut(int m, int id) {
    super(m, id);
  }

  @Override
  public Class eventClass() {
    return NoteEvent.class;
  }

  public static int registerID(int id, String description) {
    return Shortcut.registerID(NoteShortcut.class, id, description);
  }

  public static int registerID(String description) {
    return Shortcut.registerID(NoteShortcut.class, description);
  }

  public static boolean hasID(int id) {
    return Shortcut.hasID(NoteShortcut.class, id);
  }
}