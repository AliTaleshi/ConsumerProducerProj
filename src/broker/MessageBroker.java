package broker;

import datastructure.CustomBlockingQueue;
import message.Message;

public class MessageBroker {
    private final CustomBlockingQueue[] consumerQueues = new CustomBlockingQueue[10];
    private int consumerCount = 0;

    public synchronized void registerConsumerQueue(CustomBlockingQueue queue) {
        if (consumerCount >= consumerQueues.length) {
            throw new RuntimeException("Too many consumers registered.");
        }
        consumerQueues[consumerCount++] = queue;
    }

    public void publish(Message message) {
        if (message == null) {
            throw new IllegalArgumentException("Null message not allowed");
        }

        for (int i = 0; i < consumerCount; i++) {
            try {
                consumerQueues[i].put(message);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
