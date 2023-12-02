import java.util.Enumeration;
import java.util.Vector;

public class TranslateCommand extends Command {
    private final int initialX;
    private final int initialY;
    private int movingX;
    private int movingY;
    private int finalX;
    private int finalY;
    private boolean wasUndone = false;

    public TranslateCommand() {
        this(0, 0);
    }

    public TranslateCommand(int initialX, int initialY) {
        this.initialX = initialX;
        this.initialY = initialY;
        this.movingX = initialX;
        this.movingY = initialY;
        this.finalX = initialX;
        this.finalY = initialY;
    }

    public void execute() {
        model.translateSelected(movingX - initialX, movingY - initialY);
    }

    public boolean undo() {
        if (wasUndone) {
            return true;
        }
        model.translateSelected(initialX - finalX, initialY - finalY);
        wasUndone = true;
        return true;
    }

    public boolean redo() {
        if (!wasUndone) {
            return true;
        }
        model.translateSelected(finalX - initialX, finalY - initialY);
        wasUndone = false;
        return true;
    }

    public boolean end() {
        model.translateSelected(finalX - initialX, finalY - initialY);
        return true;
    }

    public void setFinalX(int finalX) {
        this.finalX = finalX;
    }

    public void setFinalY(int finalY) {
        this.finalY = finalY;
    }

}
