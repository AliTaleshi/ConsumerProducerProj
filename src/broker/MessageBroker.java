package broker;

import message.Message;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public class MessageBroker<T extends Message> {
    private final List<BlockingQueue<T>> consumerQueues = new CopyOnWriteArrayList<>();

    public void registerConsumerQueue(BlockingQueue<T> queue) {
        consumerQueues.add(queue);
    }

    public void publish(T message) {
        for (BlockingQueue<T> queue : consumerQueues) {
            try {
                queue.put(message);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}