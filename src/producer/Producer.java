package producer;

import broker.MessageBroker;
import message.Message;
import message.TextMessage;

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
            Message msg = new TextMessage("[" + name + "]" + " message #" + count++);
            broker.publish(msg);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
