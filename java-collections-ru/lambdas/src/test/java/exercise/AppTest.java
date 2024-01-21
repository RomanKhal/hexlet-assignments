package exercise;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


// BEGIN
class AppTest {
    @Test
    void testAppMatrix4x4() {
        String[][] image = {
                {"*", "*", "*", "*"},
                {"*", " ", " ", "*"},
                {"*", " ", " ", "*"},
                {"*", "*", "*", "*"},
        };
        String[][] actual = App.enlargeArrayImage(image);
        String[][] expected = {
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
        };
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testAppWithEmptymatrix() {
        String[][] image = {};
        String[][] actual = App.enlargeArrayImage(image);
        String[][] expected = {};
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testAppMatrixSimple() {
        String[][] image = {
                {"*"}
        };
        String[][] actual = App.enlargeArrayImage(image);
        String[][] expected = {
                {"*", "*"},
                {"*", "*"}
        };
        assertThat(actual).isEqualTo(expected);
    }
}
// END
