package study.kafka_consumer_hello_world.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumer {

    @KafkaListener(topics = "test")
    public void receive(ConsumerRecord<String, String> consumerRecord) {
        log.info("수신한 키: {}", consumerRecord.key());
        log.info("수신한 메세지: {}", consumerRecord.value());
    }

}
