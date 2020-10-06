package io.fabric8.knative.client.demo;


import io.fabric8.knative.client.DefaultKnativeClient;
import io.fabric8.knative.client.KnativeClient;
import io.fabric8.knative.eventing.contrib.awssqs.v1alpha1.AwsSqsSource;
import io.fabric8.knative.eventing.contrib.awssqs.v1alpha1.AwsSqsSourceBuilder;
import io.fabric8.kubernetes.api.model.ObjectReferenceBuilder;

public class AwsSqsSourceTest {
    public static void main(String[] args) {
        try (KnativeClient client = new DefaultKnativeClient()) {
            AwsSqsSource awsSqsSource = new AwsSqsSourceBuilder()
                    .withNewMetadata().withName("awssqs-sample-source").endMetadata()
                    .withNewSpec()
                    .withNewAwsCredsSecret("credentials", "aws-credentials", true)
                    .withQueueUrl("QUEUE_URL")
                    .withSink(new ObjectReferenceBuilder()
                            .withApiVersion("messaging.knative.dev/v1alpha1")
                            .withKind("Channel")
                            .withName("awssqs-test")
                            .build())
                    .endSpec()
                    .build();
            client.awsSqsSources().inNamespace("default").createOrReplace(awsSqsSource);
        }
    }
}