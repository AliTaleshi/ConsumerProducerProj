package datastructure;

import message.Message;

public class CustomBlockingQueueShift {
    private final Message[] buffer;
    private final int capacity;
    private int size = 0;

    public CustomBlockingQueueShift(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be > 0");
        }
        this.capacity = capacity;
        this.buffer = new Message[capacity];
    }

    public synchronized void put(Message message) throws InterruptedException {
        if (message == null) {
            throw new NullPointerException("Message cannot be null");
        }

        while (size == capacity) {
            System.out.println("Queue full. Waiting to put...");
            wait();
        }

        buffer[size++] = message;
        notifyAll();
    }

    public synchronized Message take() throws InterruptedException {
        while (size == 0) {
            System.out.println("Queue empty. Waiting to take...");
            wait();
        }

        Message result = buffer[0];
        if (result == null) {
            throw new IllegalStateException("Unexpected null message in queue");
        }

        // Shift all elements left
        for (int i = 1; i < size; i++) {
            buffer[i - 1] = buffer[i];
        }

        buffer[--size] = null; // clear the last slot
        notifyAll();
        return result;
    }

    public synchronized int getSize() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }
}
