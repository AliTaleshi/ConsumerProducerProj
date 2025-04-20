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
                Message msg = queue.take();
                System.out.println(name + " received: " + msg.getContent());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
