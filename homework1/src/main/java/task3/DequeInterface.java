package task3;

public interface DequeInterface<E> {
    boolean add(E e, boolean first);

    E poll(boolean first);
}
