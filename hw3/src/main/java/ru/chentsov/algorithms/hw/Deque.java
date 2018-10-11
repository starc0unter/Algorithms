package ru.chentsov.algorithms.hw;

public class Deque {

    private int[] deque;
    private int size;
    private int head;
    private int tail;
    private int items;

    public Deque(int size) {
        this.size = size;
        this.deque = new int[size];
        head = 0;
        tail = -1;
        items = 0;
    }

    public boolean isEmpty() {
        return items == 0;
    }

    public boolean isFull() {
        return items == size;
    }

    public int length() {
        return items;
    }

    public void insertTail(int value) {
        if (isFull()) increaseSize(2);
        if (tail == size - 1) tail = -1;
        deque[++tail] = value;
        items++;
    }

    public void insertHead(int value) {
        if (isFull()) increaseSize(2);
        if (head == 0) head = size;
        deque[--head] = value;
        items++;
    }

    public int removeTail() {
        if (isEmpty())
            throw new RuntimeException("Queue is empty");
        int temp = deque[tail--];
        if (tail == -1) tail = size - 1;
        items--;
        return temp;
    }

    public int removeHead() {
        if (isEmpty())
            throw new RuntimeException("Queue is empty");
        int temp = deque[head++];
        head %= size;
        items--;
        return temp;
    }

    private void increaseSize(int factor) {
        size *= factor;
        int[] temp = new int[size];
        if (tail >= head) {
            System.arraycopy(deque, 0, temp, 0, deque.length);
        } else {
            System.arraycopy(deque, 0, temp, 0, tail + 1);
            System.arraycopy(deque, head, temp,
                    size - (deque.length - head),
                    deque.length - head - 1);
            head = size - head - 1;
        }
        deque = temp;
    }

    public static void main(String[] args) {
        Deque deque = new Deque(6);
        fillDeque(deque);
        while (!deque.isEmpty()) {
            System.out.println(deque.removeHead());
        }

        System.out.println("---------------------------");

        fillDeque(deque);
        while (!deque.isEmpty()) {
            System.out.println(deque.removeTail());
        }
    }

    private static void fillDeque(Deque deque) {
        deque.insertHead(0);
        deque.insertHead(0);
        deque.insertHead(0);

        deque.insertTail(1);

        deque.insertHead(0);
        deque.insertHead(0);

        deque.insertTail(1);
        deque.insertTail(1);
        deque.insertTail(1);

        deque.insertHead(0);
        deque.insertHead(0);
        deque.insertHead(0);

        deque.insertTail(1);
        deque.insertTail(1);
        deque.insertTail(1);
        deque.insertTail(1);

        deque.insertHead(0);
        deque.insertHead(0);
        deque.insertHead(0);
    }

}
