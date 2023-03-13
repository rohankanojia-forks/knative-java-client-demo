package io.fabric8.knative.client.demo;

import io.fabric8.knative.client.KnativeClient;
import io.fabric8.knative.serving.v1.Service;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientBuilder;

public class KnativeAdaptDemo {
    public static void main(String[] args) {
        try (KubernetesClient client = new KubernetesClientBuilder().build()) {
            KnativeClient kn = client.adapt(KnativeClient.class);
            if (kn.supports(Service.class)) {
                System.out.println("Client is adaptable to Knative Client, adapting...");
                kn = client.adapt(KnativeClient.class);

                // ...
                // Use kn for Knative operations
            } else {
                System.out.println("Sorry, could not adapt client");
            }
        }
    }
}
