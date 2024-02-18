package exercise;

import java.text.DecimalFormat;

// BEGIN
public class Flat implements Home{
    private final double area;
    private final double balconyArea;
    private final int floor;

    Flat(double area, double balconyArea, int floor) {
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
    }
    @Override
    public double getArea() {
        return area + balconyArea;
    }

    @Override
    public int compareTo(Home another) {
        if (getArea() > another.getArea()) return 1;
        if (getArea() < another.getArea()) return -1;
        return 0;
    }

    private int getFloor() {
        return floor;
    }
    @Override
    public String toString(){
        return String.format("Квартира площадью %s метров на %d этаже" , areaFormat(getArea()), getFloor());
    }


}
// END
