package Geometry;


public class Triangle {
    public Point top, left, right;

    public Triangle(Point top, Point left, Point right) {
        this.top = top;
        this.left = left;
        this.right = right;
    }


    private double centroidX() {
        return (top.x + left.x + right.x) / 3;
    }

    private double centroidY() {
        return (top.y + left.y + right.y) / 3;
    }

    protected Point centroid() {
        return new Point(centroidX(), centroidY());
    }


    protected Point[] allVertices() {
        return new Point[]{
                top,
                left,
                right
        };
    }


    protected void offset(double x, double y) {
        for (int i = 0; i < 3; i++) {
            allVertices()[i].offsetPoint(x, y);
        }
    }
}
