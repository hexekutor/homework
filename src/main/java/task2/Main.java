package task2;

public class Main {

    public static void main(String[] args) {
        Queue queue = new Queue();
        queue.add("10");
        queue.add("20");
        queue.add("30");
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        queue.add("40");
        System.out.println(queue.poll());

    }
}
