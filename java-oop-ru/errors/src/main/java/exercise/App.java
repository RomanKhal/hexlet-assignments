package exercise;

// BEGIN
public class App {

    public static void main(String[] args) {
        Circle circle = new Circle(new Point(1, 2), -12);
        printSquare(circle);
    }

    public static void printSquare(Circle circle) {
        try {
            System.out.println(Math.round(circle.getSquare()));
        } catch (NegativeRadiusException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Вычисление окончено");
        }
    }
}
// END
