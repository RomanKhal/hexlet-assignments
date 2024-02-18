package exercise;

import java.text.DecimalFormat;

// BEGIN
public class Cottage implements Home{
    private final double area;
    private final int floorCount;

    Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    @Override
    public String toString() {
        return String.format("%d этажный коттедж площадью %s метров", floorCount, getArea());
    }

    @Override
    public double getArea() {
        return area;
    }

    @Override
    public int compareTo(Home another) {
        if (this.area > another.getArea()) return 1;
        if (this.area < another.getArea()) return -1;
        return 0;
    }
}
// END
