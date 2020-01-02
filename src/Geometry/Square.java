package Geometry;

public class Square extends Rectangle {

    public Square(Line topLine) {
        super(topLine, topLine.getLength());
    }


    public Point centroid() {
        return new Point((topLine.startPoint.x + bottomLine.endPoint.x) / 2, (topLine.startPoint.y + bottomLine.endPoint.y) / 2);
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
