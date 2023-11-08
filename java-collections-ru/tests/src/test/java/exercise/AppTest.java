package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void testTake() {
        // BEGIN
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 3, 3, 4, 5, 10));
        assertThat(App.take(list, 3).size()).isEqualTo(3);
        assertThat(App.take(list, 0).size()).isEqualTo(0);
        assertThat(App.take(list, list.size() + 1).size()).isEqualTo(list.size());
       // assertThat(App.take(list, list.size()).hashCode()).isNotEqualByComparingTo(list.hashCode());
        // END
    }
}
