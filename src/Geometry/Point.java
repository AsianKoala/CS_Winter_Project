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


    /**
     *
     * @param d distance of extended point
     * @param m slope of line
     * @return
     */
    public Point findExtendedPoint(double d, double m) {
        if(m != 0) {
            double newX = x + (d / Math.sqrt(1 + m * m));
            double newY = m * (newX - x) + y;

            return new Point(newX, newY);
        }

        else {
            return new Point(x, y + d);
        }
    }




    @Override
    public String toString() {
        return " (" + x + "," + y + ")";
    }
}
