package consumer;

import broker.MessageBroker;
import message.Message;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Consumer implements Runnable {
    private final BlockingQueue<Message> queue;
    private final String name;

    public Consumer(MessageBroker<Message> broker, String name) {
        this.queue = new LinkedBlockingQueue<>();
        this.name = name;
        broker.registerConsumerQueue(queue);
    }

    @Override
    public void run() {
        try {
            while (true) {
                Message msg = queue.take();
                System.out.println("[" + name + "] received: " + msg.getContent());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
