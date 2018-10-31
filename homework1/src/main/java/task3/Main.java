package task3;

public class Main {

    public static void main(String[] args) {
        Deque deq = new Deque();
        deq.add("10", true);
        deq.add("20", true);
        deq.add("30", false);
        System.out.println(deq.poll(true));
        System.out.println(deq.poll(true));
        deq.add("40", true);
        System.out.println(deq.poll(false));

    }
}
