import java.awt.*;

public class PolygonCommand extends Command {
    private final Polygon polygon;
    private int pointCount;

    public PolygonCommand(Point point) {
        polygon = new Polygon(point);
        pointCount = 1;
    }

    public void setPolygonPoint(Point point) {
        polygon.addPoint(point);
        pointCount++;
        model.setChanged();
    }

    public void execute() {
        model.addItem(polygon);
    }

    public boolean undo() {
        model.removeItem(polygon);
        return true;
    }

    public boolean redo() {
        execute();
        return true;
    }

    public boolean end() {
        if (pointCount < 3) {
            undo();
            return false;
        }
        polygon.setComplete();
        return true;
    }
}
