package io.fabric8.knative.client.demo;


import io.fabric8.knative.client.DefaultKnativeClient;
import io.fabric8.knative.client.KnativeClient;
import io.fabric8.knative.eventing.contrib.kafka.v1beta1.KafkaChannel;
import io.fabric8.knative.eventing.contrib.kafka.v1beta1.KafkaChannelBuilder;

public class KafkaChannelTest {
    public static void main(String[] args) {
        try (KnativeClient kn = new DefaultKnativeClient()) {
            KafkaChannel kafkaChannel = new KafkaChannelBuilder()
                    .withNewMetadata().withName("my-kafka-channel").endMetadata()
                    .withNewSpec()
                    .withNumPartitions(1)
                    .withReplicationFactor(1)
                    .endSpec()
                    .build();
            kafkaChannel = kn.kafkaChannels().inNamespace("default").createOrReplace(kafkaChannel);
            System.out.println(kafkaChannel.getMetadata().getName() + " created.");
        }
    }
}