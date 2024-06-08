package study.kafka_producer_hello_world.producer;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SimpleProducerTest {

    @Autowired
    private SimpleProducer simpleProducer;

    @Test
    @DisplayName("메세지_전송")
    void 메세지_전송() throws Exception {
        simpleProducer.sendMessage("test", "hello world");
        simpleProducer.sendMessage("test", "key1", "hello world");
        simpleProducer.sendMessage("test", 0, "key1", "hello world");
    }

}