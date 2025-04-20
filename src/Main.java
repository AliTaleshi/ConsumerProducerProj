import broker.MessageBroker;
import consumer.Consumer;
import producer.Producer;

public class Main {
    public static void main(String[] args) {
        MessageBroker broker = new MessageBroker();

        for (int i = 0; i < 3; i++) {
            Thread consumerThread = new Thread(new Consumer(broker, "Consumer-" + i));
            consumerThread.start();
        }

        for (int i = 0; i < 2; i++) {
            Thread producerThread = new Thread(new Producer(broker, "Producer-" + i));
            producerThread.start();
        }
    }
}
