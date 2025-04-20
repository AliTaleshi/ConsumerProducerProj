package broker;

import message.Message;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public class MessageBroker {
    private final List<BlockingQueue<Message>> consumerQueues = new CopyOnWriteArrayList<>();

    public void registerConsumerQueue(BlockingQueue<Message> queue) {
        consumerQueues.add(queue);
    }

    public void publish(Message message) {
        for (BlockingQueue<Message> queue : consumerQueues) {
            try {
                queue.put(message);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
