package Geometry;

public class BetterRectangle {
    public Line topLine, bottomLine;



    public BetterRectangle() {

    }


    public BetterRectangle(Line topLine, Line bottomLine) {
        this.topLine = topLine;
        this.bottomLine = bottomLine;
    }


    public BetterRectangle(Line topLine, double height) {
        this.topLine = topLine;
        bottomLine = topLine.getParallelLine(height);
    }



    public void shiftRect(double d) {
        topLine.shiftLine(d);
        bottomLine.shiftLine(d);
    }


    @Override
    public String toString() {
        return "[" + topLine.startPoint + " , " + bottomLine.startPoint + " , " + topLine.endPoint + " , " + bottomLine.endPoint + " ] ";
    }
}
