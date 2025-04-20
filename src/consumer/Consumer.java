package consumer;

import broker.MessageBroker;
import message.Message;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Consumer implements Runnable {
    private final BlockingQueue<Message> queue;

    public Consumer(MessageBroker broker) {
        this.queue = new LinkedBlockingQueue<>();
        broker.registerConsumerQueue(queue);
    }

    @Override
    public void run() {
        try {
            while (true) {
                Message msg = queue.take();
                System.out.println("Consumer [" + Thread.currentThread().getName() + "] received: " + msg.getContent());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
