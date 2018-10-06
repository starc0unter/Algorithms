package ru.chentsov.algorithms.hw;

public class PriorityQueue {

    private int[] queue;
    private int head;
    private int tail;
    private int size;
    private int items;

    public PriorityQueue(int size) {
        this.size = size;
        queue = new int[size];
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

    public void insert(int value) {
        if (isEmpty()) {
            queue[++tail] = value;
            items++;
        } else {
            if (isFull()) increaseSize(2);
            if (tail == size - 1) tail = -1;
            queue[++tail] = value;
            items++;
            prioritizeItem();
        }
    }

    private void prioritizeItem() {
        int current = tail;
        int next = tail;
        for (int i = 0; i < items - 1; i++) {
            next = (current == 0) ? size - 1 : next - 1;
            if (queue[current] < queue[next]) {
                int temp = queue[current];
                queue[current] = queue[next];
                queue[next] = temp;
                current = next;
            } else break;
        }
    }

    private void increaseSize(int factor) {
        size *= factor;
        int[] temp = new int[size];
        if (tail >= head) {
            System.arraycopy(queue, 0, temp, 0, queue.length);
        } else {
            System.arraycopy(queue, 0, temp, 0, tail + 1);
            System.arraycopy(queue, head, temp,
                    size - (queue.length - head),
                    queue.length - head - 1);
            head = size - head - 1;
        }
        queue = temp;
    }

    public int remove() {
        if (isEmpty())
            throw new RuntimeException("Queue is empty");
        int temp = queue[head++];
        head %= size;
        items--;
        return temp;
    }

    public int peek() {
        if (isEmpty())
            throw new RuntimeException("Queue is empty");
        return queue[head];
    }

    public static void main(String[] args) {
        PriorityQueue priorityQueue = new PriorityQueue(2);

        priorityQueue.insert(7);
        priorityQueue.insert(4);
        priorityQueue.insert(2);
        priorityQueue.insert(6);
        priorityQueue.insert(3);
        priorityQueue.insert(8);
        priorityQueue.insert(9);
        priorityQueue.insert(5);
        priorityQueue.insert(1);
        priorityQueue.insert(10);

        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.remove());
        }
    }

}
