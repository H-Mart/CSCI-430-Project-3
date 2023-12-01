import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TriangleButton extends JButton implements ActionListener {
    private final TriangleButton.MouseHandler mouseHandler;
    private final UndoManager undoManager;
    protected JPanel drawingPanel;
    protected View view;
    private TriangleCommand triangleCommand;

    public TriangleButton(UndoManager undoManager, View jFrame, JPanel jPanel) {
        super("Triangle");
        this.undoManager = undoManager;
        addActionListener(this);
        view = jFrame;
        drawingPanel = jPanel;
        mouseHandler = new TriangleButton.MouseHandler();
    }

    public void actionPerformed(ActionEvent event) {
        view.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        // Change cursor when button is clicked
        drawingPanel.addMouseListener(mouseHandler);
        // Start listening for mouseclicks on the drawing panel
    }

    private class MouseHandler extends MouseAdapter {
        private int pointCount = 0;

        public void mouseClicked(MouseEvent event) {
            if (++pointCount == 1) {
                triangleCommand = new TriangleCommand(View.mapPoint(event.getPoint()));
                undoManager.beginCommand(triangleCommand);
            } else if (pointCount == 2) {
                triangleCommand.setTrianglePoint(View.mapPoint(event.getPoint()));
            } else if (pointCount == 3) {
                pointCount = 0;
                triangleCommand.setTrianglePoint(View.mapPoint(event.getPoint()));
                drawingPanel.removeMouseListener(this);
                view.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                undoManager.endCommand(triangleCommand);
            }
        }
    }
}