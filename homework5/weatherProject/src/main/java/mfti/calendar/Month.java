package mfti.calendar;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Month {
    private static final String[] months = {"Январь", "Февраль", "Март", "Апрель",
                        "Май", "Июнь", "Июль", "Август",
                        "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
    private static final List<String> monthsList = Arrays.asList(months);

    private static Function<Integer, String> monthName = monthsList::get;
    private static Function<String, Integer> monthNumber = monthsList::indexOf;
    public static boolean checkMonthChangesToPrevious(String monthBeforeString, String monthAfterString){
        Integer monthBefore = monthNumber.apply(monthBeforeString);
        Integer monthAfter = monthNumber.apply(monthAfterString);
        return monthBefore == 0 && monthAfter == 11 || monthBefore - monthAfter == 1;
    }
    public static boolean checkMonthChangesToNext(String monthBeforeString, String monthAfterString){
        Integer monthBefore = monthNumber.apply(monthBeforeString);
        Integer monthAfter = monthNumber.apply(monthAfterString);
        return monthBefore == 11 && monthAfter == 0 || monthAfter - monthBefore == 1;

    }
}
