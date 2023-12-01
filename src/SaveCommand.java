public class SaveCommand extends Command {
    private final String fileName;

    public SaveCommand(String fileName) {
        this.fileName = fileName;
    }

    public void execute() {
        model.save(fileName);
    }

    public boolean undo() {
        return false;
    }

    public boolean redo() {
        return false;
    }
}
