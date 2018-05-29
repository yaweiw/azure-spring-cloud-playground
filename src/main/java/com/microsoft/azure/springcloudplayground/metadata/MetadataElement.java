package com.microsoft.azure.springcloudplayground.metadata;

public class MetadataElement {

    /**
     * A visual representation of this element.
     */
    private String name;

    /**
     * The unique id of this element for a given capability.
     */
    private String id;

    public MetadataElement() {
    }

    public MetadataElement(MetadataElement other) {
        this(other.id, other.name);
    }

    public MetadataElement(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return this.name != null ? this.name : this.id;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

}
