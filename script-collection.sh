/**
  * kafka-topics.sh
  */
# 토픽 생성
bash bin/kafka-topics.sh \
  --create \
  --bootstrap-server kafka-0:9092 \
  --topic hello.kafka

# 토픽 생성 with 옵션
bash bin/kafka-topics.sh \
  --create \
  --bootstrap-server kafka-0:9092 \
  --topic hello.kafka2 \
  --partitions 10 \
  --replication-factor 1 \
  --config retention.ms=172800000

# 토픽 설정 수정
bash bin/kafka-topics.sh \
  --bootstrap-server kafka-0:9092 \
  --topic hello.kafka \
  --alter \
  --partitions 10


# 토픽 정보 출력
bash bin/kafka-topics.sh --bootstrap-server kafka-0:9092 --topic hello.kafka --describe
bash bin/kafka-topics.sh --bootstrap-server kafka-0:9092 --topic hello.kafka2 --describe

/**
  * kafka-configs.sh
  */
# 토픽 설정 수정 (kafka-configs.sh)
bash bin/kafka-configs.sh \
  --bootstrap-server kafka-0:9092 \
  --alter \
  --add-config min.insync.replicas=2 \
  --topic hello.kafka

# 브로커 옵션 조회
bash bin/kafka-configs.sh \
  --bootstrap-server kafka-0:9092 \
  --broker 1001 \
  --all \
  --describe

/**
  * kafka-console-producer.sh
  */
# 기본
bash bin/kafka-console-producer.sh \
  --bootstrap-server kafka-0:9092 \
  --topic hello.kafka

# 키:밸류
bash bin/kafka-console-producer.sh \
  --bootstrap-server kafka-0:9092 \
  --topic hello.kafka \
  --property "parse.key=true" \
  --property "key.separator=:"

/**
  * kafka-console-consumer.sh
  */
# 기본
bash bin/kafka-console-consumer.sh \
  --bootstrap-server kafka-0:9092 \
  --topic hello.kafka \
  --from-beginning \
  --max-messages 10 \
  --partition 0

# 키:밸류
bash bin/kafka-console-consumer.sh \
  --bootstrap-server kafka-0:9092 \
  --topic hello.kafka \
  --property "print.key=true" \
  --property "key.separator=-" \
  --from-beginning

/**
  * kafka-consumer-groups.sh
  */
# 컨슈머 그룹 조회
bash bin/kafka-consumer-groups.sh \
  --bootstrap-server kafka-0:9092 \
  --list

# 컨슈머 그룹 상세 조회
bash bin/kafka-consumer-groups.sh \
  --bootstrap-server kafka-0:9092 \
  --group console-consumer-31602 \
  --describe

# 오프셋 리셋
bash bin/kafka-consumer-groups.sh \
  --bootstrap-server kafka-0:9092 \
  --group console-consumer-31602 \
  --topic hello.kafka \
  --reset-offsets \
  --to-earliest \
  --execute

/**
  * ETC
  */
bash bin/kafka-producer-perf-test.sh \
  --bootstrap-server kafka-0:9092 \
  --topic hello.kafka \
  --num-records 10 \
  --throughput 1 \
  --record-size 100 \
  --print-metric

bash bin/kafka-consumer-perf-test.sh \
  --bootstrap-server kafka-0:9092 \
  --topic hello.kafka \
  --messages 10 \
  --show-detailed-stats

bash bin/kafka-reassign-partitions.sh \
  --zookeeper \
  --bootstrap-server kafka-0:9092 \
  --reassignment-json-file reassign.json \
  --execute

