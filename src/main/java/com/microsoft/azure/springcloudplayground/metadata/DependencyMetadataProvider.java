package com.microsoft.azure.springcloudplayground.metadata;

import com.microsoft.azure.springcloudplayground.util.Version;

public interface DependencyMetadataProvider {

    /**
     * Return the dependency metadata to use for the specified {@code bootVersion}.
     * @param metadata the intializr metadata
     * @param bootVersion the Spring Boot version
     * @return the dependency metadata
     */
    DependencyMetadata get(GeneratorMetadata metadata, Version bootVersion);

}
