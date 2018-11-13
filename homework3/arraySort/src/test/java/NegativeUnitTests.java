import main.Main;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class NegativeUnitTests {
    @Test(expected = NullPointerException.class)
    public void test_assertSortListOfNullLists(){
        List<List<Integer>> checkList = new ArrayList<>();
        checkList.add(null);
        checkList.add(null);
        checkList.add(null);
        List<List<Integer>> actual = Main.sortList(checkList);
    }
    @Test(expected = NullPointerException.class)
    public void test_assertSortNullList(){
        Integer sumOfNullList = Main.sumOfList(null);
    }
}
