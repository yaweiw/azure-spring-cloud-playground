package com.microsoft.azure.springcloudplayground.metadata;

public class ServiceModule extends MetadataElement implements Describable {
    private String description;

    @Override
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ServiceModule() {
    }

    public ServiceModule(ServiceModule module) {
        super(module);
        this.description = module.description;
    }

    @Override
    public String toString() {
        return "ServiceModule{" + "id='" + getId() + '\'' + ", name='" + getName()
                + '\'' + ", description='" + this.getDescription() + '\'' + '}';
    }

    public static ServiceModule withId(String id, String name, String description) {
        ServiceModule module = new ServiceModule();
        module.setId(id);
        module.setName(name);
        module.setDescription(description);
        return module;
    }
}
