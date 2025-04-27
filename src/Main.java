import broker.MessageBroker;
import consumer.Consumer;
import message.Message;
import producer.Producer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    public static void main(String[] args) {
        MessageBroker broker = new MessageBroker();

        for (int i = 0; i < 1; i++) {
            Thread consumerThread = new Thread(new Consumer(broker, 10, "Consumer-" + i));
            consumerThread.start();
        }


        // TODO: (Debug) Producers should not publish message when there is not consumer subscribed to producers
        for (int i = 0; i < 2; i++) {
            Thread producerThread = new Thread(new Producer(broker, "Producer-" + i));
            producerThread.start();
        }

        BlockingQueue<Message> queue = new ArrayBlockingQueue<Message>(10);
    }
}
