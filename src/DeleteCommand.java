import java.util.*;

class DeleteCommand extends Command {
    private final Vector<Item> itemList;

    public DeleteCommand() {
        itemList = new Vector<>();
        Enumeration<Item> enumeration = model.getSelectedItems();
        while (enumeration.hasMoreElements()) {
            Item item = enumeration.nextElement();
            itemList.add(item);
        }
//        model.deleteSelectedItems();
    }

    public boolean undo() {
        Enumeration<Item> enumeration = itemList.elements();
        while (enumeration.hasMoreElements()) {
            Item item = enumeration.nextElement();
            model.addItem(item);
            model.markSelected(item);
        }
        return true;
    }

    public boolean redo() {
        execute();
        return true;
    }

    public void execute() {
        model.deleteSelectedItems();
    }
}
