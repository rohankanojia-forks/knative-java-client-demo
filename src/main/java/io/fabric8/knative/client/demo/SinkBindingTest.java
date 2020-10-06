package io.fabric8.knative.client.demo;

import io.fabric8.knative.client.DefaultKnativeClient;
import io.fabric8.knative.client.KnativeClient;
import io.fabric8.knative.sources.v1beta1.SinkBinding;
import io.fabric8.knative.sources.v1beta1.SinkBindingBuilder;
import io.fabric8.kubernetes.api.model.LabelSelectorBuilder;

public class SinkBindingTest {
    public static void main(String[] args) {
        try (KnativeClient client = new DefaultKnativeClient()) {
            SinkBinding sinkBinding = new SinkBindingBuilder()
                    .withNewMetadata().withName("bind-heartbeat").endMetadata()
                    .withNewSpec()
                    .withNewSubject()
                    .withApiVersion("batch/v1")
                    .withKind("Job")
                    .withSelector(new LabelSelectorBuilder().addToMatchLabels("app", "heatbeat-cron").build())
                    .endSubject()
                    .withNewSink()
                    .withNewRef()
                    .withApiVersion("v1")
                    .withKind("Service")
                    .withName("event-display")
                    .endRef()
                    .endSink()
                    .endSpec()
                    .build();
            client.sinkBindings().inNamespace("default").createOrReplace(sinkBinding);
        }
    }
}
