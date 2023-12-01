import javax.swing.*;
import java.awt.event.*;

public class SelectButton extends JButton implements ActionListener {
    private final MouseHandler mouseHandler;
    private final UndoManager undoManager;
    protected JPanel drawingPanel;
    protected View view;
    private SelectCommand selectCommand;

    public SelectButton(UndoManager undoManager, View jFrame, JPanel jPanel) {
        super("Select");
        addActionListener(this);
        view = jFrame;
        drawingPanel = jPanel;
        this.undoManager = undoManager;
        mouseHandler = new MouseHandler();
    }

    public void actionPerformed(ActionEvent event) {
        selectCommand = new SelectCommand();
        drawingPanel.addMouseListener(mouseHandler);
        undoManager.beginCommand(selectCommand);
    }

    private class MouseHandler extends MouseAdapter {
        public void mouseClicked(MouseEvent event) {
            if (selectCommand.setPoint(View.mapPoint(event.getPoint()))) {
                drawingPanel.removeMouseListener(this);
                undoManager.endCommand(selectCommand);
            } else {
                undoManager.cancelCommand();
            }
        }
    }
}
