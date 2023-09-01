import component.Point;
import component.Triangle;
import component.Window;

public class Application {

    public static void main(String[] args) {
        Triangle triangle = getTriangle();
        System.out.println("The Triangle type is: " + "\n "+triangle.getType());
        Window window = Window.get(triangle);
        window.run();
        System.out.println("Done");
    }

    private static Triangle getTriangle() {
        Point point_a = new Point(0.0F, 0.25F);
        Point point_b = new Point(-0.25F, -0.25F);
        Point point_c = new Point(0.25F, -0.25F);
        return new Triangle(point_a, point_b, point_c);
    }
}
