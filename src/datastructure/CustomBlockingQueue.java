package datastructure;

import message.Message;

public class CustomBlockingQueue {
    private final Message[] buffer;
    private int head = -1;
    private int tail = -1;
    private int size = 0;
    private final int capacity;

    public CustomBlockingQueue(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("Capacity must be > 0");
        this.capacity = capacity;
        this.buffer = new Message[capacity];
    }

    public synchronized void put(Message message) throws InterruptedException {
        if (message == null) {
            throw new NullPointerException("Message cannot be null");
        }

        while (size == capacity) {
            System.out.println("Queue is full, waiting to put...");
            wait();
        }

        if (size == 0) {
            head = 0;
            tail = 0;
        } else {
            tail = (tail + 1) % capacity;
        }

        buffer[tail] = message;
        size++;
        notifyAll();
    }

    public synchronized Message take() throws InterruptedException {
        while (size == 0) {
            System.out.println("Queue is empty, waiting to take...");
            wait();
        }

        Message message = buffer[head];
        if (message == null) {
            throw new IllegalStateException("Null message at head index");
        }

        buffer[head] = null;
        size--;

        if (size == 0) {
            head = -1;
            tail = -1;
        } else {
            head = (head + 1) % capacity;
        }

        notifyAll();
        return message;
    }
}
