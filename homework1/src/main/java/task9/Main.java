package task9;

public class Main {
    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        Thread mythread = new Thread(thread1);
        Thread mythread2 = new Thread(thread2);
        mythread.start();mythread2.start();



    }
}
