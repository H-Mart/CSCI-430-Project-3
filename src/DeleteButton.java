import javax.swing.*;
import java.awt.event.*;

public class DeleteButton extends JButton implements ActionListener {
    private final UndoManager undoManager;
    private DeleteCommand deleteCommand;

    public DeleteButton(UndoManager undoManager) {
        super("Delete");
        this.undoManager = undoManager;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent event) {
        deleteCommand = new DeleteCommand();
        undoManager.beginCommand(deleteCommand);
        undoManager.endCommand(deleteCommand);
    }
}
