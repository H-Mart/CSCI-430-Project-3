import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class View extends JFrame {
    private static UndoManager undoManager;
    // other buttons to be added as needed;
    private static Model model;
    private final JPanel drawingPanel;
    private final JPanel buttonPanel;
    private final JButton triangleButton;
    private final JButton polygonButton;
    private final JButton lineButton;
    private final JButton deleteButton;
    private final JButton labelButton;
    private final JButton selectButton;
    private final JButton translateButton;
    private final JButton saveButton;
    private final JButton openButton;
    private final JButton undoButton;
    private final JButton redoButton;
    private UIContext uiContext;
    private JPanel filePanel;
    private String fileName;

    public View() {
        super("Drawing Program 1.1  Untitled");
        try {
            var looksAvailable = new HashMap<String, UIManager.LookAndFeelInfo>();
            Arrays.stream(UIManager.getInstalledLookAndFeels())
                    .peek(info -> looksAvailable.put(info.getName(), info));

            if (looksAvailable.containsKey("Windows")) {
                UIManager.setLookAndFeel(looksAvailable.get("Windows").getClassName());
            } else if (looksAvailable.containsKey("GTK+")) {
                UIManager.setLookAndFeel(looksAvailable.get("GTK+").getClassName());
            } else if (looksAvailable.containsKey("Nimbus")) {
                UIManager.setLookAndFeel(looksAvailable.get("Nimbus").getClassName());
            } else {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
            System.out.println("Error setting look and feel");
        }
        fileName = null;
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                System.exit(0);
            }
        });
        this.setUI(NewSwingUI.getInstance());
        drawingPanel = new DrawingPanel();
        buttonPanel = new JPanel();
        Container contentpane = getContentPane();
        contentpane.add(buttonPanel, "North");
        contentpane.add(drawingPanel);
        lineButton = new LineButton(undoManager, this, drawingPanel);
        triangleButton = new TriangleButton(undoManager, this, drawingPanel);
        polygonButton = new PolygonButton(undoManager, this, drawingPanel);
        labelButton = new LabelButton(undoManager, this, drawingPanel);
        selectButton = new SelectButton(undoManager, this, drawingPanel);
        deleteButton = new DeleteButton(undoManager);
        translateButton = new TranslateButton(undoManager, this, drawingPanel);
        saveButton = new SaveButton(undoManager, this);
        openButton = new OpenButton(undoManager, this);
        undoButton = new UndoButton(undoManager);
        redoButton = new RedoButton(undoManager);
        buttonPanel.add(lineButton);
        buttonPanel.add(triangleButton);
        buttonPanel.add(polygonButton);
        buttonPanel.add(labelButton);
        buttonPanel.add(selectButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(translateButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(openButton);
        buttonPanel.add(undoButton);
        buttonPanel.add(redoButton);
        this.setSize(600, 400);
    }

    public static void setModel(Model model) {
        View.model = model;
    }

    public static void setUndoManager(UndoManager undoManager) {
        View.undoManager = undoManager;
    }

    public static Point mapPoint(Point point) {
        // maps a point on the drawing panel to a point
        // on the figure being created. Perhaps this
        // should be in drawing panel
        return point;
    }

    public UIContext getUI() {
        return uiContext;
    }

    private void setUI(UIContext uiContext) {
        this.uiContext = uiContext;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
        setTitle("Drawing Program 1.1  " + fileName);
    }

    public void refresh() {
        // code to access the Model update the contents of the drawing panel.
        drawingPanel.repaint();
    }

    private class DrawingPanel extends JPanel {
        private MouseListener currentMouseListener;
        private KeyListener currentKeyListener;
        private FocusListener currentFocusListener;

        public DrawingPanel() {
            setLayout(null);
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            (NewSwingUI.getInstance()).setGraphics(g);
            g.setColor(Color.BLUE);
            Enumeration<Item> enumeration = model.getItems();
            while (enumeration.hasMoreElements()) {
                enumeration.nextElement().render(uiContext);
            }
            g.setColor(Color.RED);
            enumeration = model.getSelectedItems();
            while (enumeration.hasMoreElements()) {
                enumeration.nextElement().render(uiContext);
            }
        }

        public void addMouseListener(MouseListener newListener) {
            removeMouseListener(currentMouseListener);
            currentMouseListener = newListener;
            super.addMouseListener(newListener);
        }

        public void addKeyListener(KeyListener newListener) {
            removeKeyListener(currentKeyListener);
            currentKeyListener = newListener;
            super.addKeyListener(newListener);
        }

        public void addFocusListener(FocusListener newListener) {
            removeFocusListener(currentFocusListener);
            currentFocusListener = newListener;
            super.addFocusListener(newListener);
        }
    }
}
