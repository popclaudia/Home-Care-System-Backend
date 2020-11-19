package ro.tuc.ds2020.receiver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import ro.tuc.ds2020.entities.Activity;


public class MessageReceiver {

    private final static String QUEUE_NAME = "rabbit";
    private final static String HOST_NAME = "roedeer.rmq.cloudamqp.com";
    private final static String HOST_PASS = "jSFPwZod7dGTKQ5V-Ex8zEKdxAxuNAMd";
    private final static String HOST_USER = "pnwczjmz";

    public static void main(String [] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername(HOST_USER);
        factory.setPassword(HOST_PASS);
        factory.setHost(HOST_NAME);
        factory.setVirtualHost(HOST_USER);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        MessageHandler mh = new MessageHandler();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        mh.connectToSocket();
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            ObjectMapper mapper = new ObjectMapper();

            Activity activity = mapper.readValue(message, Activity.class);
            System.out.println(" [x] Received '" + message + "'");
            System.out.println(activity);
            mh.checkRules(activity, message);
            mh.persistActivity(activity);

        };

        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }

}
