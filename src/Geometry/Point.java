package Geometry;

public class Point {
    public double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
        x = 0;
        y = 0;
    }

    public void offsetPoint(double dx, double dy) {
         x += dx;
         y += dy;
    }


    public void setPoint(double deltaX, double deltaY) {
        x = deltaX;
        y = deltaY;
    }




    @Override
    public String toString() {
        return " (" + x + "," + y + ")";
    }
}
