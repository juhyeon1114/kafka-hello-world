package study.kafka_producer_hello_world.producer;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.InvalidRecordException;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.utils.Utils;

import java.util.List;
import java.util.Map;

public class CustomPartitioner implements Partitioner {
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        if (keyBytes == null) {
            throw new InvalidRecordException("메세지 키를 입력해주세요.");
        }

        // 키가 test인 경우 0번 파티션으로 보내기
        if (key.toString().equals("test")) {
            return 0;
        }

        List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
        int partitionSize = partitions.size();
        return Utils.toPositive(Utils.murmur2(keyBytes)) % partitionSize;
    }

    @Override
    public void close() {}

    @Override
    public void configure(Map<String, ?> map) {}
}
