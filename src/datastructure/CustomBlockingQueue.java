package datastructure;

import message.Message;

public class CustomBlockingQueue {
    private final Message[] buffer;
    private int head = 0, tail = 0, size = 0;
    private final int capacity;

    public CustomBlockingQueue(int capacity) {
        this.capacity = capacity;
        buffer = new Message[capacity];
    }

    public synchronized void put(Message message) throws InterruptedException {
        while (size == capacity) {
            System.out.println("Queue is full waiting!");
            wait();
        }
        buffer[tail] = message;
        tail = (tail + 1) % capacity;
        size++;
        notifyAll();
    }

    public synchronized Message take() throws InterruptedException {
        while (size == 0) {
            System.out.println("Queue is empty waiting!");
            wait();
        }
        Message message = buffer[head];
        head = (head + 1) % capacity;
        size--;
        notifyAll();
        return message;
    }
}
