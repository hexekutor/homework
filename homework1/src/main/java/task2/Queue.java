package task2;



import java.util.Objects;
import java.util.Stack;

public class Queue implements QueueInterface<String> {

    private Stack<String> firstStack = new Stack<>();
    private Stack<String> secondStack = new Stack<>();

    Queue() {
    }

    @Override
    public boolean add(String o) {
        firstStack.push(o);
        return Objects.equals(firstStack.peek(), o);
    }

    @Override
    public String poll() {
        if(firstStack.size() == 1){
            return firstStack.pop();
        }
        while(!firstStack.isEmpty()){
            secondStack.add(firstStack.pop());
        }
        String polled = secondStack.pop();
        while(!secondStack.isEmpty()){
            firstStack.add(secondStack.pop());
        }
        return polled;
    }
}
