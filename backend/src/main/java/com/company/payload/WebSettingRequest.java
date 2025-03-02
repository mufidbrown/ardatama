package com.company.payload;

public class WebSettingRequest {
    private String key;
    private String value; // bisa teks atau URL dari file yang sudah diupload

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
