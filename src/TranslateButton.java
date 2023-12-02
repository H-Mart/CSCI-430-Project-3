import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TranslateButton extends JButton implements ActionListener {
    private final TranslateButton.MouseHandler mouseHandler;
    private final UndoManager undoManager;
    protected JPanel drawingPanel;
    protected View view;
    private TranslateCommand translateCommand;

    public TranslateButton(UndoManager undoManager, View jFrame, JPanel jPanel) {
        super("Translate");
        this.undoManager = undoManager;
        addActionListener(this);
        view = jFrame;
        drawingPanel = jPanel;
        mouseHandler = new TranslateButton.MouseHandler();
    }

    public void actionPerformed(ActionEvent event) {
        view.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        // Change cursor when button is clicked
        drawingPanel.addMouseListener(mouseHandler);
        drawingPanel.addMouseMotionListener(mouseHandler);
        // Start listening for mouseclicks on the drawing panel
    }

    private class MouseHandler extends MouseAdapter {
        private Boolean isDragging = false;

        public void mousePressed(MouseEvent event) {
            if (!isDragging) {
                int x1 = Math.round((float) (View.mapPoint(event.getPoint()).getX()));
                int y1 = Math.round((float) (View.mapPoint(event.getPoint()).getY()));
                translateCommand = new TranslateCommand(x1, y1);
                undoManager.beginCommand(translateCommand);
                isDragging = true;
            }
        }

        public void mouseReleased(MouseEvent event) {
            int x2 = Math.round((float) (View.mapPoint(event.getPoint()).getX()));
            int y2 = Math.round((float) (View.mapPoint(event.getPoint()).getY()));
            translateCommand.setFinalX(x2);
            translateCommand.setFinalY(y2);
            drawingPanel.removeMouseListener(this);
            view.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            undoManager.endCommand(translateCommand);
            isDragging = false;
        }

//        public void mouseDragged(MouseEvent event) {
//            if (isDragging) {
//                int x2 = Math.round((float) (View.mapPoint(event.getPoint()).getX()));
//                int y2 = Math.round((float) (View.mapPoint(event.getPoint()).getY()));
//                translateCommand.setDeltaX(x2 - x1);
//                translateCommand.setDeltaY(y2 - y1);
//                translateCommand.addToFinalDeltaX(x2 - x1);
//                translateCommand.addToFinalDeltaY(y2 - y1);
//                undoManager.endCommand(translateCommand);
//                undoManager.beginCommand(translateCommand);
//                x1 = x2;
//                y1 = y2;
//            }
//        }
    }
}