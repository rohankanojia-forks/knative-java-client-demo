package io.fabric8.knative.client.demo;

import io.fabric8.knative.client.DefaultKnativeClient;
import io.fabric8.knative.client.KnativeClient;
import io.fabric8.knative.serving.v1.Service;
import io.fabric8.knative.serving.v1.ServiceList;

public class ServiceListDemo {
    public static void main(String[] args) {
        try (KnativeClient kn = new DefaultKnativeClient()) {
            // Get all Service objects
            ServiceList services = kn.services()
                    .inNamespace("default")
                    .list();
            // Iterate through list and print names
            for (Service svc : services.getItems()) {
                System.out.println(svc.getMetadata().getName());
            }
        }
    }
}
