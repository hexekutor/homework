import main.Main;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.OrderingComparison.greaterThanOrEqualTo;

public class PositiveUnitTests{

    @Test
    public void test_assertSumOfList(){
        final Integer[] array= {1, 2, 5,10,-2};
        final List<Integer> list = Arrays.asList(array);
        Integer sum = 0;

        for(Integer numb : list)
            sum+=numb;

        assertThat(Main.sumOfList(list), is(sum));
    }
    @Test
    public void test_assertSumOfEmptyList(){
        final List<Integer> list = new ArrayList<>();
        assertThat(Main.sumOfList(list), is(0));
    }
    @Test
    public void test_assertSortList(){
        List<List<Integer>> checkList = new ArrayList<>();
        checkList.add(Collections.singletonList(2));
        checkList.add(Collections.singletonList(1));
        checkList.add(Collections.singletonList(4));
        checkList.add(Collections.singletonList(3));

        List<List<Integer>> sortedList = Main.sortList(checkList);

        assertThat(sortedList, hasSize(checkList.size()));

        for(int i = 1; i < checkList.size(); i++){
            assertThat(Main.sumOfList(sortedList.get(i - 1)),
                    greaterThanOrEqualTo(Main.sumOfList(sortedList.get(i))));

        }
    }
    @Test
    public void test_assertSortEmptyList(){
        List<List<Integer>> checkList = new ArrayList<>();
        List<List<Integer>> sortedList = Main.sortList(checkList);

        assertThat(sortedList, empty());
    }
}
