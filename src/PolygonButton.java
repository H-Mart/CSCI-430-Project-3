import javax.swing.*;
import java.awt.event.ActionListener;

public class PolygonButton extends JButton implements ActionListener {
    private final MouseHandler mouseHandler;
    private final UndoManager undoManager;
    protected JPanel drawingPanel;
    protected View view;
    private PolygonCommand polygonCommand;

    public PolygonButton(UndoManager undoManager, View jFrame, JPanel jPanel) {
        super("Polygon");
        this.undoManager = undoManager;
        addActionListener(this);
        view = jFrame;
        drawingPanel = jPanel;
        mouseHandler = new MouseHandler();
    }

    public void actionPerformed(java.awt.event.ActionEvent event) {
        view.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        // Change cursor when button is clicked
        drawingPanel.addMouseListener(mouseHandler);
        // Start listening for mouseclicks on the drawing panel
    }

    private class MouseHandler extends java.awt.event.MouseAdapter {
        private int pointCount = 0;

        // if left mouse button clicked, add point to polygon
        // if right mouse button clicked, end polygon
        public void mouseClicked(java.awt.event.MouseEvent event) {
            if (event.getButton() == java.awt.event.MouseEvent.BUTTON1) {
                if (++pointCount == 1) {
                    polygonCommand = new PolygonCommand(View.mapPoint(event.getPoint()));
                    undoManager.beginCommand(polygonCommand);
                } else {
                    polygonCommand.setPolygonPoint(View.mapPoint(event.getPoint()));
                }
            } else if (event.getButton() == java.awt.event.MouseEvent.BUTTON3) {
                pointCount = 0;
                if (polygonCommand.end()) {
                    drawingPanel.removeMouseListener(this);
                    view.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                    undoManager.endCommand(polygonCommand);
                }
            }
        }
    }
}
