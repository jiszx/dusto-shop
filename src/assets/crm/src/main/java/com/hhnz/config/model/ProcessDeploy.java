package com.hhnz.config.model;

import java.math.BigDecimal;

public class ProcessDeploy {
    private String id;

    private String deployId;

    private String pkey;

    private String name;

    private BigDecimal version;

    private String deployDes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeployId() {
        return deployId;
    }

    public void setDeployId(String deployId) {
        this.deployId = deployId;
    }

    public String getPkey() {
        return pkey;
    }

    public void setPkey(String pkey) {
        this.pkey = pkey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getVersion() {
        return version;
    }

    public void setVersion(BigDecimal version) {
        this.version = version;
    }

    public String getDeployDes() {
        return deployDes;
    }

    public void setDeployDes(String deployDes) {
        this.deployDes = deployDes;
    }
}