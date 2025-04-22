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
        while (true) {
            Message message = new TextMessage(name + " message #" + count++);
            broker.publish(message);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
