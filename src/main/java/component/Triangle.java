package component;

public class Triangle {

    Point pointA;
    Point pointB;
    Point pointC;

    String type;

    public Triangle(Point a, Point b, Point c) {
        this.pointA = a;
        this.pointB = b;
        this.pointC = c;
        validate(a, b, c); 
    }

    private void validate(Point a, Point b, Point c) {
        final float side_ab = distance(a, b);
        final float side_bc = distance(b, c);
        final float side_ca = distance(c, a);

        final var isValid = side_ab + side_bc > side_ca && side_bc + side_ca > side_ab && side_ca + side_ab > side_bc;

        if (!isValid) {
            throw new IllegalArgumentException("Invalid triangle");
        }
    
        this.type = this.checkType(side_ab, side_bc, side_ca);

    }

    private float distance(Point p1, Point p2) {
        final float distance = (float) Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
        return distance;
    }

    private String checkType(float side_ab, float side_bc, float side_ca) {
        if (side_ab == side_bc && side_bc == side_ca) {
            return  "equilateral";
        }
        if (side_ab == side_bc || side_bc == side_ca || side_ca == side_ab) {
            return "isosceles";
        }
        return "scalene";
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

    public String getType() {
        return type;
    }
}