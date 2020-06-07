package io.fabric8.knative.client.demo;

import io.fabric8.knative.client.KnativeClient;
import io.fabric8.knative.serving.v1.Service;
import io.fabric8.knative.serving.v1.ServiceBuilder;
import io.fabric8.kubernetes.api.model.ContainerBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;

public class KnativeAdaptDemo {
    public static void main(String[] args) {
        try (KubernetesClient client = new DefaultKubernetesClient()) {
            KnativeClient kn = null;
            if (client.isAdaptable(KnativeClient.class)) {
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
