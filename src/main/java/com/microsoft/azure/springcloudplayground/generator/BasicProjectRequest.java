package com.microsoft.azure.springcloudplayground.generator;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class BasicProjectRequest {

    private List<String> style = new ArrayList<>();

    private List<String> dependencies = new ArrayList<>();

    private String name;

    private String type;

    private String description;

    private String groupId;

    private String artifactId;

    private String version;

    private String bootVersion;

    private String packaging;

    private String applicationName;

    private String language;

    private String packageName;

    private String javaVersion;

    // The base directory to create in the archive - no baseDir by default
    private String baseDir;

    private BasicProjectRequest parent;

    public BasicProjectRequest(BasicProjectRequest parentProject) {
        this.parent = parentProject;
    }

    public BasicProjectRequest() {
    }

    public List<String> getStyle() {
        return this.style;
    }

    public void setStyle(List<String> style) {
        this.style = style;
    }

    public List<String> getDependencies() {
        return this.dependencies;
    }

    public void setDependencies(List<String> dependencies) {
        this.dependencies = dependencies;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        if(this.parent != null){
            return this.parent.type;
        }
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        if(this.parent != null){
            return this.parent.getDescription();
        }
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGroupId() {
        if(this.parent != null){
            return this.parent.groupId;
        }
        return this.groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return this.artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getVersion() {
        if(this.parent != null){
            return this.parent.version;
        }
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBootVersion() {
        if(this.parent != null) {
            return this.parent.bootVersion;
        }

        return this.bootVersion;
    }

    public void setBootVersion(String bootVersion) {
        this.bootVersion = bootVersion;
    }

    public String getPackaging() {
        if(this.parent != null){
            return this.parent.getPackaging();
        }
        return this.packaging;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    public String getApplicationName() {
        return this.applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getLanguage() {
        if(this.parent != null){
            return this.parent.language;
        }
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPackageName() {
        if (StringUtils.hasText(this.packageName)) {
            return this.packageName;
        }
        if (StringUtils.hasText(this.groupId) && StringUtils.hasText(this.artifactId)) {
            return getGroupId() + "." + getArtifactId();
        }
        return null;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getJavaVersion() {
        return this.javaVersion;
    }

    public void setJavaVersion(String javaVersion) {
        this.javaVersion = javaVersion;
    }

    public String getBaseDir() {
        return this.baseDir;
    }

    public void setBaseDir(String baseDir) {
        this.baseDir = baseDir;
    }

    public BasicProjectRequest getParent() {
        return parent;
    }
}
