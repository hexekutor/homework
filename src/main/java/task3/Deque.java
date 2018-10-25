package task3;

import java.util.Stack;

public class Deque implements DequeInterface<String> {
    private Stack<String> firstStack = new Stack<>();
    private Stack<String> secondStack = new Stack<>();

    Deque() {
    }

    @Override
    public boolean add(String s, boolean first) {
        Integer size = firstStack.size();

        if(first){
            while(!firstStack.isEmpty()){
                secondStack.add(firstStack.pop());
            }
            firstStack.push(s);
            while(!secondStack.isEmpty()){
                firstStack.add(secondStack.pop());
            }
        }else{
            firstStack.push(s);
        }

        return size == firstStack.size() - 1;
    }

    @Override
    public String poll(boolean first) {
        if(firstStack.size() == 1){
            return firstStack.pop();
        }

        String polled;

        if(first){
            while(!firstStack.isEmpty()){
                secondStack.add(firstStack.pop());
            }
            polled = secondStack.pop();
            while(!secondStack.isEmpty()){
                firstStack.add(secondStack.pop());
            }
        }else{
            polled = firstStack.pop();
        }

        return polled;
    }
}
