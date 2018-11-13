import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PositiveUnitTests {

    @Test
    public void test_assertListEquals(){
        final Integer N = 14;
        Integer[] expected = {2,3,5,7,11,13};
        List<Integer> expectedList= Arrays.asList(expected);
        List<Integer> actual = Main.findPrimes(N);
        assertEquals(String.format("должны быть найдены простые числа до %d", N), expectedList, actual);
    }
    @Test
    public void test_assertEmpty(){
        final Integer N = 0;
        List<Integer> expected = new ArrayList<>();
        List<Integer> actual = Main.findPrimes(N);
        assertEquals("должен быть пустым", expected, actual);
    }
}
