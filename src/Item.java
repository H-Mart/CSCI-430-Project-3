import java.io.*;
import java.awt.*;

public abstract class Item implements Serializable {
    public abstract boolean includes(Point point);

    protected double distance(Point point1, Point point2) {
        double xDifference = point1.getX() - point2.getX();
        double yDifference = point1.getY() - point2.getY();
        return Math.sqrt(xDifference * xDifference + yDifference * yDifference);
    }

    public abstract void render(UIContext uiContext);

    public abstract void translate(int deltaX, int deltaY);
}
