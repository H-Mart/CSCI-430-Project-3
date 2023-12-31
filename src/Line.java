import java.awt.*;

public class Line extends Item {
    private Point point1;
    private Point point2;

    public Line(Point point1, Point point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    public Line(Point point1) {
        this.point1 = point1;
        point2 = null;
    }

    public Line() {
        point1 = null;
        point2 = null;
    }

    public boolean includes(Point point) {
        return ((distance(point, point1) < 10.0) || (distance(point, point2) < 10.0));
    }

    public void render(UIContext uiContext) {
        uiContext.drawLine(point1, point2);
    }

    public Point getPoint1() {
        return point1;
    }

    public void setPoint1(Point point) {
        point1 = point;
    }

    public Point getPoint2() {
        return point2;
    }

    public void setPoint2(Point point) {
        point2 = point;
    }

    public String toString() {
        return "Line  from " + point1 + " to " + point2;
    }

    public void translate(int deltaX, int deltaY) {
        point1.translate(deltaX, deltaY);
        point2.translate(deltaX, deltaY);

        System.out.println(point1.getX());
        System.out.println(point1.getY());
        System.out.println();
        System.out.println(point2.getX());
        System.out.println(point2.getY());
    }
}

