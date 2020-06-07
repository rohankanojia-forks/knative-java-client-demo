package io.fabric8.knative.client.demo;

import io.fabric8.knative.client.DefaultKnativeClient;
import io.fabric8.knative.client.KnativeClient;
import io.fabric8.knative.serving.v1.Service;

public class KnativeLoadDemo {
    public static void main(String[] args) {
        try (KnativeClient kn = new DefaultKnativeClient()) {
            Service svc = kn.services()
                    .load(KnativeLoadDemo.class.getResourceAsStream("/knative-svc.yml"))
                    .get();

            System.out.println(svc.getMetadata().getName() + " got loaded successfully");
        }
    }
}
