package Geometry;

public class Square extends Rectangle {

    public Square(Line topLine) {
        super(topLine, topLine.getLength());
    }



    public void shiftSquare(double dx, double dy) {
        topLine.startPoint.x += dx;
        topLine.startPoint.y += dy;

        topLine.endPoint.x += dx;
        topLine.endPoint.y += dy;

        bottomLine.startPoint.x += dx;
        bottomLine.startPoint.y += dy;

        bottomLine.endPoint.x += dx;
        bottomLine.endPoint.y += dy;
    }



    public Point centroid() {
        return new Point((topLine.startPoint.x + bottomLine.endPoint.x)/2, (topLine.startPoint.y + bottomLine.endPoint.y)/2);
    }
}
