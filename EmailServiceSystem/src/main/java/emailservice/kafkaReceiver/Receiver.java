package emailservice.kafkaReceiver;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class Receiver {

    @KafkaListener(topics = {"students","registration-events"})
    public void receive(@Payload String message) {
        /**
         * receiving from kafka and store them in list of students and list of events
         */
    }
}
