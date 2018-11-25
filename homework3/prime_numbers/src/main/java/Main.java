import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<Integer> findPrimes(int n){
        ArrayList<Boolean> numberList = new ArrayList<>();
        numberList.ensureCapacity(n + 1);
        int i, j;
        for(i = 0; i <= n; i++)
            numberList.add(true);
        numberList.set(0, false);
        if(n>0)
            numberList.set(1, false);
        for(i = 2; i <= n; i++)
            if(numberList.get(i))
                for(j = i * i; j <= n; j+=i)
                    numberList.set(j, false);
        List<Integer> primeList = new ArrayList<>();
        for(i = 0; i <= n; i++){
            if(numberList.get(i))
                primeList.add(i);
        }
        return primeList;
    }
}
