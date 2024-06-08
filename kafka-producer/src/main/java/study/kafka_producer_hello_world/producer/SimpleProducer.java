package study.kafka_producer_hello_world.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@RequiredArgsConstructor
@Component
public class SimpleProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 키가 없는 메세지 전송
     * @param topic - 토픽
     * @param message - 메세지
     */
    public void sendMessage(String topic, String message) throws ExecutionException, InterruptedException {
        syncSend(new ProducerRecord(topic, message));
    }

    /**
     * 키가 있는 메세지 전송
     * @param topic - 토픽
     * @param key - 키
     * @param message - 메세지
     */
    public void sendMessage(String topic, String key, String message) throws ExecutionException, InterruptedException {
        syncSend(new ProducerRecord(topic, key, message));
    }

    /**
     * 키, 파티션을 지정한 메세지 전송
     * @param topic - 토픽
     * @param partition - 파티션
     * @param key - 키
     * @param message - 메세지
     */
    public void sendMessage(String topic, int partition, String key, String message) throws ExecutionException, InterruptedException {
        syncSend(new ProducerRecord(topic, partition, key, message));
    }

    /**
     * 비동기 전송
     * @param record - ProducerRecord
     * @return CompletableFuture
     */
    public CompletableFuture asyncSend(ProducerRecord record) {
        return kafkaTemplate.send(record);
    }

    /**
     * 동기 전송
     * @param record
     * @return SendResult
     */
    public SendResult syncSend(ProducerRecord record) throws ExecutionException, InterruptedException {
        CompletableFuture future = asyncSend(record);
        return (SendResult) future.get();
    }

}
