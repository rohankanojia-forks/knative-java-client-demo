package io.fabric8.knative.client.demo;

import io.fabric8.knative.client.DefaultKnativeClient;
import io.fabric8.knative.client.KnativeClient;
import io.fabric8.knative.serving.v1.Route;
import io.fabric8.kubernetes.api.model.DeletionPropagation;

import java.io.InputStream;

public class KnativeRouteCrudDemo {

    public static final String NAMESPACE = "default";

    public static void main(String[] args) {
        try (KnativeClient kn = new DefaultKnativeClient()) {
            // Load Route object from YAML
            InputStream routeYamlIn = KnativeRouteCrudDemo.class.getResourceAsStream("/knative-route.yml");
            Route route = kn.routes().load(routeYamlIn).get();

            // Create Route object into Kubernetes
            kn.routes().inNamespace(NAMESPACE).createOrReplace(route);

            // Get Route object from APIServer
            String routeName = route.getMetadata().getName();
            route = kn.routes().inNamespace(NAMESPACE)
                    .withName(routeName)
                    .get();

            // Edit Route object, add some dummy label
            kn.routes().inNamespace(NAMESPACE).withName(routeName).edit()
                    .editOrNewMetadata()
                    .addToAnnotations("context", "demo")
                    .endMetadata()
                    .done();

            // Delete Route object
            kn.routes().inNamespace(NAMESPACE).withName(routeName).withPropagationPolicy(DeletionPropagation.FOREGROUND).delete();
        }
    }
}
