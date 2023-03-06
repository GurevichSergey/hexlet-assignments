package exercise;

// BEGIN
public class Flat implements Home {
    double area;
    double balconyArea;
    int floor;
    public Flat(double area, double balconyArea, int floor) {
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
    }
    public double getArea() {
        return area + balconyArea;
    }
    public int compareTo(Home another) {
        var fullAreaAnother= another.getArea();
        var fullAreaThis = this.getArea();
        if(fullAreaThis > fullAreaAnother) {
            return 1;
        }
        else if(fullAreaThis < fullAreaAnother) {
            return -1;
        }
        else {
            return 0;
        }
    }
    public String toString() {
        return ("Квартира площадью " + getArea() + " метров на " + floor + " этаже");

    }
}
// END
