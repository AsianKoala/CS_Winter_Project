package Geometry;

public class Line {
    public Point startPoint;
    public Point endPoint;


    public Line(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }


    double getLength() {
        return Math.hypot(startPoint.x - endPoint.x, startPoint.y - endPoint.y);
    }


    public double getSlope() {
        double slope = (endPoint.y - startPoint.y) / (endPoint.x - startPoint.x);

        if (slope == 0) {
            slope += 0.00001;
        }

        if (startPoint.x - endPoint.x == 0) {
            slope = 0;
        }

        return slope;
    }


    private double getParallelSlope() {
        if (getSlope() == 0) {
            return 0.00001;
        } else {
            return 1 / getSlope();
        }
    }


    double getHeading() {
        double heading = Math.atan2(endPoint.y - startPoint.y, endPoint.x - startPoint.x);

        if (heading < 0) {
            heading += Math.PI * 2;
        }

        return Math.toDegrees(heading);
    }


    public void shiftLine(double d) {
        startPoint = startPoint.findExtendedPoint(d, getSlope());
        endPoint = endPoint.findExtendedPoint(d, getSlope());
    }


    Line getParallelLine(double d) {
        Point startingPoint = startPoint.findExtendedPoint(d, getParallelSlope());
        Point endingPoint = endPoint.findExtendedPoint(d, getParallelSlope());

        return new Line(startingPoint, endingPoint);
    }


    @Override
    public String toString() {
        return "[" + startPoint + " , " + endPoint + "]";
    }
}
