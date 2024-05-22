package controller.customer;

public class CustomerCartStack <T> {
    private T[] elements;
    private int N;

    public CustomerCartStack(int size) {
        this.elements = (T[]) new Object[size];
    }

    public boolean isEmpty() {
        return N==0;
    }

    public void push(T item) {
        elements[N++] = item;
    }

    public T pop() {
        return elements[--N];
    }
}
