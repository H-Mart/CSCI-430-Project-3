import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Polygon extends Item {
    private final ArrayList<Point> points;
    private boolean complete;

    public Polygon(Point point) {
        points = new ArrayList<>();
        points.add(point);
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public void setComplete() {
        this.complete = true;
    }

    public boolean includes(Point point) {
        for (Point p : points) {
            if (distance(point, p) < 10.0) {
                return true;
            }
        }
        return false;
    }

    public void render(UIContext uiContext) {
        Iterator<Point> iterator = points.iterator();
        Point point1 = iterator.next();
        Point point2;
        while (iterator.hasNext()) {
            point2 = iterator.next();
            uiContext.drawLine(point1, point2);
            point1 = point2;
        }

        if (complete) {
            uiContext.drawLine(point1, points.get(0));
        }
    }

    public void translate(int deltaX, int deltaY) {
        for (Point point : points) {
            point.translate(deltaX, deltaY);
        }
    }
}
