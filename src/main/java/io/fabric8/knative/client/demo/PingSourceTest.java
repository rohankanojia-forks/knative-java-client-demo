package io.fabric8.knative.client.demo;

import io.fabric8.knative.client.DefaultKnativeClient;
import io.fabric8.knative.client.KnativeClient;
import io.fabric8.knative.sources.v1.PingSource;
import io.fabric8.knative.sources.v1.PingSourceBuilder;

public class PingSourceTest {
    public static void main(String[] args) {
        try (KnativeClient client = new DefaultKnativeClient()) {
            PingSource pingSource = new PingSourceBuilder()
                    .withNewMetadata().withName("test-ping-source").endMetadata()
                    .withNewSpec()
                    .withSchedule("*/1 * * * *")
                    .withNewSink()
                    .withNewRef()
                    .withApiVersion("v1")
                    .withKind("Service")
                    .withName("event-display")
                    .endRef()
                    .endSink()
                    .endSpec()
                    .build();
            client.pingSources().inNamespace("default").createOrReplace(pingSource);
        }
    }
}
