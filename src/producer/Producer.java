package producer;

import broker.MessageBroker;
import message.TextMessage;
import message.Message;

public class Producer implements Runnable {
    private final MessageBroker broker;
    private final String name;

    public Producer(MessageBroker broker, String name) {
        this.broker = broker;
        this.name = name;
    }

    @Override
    public void run() {
        int count = 0;
        try {
            while (true) {
                Message message = new TextMessage(name + " message #" + count++);
                System.out.println(name + " published: " + message.getContent());
                broker.publish(message);

                if (count == 3) {
                    Thread.sleep(100000);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
