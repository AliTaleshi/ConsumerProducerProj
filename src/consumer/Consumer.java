package consumer;

import datastructure.CustomBlockingQueue;
import message.Message;
import broker.MessageBroker;

public class Consumer implements Runnable {
    private final CustomBlockingQueue queue;
    private final String name;

    public Consumer(MessageBroker broker, int queueSize, String name) {
        this.queue = new CustomBlockingQueue(queueSize);
        this.name = name;
        broker.registerConsumerQueue(queue);
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(1000);
                Message message = queue.take();
                System.out.println(name + " received: " + message.getContent());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
