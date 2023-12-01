import java.awt.*;

public class Triangle extends Item {
    private Point point1;
    private Point point2;
    private Point point3;

    public Triangle(Point point1, Point point2, Point point3) {
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
    }

    public boolean includes(Point point) {
        return ((distance(point, point1) < 10.0) ||
                (distance(point, point2) < 10.0) ||
                (distance(point, point3) < 10.0));
    }

    public void render(UIContext uiContext) {
//        System.out.println("Triangle.render");
        uiContext.drawLine(point1, point2);
        uiContext.drawLine(point2, point3);
        uiContext.drawLine(point3, point1);
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

    public Point getPoint3() {
        return point3;
    }

    public void setPoint3(Point point) {
        point3 = point;
    }

    public String toString() {
        return "Triangle  from " + point1 + " to " + point2 + " to " + point3;
    }

    public void translate(int deltaX, int deltaY) {
        point1.translate(deltaX, deltaY);
        point2.translate(deltaX, deltaY);
        point3.translate(deltaX, deltaY);
    }

}
