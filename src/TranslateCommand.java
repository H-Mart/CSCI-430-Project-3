import java.util.Enumeration;
import java.util.Vector;

public class TranslateCommand extends Command {
    private int deltaX = 0;
    private int deltaY = 0;
    private int finalDeltaX = 0;
    private int finalDeltaY = 0;
    private int redoDeltaX = 0;
    private int redoDeltaY = 0;
    private final Vector<Item> itemList;

    public TranslateCommand() {
        itemList = new Vector<>();
        Enumeration<Item> enumeration = model.getSelectedItems();
        while (enumeration.hasMoreElements()) {
            Item item = enumeration.nextElement();
            itemList.add(item);
        }
    }

    public void execute() {
    }

    public boolean undo() {
        if (finalDeltaX == 0 && finalDeltaY == 0) {
            return false;
        }

        Enumeration<Item> enumeration = itemList.elements();
        while (enumeration.hasMoreElements()) {
            Item item = enumeration.nextElement();
            item.translate(-finalDeltaX, -finalDeltaY);
        }

        finalDeltaX = 0;
        finalDeltaY = 0;

        model.setChanged();
        return true;
    }

    public boolean redo() {
        System.out.println("redoDeltaX: " + redoDeltaX + ", redoDeltaY: " + redoDeltaY);
        if (redoDeltaX == 0 && redoDeltaY == 0) {
            return false;
        }

        Enumeration<Item> enumeration = itemList.elements();
        while (enumeration.hasMoreElements()) {
            Item item = enumeration.nextElement();
            item.translate(redoDeltaX, redoDeltaY);
        }

        finalDeltaX += redoDeltaX;
        finalDeltaY += redoDeltaY;

        redoDeltaX = 0;
        redoDeltaY = 0;

        return true;
    }

    public boolean end() {
        Enumeration<Item> enumeration = itemList.elements();
        while (enumeration.hasMoreElements()) {
            Item item = enumeration.nextElement();
            item.translate(deltaX, deltaY);
        }
        return true;
    }

    public void setDeltaX(int deltaX) {
        this.deltaX = deltaX;
    }

    public void setDeltaY(int deltaY) {
        this.deltaY = deltaY;
    }

    public void addToFinalDeltaX(int deltaX) {
        this.finalDeltaX += deltaX;
        this.redoDeltaX += deltaX;
    }

    public void addToFinalDeltaY(int deltaY) {
        this.finalDeltaY += deltaY;
        this.redoDeltaY += deltaY;
    }
}
