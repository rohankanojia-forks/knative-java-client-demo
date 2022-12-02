package io.fabric8.knative.client.demo;

import io.fabric8.knative.client.DefaultKnativeClient;
import io.fabric8.knative.client.KnativeClient;
import io.fabric8.knative.serving.v1.RouteBuilder;

public class KnativeRouteCreateOrReplace {
    public static void main(String[] args) {
        try (KnativeClient kn = new DefaultKnativeClient()) {
            kn.routes().resource(new RouteBuilder()
                    .withNewMetadata()
                    .withName("helloworld-nodejs-red-blue1")
                    .withNamespace("default")
                    .endMetadata()
                    .withNewSpec()
                    .addNewTraffic()
                    .withConfigurationName("greeter")
                    .withPercent(100L)
                    .endTraffic()
                    .endSpec()
                .build()).createOrReplace();
        }
    }
}