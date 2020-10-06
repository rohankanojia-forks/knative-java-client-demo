package io.fabric8.knative.client.demo;


import io.fabric8.knative.client.DefaultKnativeClient;
import io.fabric8.knative.client.KnativeClient;
import io.fabric8.knative.sources.v1beta1.ContainerSource;
import io.fabric8.knative.sources.v1beta1.ContainerSourceBuilder;
import io.fabric8.kubernetes.api.model.PodTemplateSpecBuilder;

public class ContainerSourceTest {
    public static void main(String[] args) {
        try (KnativeClient client = new DefaultKnativeClient()) {
            ContainerSource containerSource = new ContainerSourceBuilder()
                    .withNewMetadata().withName("test-heartbeats").endMetadata()
                    .withNewSpec()
                    .withTemplate(new PodTemplateSpecBuilder()
                            .withNewSpec()
                            .addNewContainer()
                            .withImage("test-image")
                            .withName("heartbeats")
                            .addToArgs("--period=1")
                            .addNewEnv().withName("POD_NAME").withValue("mypod").endEnv()
                            .addNewEnv().withName("POD_NAMESPACE").withValue("event-test").endEnv()
                            .endContainer()
                            .endSpec()
                    .build())
                    .withNewSink()
                    .withNewRef()
                    .withApiVersion("v1")
                    .withName("Service")
                    .withName("event-test")
                    .endRef()
                    .endSink()
                    .endSpec()
                    .build();

            client.containerSources().inNamespace("default").createOrReplace(containerSource);
        }
    }
}