package io.spring.initializr.generator;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ServiceLink {
    private String serviceName;
    private String serviceUrl;
    private boolean isBasic;
}
