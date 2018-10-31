package task2;

/**
 * Created by User on 03.10.2018.
 */
public interface QueueInterface<E> {
    boolean add(E e);

    E poll();
}
