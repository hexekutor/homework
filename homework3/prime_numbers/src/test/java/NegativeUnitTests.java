import org.junit.Test;

import java.util.List;

public class NegativeUnitTests {
    @Test(expected = IndexOutOfBoundsException.class)
    public void test_assertIndexOutOfBoundsException(){
        final Integer N = -1;
        List<Integer> actual = Main.findPrimes(N);
    }
    @Test(expected = NullPointerException.class)
    public void test_assertNullException(){
        final Integer N = null;
        List<Integer> actual = Main.findPrimes(N);
    }
}
