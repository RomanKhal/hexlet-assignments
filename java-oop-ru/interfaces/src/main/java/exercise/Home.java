package exercise;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

// BEGIN
public interface Home  {
    double getArea();
    int compareTo(Home another);

    public default String areaFormat(double area) {
        DecimalFormat df = new DecimalFormat("0.0");
        df.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.US));
        return String.format("%s",df.format(area));
    }
}
// END
