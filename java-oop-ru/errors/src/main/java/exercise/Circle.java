package exercise;

// BEGIN
public class Circle {
    private Point point;
    private int radius;
    public Circle(Point point, int radius) {
        this.point = point;
        this.radius = radius;
    }
    public int getRadius() {
        return this.radius;
    }
    public double getSquare() throws NegativeRadiusException {
        if (getRadius() < 0) {
            throw new NegativeRadiusException();
        }
        return getRadius() * getRadius() * Math.PI;
    }
}
// END
