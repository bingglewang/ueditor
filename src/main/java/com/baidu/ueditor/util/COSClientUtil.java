
package com.baidu.ueditor.util;

import com.baidu.ueditor.config.COSConfig;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class COSClientUtil {

    @Autowired
    private COSConfig cosConfig;

    private static COSConfig cosConfigCopy;

    @PostConstruct
    public void init() {
        this.cosConfigCopy = cosConfig;
    }


     /**
     * 获取COSClient操作对象
     * @return
     */

    public static COSClient getCOSClient(){
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(cosConfigCopy.getAccessKey(),cosConfigCopy.getSecretKey());
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region(cosConfigCopy.getBucket()));
        // 3 生成cos客户端
        COSClient cosclient = new COSClient(cred, clientConfig);
        return cosclient;
    }
}
