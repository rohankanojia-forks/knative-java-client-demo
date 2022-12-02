package io.fabric8.knative.client.demo;


import io.fabric8.knative.client.DefaultKnativeClient;
import io.fabric8.knative.client.KnativeClient;
import io.fabric8.knative.sources.v1.ApiServerSource;
import io.fabric8.knative.sources.v1.ApiServerSourceBuilder;

public class ApiServerSourceTest {
    public static void main(String[] args) {
        try (KnativeClient client = new DefaultKnativeClient()) {
            ApiServerSource apiServerSource = new ApiServerSourceBuilder()
                    .withNewMetadata()
                    .withName("testevents")
                    .endMetadata()
                    .withNewSpec()
                    .withServiceAccountName("events-sa")
                    .withMode("Resource")
                    .addNewResource()
                    .withApiVersion("v1")
                    .withKind("Event")
                    .endResource()
                    .withNewSink()
                    .withNewRef()
                    .withApiVersion("v1")
                    .withKind("Service")
                    .withName("event-display")
                    .endRef()
                    .endSink()
                    .endSpec()
                    .build();

            client.apiServerSources().inNamespace("default").createOrReplace(apiServerSource);
        }
    }
}