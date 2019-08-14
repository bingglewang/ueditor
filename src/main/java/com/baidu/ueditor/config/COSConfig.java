package com.baidu.ueditor.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "tengxunyun.cos")
public class COSConfig {
    private String accessKey;
    private String secretKey;
    private String bucket;
    private String bucketName;
    private String path;
    private String qianzui;
    private String locationServer;

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getQianzui() {
        return qianzui;
    }

    public void setQianzui(String qianzui) {
        this.qianzui = qianzui;
    }

    public String getLocationServer() {
        return locationServer;
    }

    public void setLocationServer(String locationServer) {
        this.locationServer = locationServer;
    }
}
