package io.spring.initializr.service.extension;

import io.spring.initializr.generator.ProjectRequest;
import io.spring.initializr.generator.ProjectRequestPostProcessor;
import io.spring.initializr.metadata.InitializrMetadata;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class SpringCloudMicroservicePostprocessor extends AbstractProjectRequestPostProcessor implements ProjectRequestPostProcessor {
    private static final Map<String, List<String>> serviceToDependencies = new HashMap<>();

    static {
        serviceToDependencies.put("cloud-config-server", Arrays.asList("cloud-config-server"));
        serviceToDependencies.put("cloud-gateway", Arrays.asList("cloud-gateway", "cloud-eureka-client"));
        serviceToDependencies.put("cloud-eureka-server", Arrays.asList("cloud-eureka-server", "cloud-config-client"));
        serviceToDependencies.put("cloud-hystrix-dashboard", Arrays.asList("cloud-hystrix-dashboard", "cloud-config-client", "cloud-eureka-client", "web"));
        serviceToDependencies.put("azure-service-bus", Arrays.asList("azure-service-bus", "cloud-config-client", "cloud-eureka-client", "web"));
        serviceToDependencies.put("cloud-sleuth-zipkin", Arrays.asList("cloud-starter-sleuth", "cloud-starter-zipkin", "zipkin-server", "zipkin-server-ui"));
    }

    @Override
    public void postProcessBeforeResolution(ProjectRequest request,
                                           InitializrMetadata metadata) {
        // Parent pom need these dependencies to determine whether include bom
        if(request.getParent() == null) {
            request.setDependencies(request.getServices().stream().map(serviceToDependencies::get).flatMap(List::stream).collect(Collectors.toList()));
        }
    }

    @Override
    public void postProcessAfterResolution(ProjectRequest request,
                                            InitializrMetadata metadata) {

        for (String service : request.getServices()) {
            if (!serviceToDependencies.containsKey(service)) {
                throw new IllegalStateException("Unsupported service type " + service);
            }

            ProjectRequest subModule = new ProjectRequest(request);
            subModule.setName(service);
            subModule.setArtifactId(request.getArtifactId() + "." + service);
            subModule.setDependencies(serviceToDependencies.get(service));
            subModule.setBaseDir(service);
            subModule.resolve(metadata);
            request.addModule(subModule);
        }


    }
}
