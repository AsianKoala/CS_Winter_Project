package Geometry;

import java.util.ArrayList;

public class Triangle {
    public Point top, left, right;

    public Triangle(Point top, Point left, Point right) {
        this.top = top;
        this.left = left;
        this.right = right;
    }


    public Triangle() {
        top = new Point();
        left = new Point();
        right = new Point();
    }




    public double centroidX() {
        return (top.x + left.x + right.x)/3;
    }

    public double centroidY() {
        return (top.y + left.y + right.y)/3;
    }

    public Point centroid() {
        return new Point(centroidX(), centroidY());
    }



    public Point[] allVertices() {
        return new Point[] {
                top,
                left,
                right
        };
    }


    public void offset(double x, double y) {
        for(int i=0; i<3; i++) {
            allVertices()[i].offsetPoint(x,y);
        }
    }
}
