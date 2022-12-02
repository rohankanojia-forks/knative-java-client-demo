package io.fabric8.knative.client.demo;

import io.fabric8.knative.client.DefaultKnativeClient;
import io.fabric8.knative.client.KnativeClient;
import io.fabric8.knative.serving.v1.Service;
import io.fabric8.knative.serving.v1.ServiceBuilder;
import io.fabric8.kubernetes.api.model.ContainerBuilder;

public class ServiceBuilderDemo {
    public static void main(String[] args) {
        try (KnativeClient kn = new DefaultKnativeClient()) {
            // Create Service object
            Service service = new ServiceBuilder()
                    .withNewMetadata().withName("helloworld-go").endMetadata()
                    .withNewSpec()
                    .withNewTemplate()
                    .withNewSpec()
                    .addToContainers(new ContainerBuilder()
                            .withImage("gcr.io/knative-samples/helloworld-go")
                            .addNewEnv().withName("TARGET").withValue("Go Sample V1").endEnv()
                            .build())
                    .endSpec()
                    .endTemplate()
                    .endSpec()
                    .build();

            // Apply it onto Kubernetes Server
            kn.services().inNamespace("default").resource(service).createOrReplace();

        }
    }
}
