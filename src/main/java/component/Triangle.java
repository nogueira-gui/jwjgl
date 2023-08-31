package component;

public class Triangle {

    Point pointA;
    Point pointB;
    Point pointC;

    public Triangle(Point a, Point b, Point c) {
        this.pointA = a;
        this.pointB = b;
        this.pointC = c;
        if (!validate(a, b, c)) {
            throw new IllegalArgumentException("Invalid triangle");
        }
    }

    public boolean validate(Point a, Point b, Point c) {
        final float side_ab = distance(a, b);
        final float side_bc = distance(b, c);
        final float side_ca = distance(c, a);

        return side_ab + side_bc > side_ca && side_bc + side_ca > side_ab && side_ca + side_ab > side_bc;
    }

    private float distance(Point p1, Point p2) {
        final float distance = (float) Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
        return distance;
    }

    public Point getPointA() {
        return pointA;
    }

    public Point getPointB() {
        return pointB;
    }

    public Point getPointC() {
        return pointC;
    }
}