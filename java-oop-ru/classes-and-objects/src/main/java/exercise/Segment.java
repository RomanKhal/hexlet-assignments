package exercise;

// BEGIN
public class Segment {
    private final Point first;
    private final Point second;

    public Segment(Point first, Point second) {
        this.first = first;
        this.second = second;
    }

    public Point getBeginPoint() {
        return first;
    }

    public Point getEndPoint() {
        return second;
    }
    public Point getMidPoint(){
        int x = (first.getX() + second.getX()) / 2;
        int y = (first.getY()+ second.getY()) / 2;
        return new Point(x, y);
    }
}
// END
