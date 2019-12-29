package Geometry;

public class Line {
    public Point startPoint;
    public Point endPoint;


    public Line(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }


    public Line(double x1, double y1, double x2, double y2) {
        startPoint = new Point(x1, y1);
        endPoint = new Point(x2, y2);
    }





    public double getLength() {
        return Math.hypot(startPoint.x - endPoint.x, startPoint.y - endPoint.y);
    }


    public double getSlope() {
        if(startPoint.x == endPoint.x) {
            startPoint.x += 0.003;
        }

        if(startPoint.y == endPoint.y) {
            startPoint.y += 0.003;
        }

        return (startPoint.y - endPoint.y) / (startPoint.x = endPoint.x);
    }






    public void shiftLine(double d) {
        startPoint = extendLine(startPoint, d, getSlope());
        endPoint = extendLine(endPoint, d, getSlope());
    }


    public Point extendLine(Point startPoint, double d, double m) {
        double x = startPoint.x + (d / Math.sqrt(1 + m * m));
        double y = m * (x - startPoint.x) + startPoint.y;

        return new Point(x , y);
    }



    public Line getParallelLine(double d) {
        Point startingPoint = extendLine(startPoint, d, -1/getSlope());
        Point endingPoint = extendLine(endPoint, d, -1/getSlope());

        return new Line(startingPoint, endingPoint);
    }










    @Override
    public String toString() {
        return "[" + startPoint + " , " + endPoint + "]";
    }
}
