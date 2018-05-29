package com.microsoft.azure.springcloudplayground.metadata;

public class SpringCloudModule {
    private String applicationName;
    private int port;

    public SpringCloudModule(String applicationName, int port) {
        this.applicationName = applicationName;
        this.port = port;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
