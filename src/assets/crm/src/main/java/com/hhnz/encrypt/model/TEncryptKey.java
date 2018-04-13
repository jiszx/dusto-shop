package com.hhnz.encrypt.model;

import java.math.BigDecimal;
import java.util.Date;

public class TEncryptKey {
    private Long id;

    private String keyName;

    private String systemName;

    private String keyDesc;

    private BigDecimal keyVersion;

    private String keyState;

    private Date keyUpdateTs;

    private String keySendtype;

    private String keySenduri;

    private String keyType;

    private byte[] keyContent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getKeyDesc() {
        return keyDesc;
    }

    public void setKeyDesc(String keyDesc) {
        this.keyDesc = keyDesc;
    }

    public BigDecimal getKeyVersion() {
        return keyVersion;
    }

    public void setKeyVersion(BigDecimal keyVersion) {
        this.keyVersion = keyVersion;
    }

    public String getKeyState() {
        return keyState;
    }

    public void setKeyState(String keyState) {
        this.keyState = keyState;
    }

    public Date getKeyUpdateTs() {
        return keyUpdateTs;
    }

    public void setKeyUpdateTs(Date keyUpdateTs) {
        this.keyUpdateTs = keyUpdateTs;
    }

    public String getKeySendtype() {
        return keySendtype;
    }

    public void setKeySendtype(String keySendtype) {
        this.keySendtype = keySendtype;
    }

    public String getKeySenduri() {
        return keySenduri;
    }

    public void setKeySenduri(String keySenduri) {
        this.keySenduri = keySenduri;
    }

    public String getKeyType() {
        return keyType;
    }

    public void setKeyType(String keyType) {
        this.keyType = keyType;
    }

    public byte[] getKeyContent() {
        return keyContent;
    }

    public void setKeyContent(byte[] keyContent) {
        this.keyContent = keyContent;
    }
}