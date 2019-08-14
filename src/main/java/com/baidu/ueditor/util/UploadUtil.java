package com.baidu.ueditor.util;

import com.baidu.ueditor.PathFormat;
import com.baidu.ueditor.config.COSConfig;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.UUID;

@Component
public class UploadUtil {

    @Autowired
    private COSConfig cosConfig;

    private static COSConfig testConfig;

    @PostConstruct
    public void init() {
        testConfig = cosConfig;
    }

    public static Ueditor upload1(File localFile,String savePath){
        // 获取COSClient操作对象
        COSClient cosclient= COSClientUtil.getCOSClient();
        // bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
        String bucketName = testConfig.getBucketName();


        // 指定要上传到 COS 上的路径
        String key = "/"+testConfig.getQianzui()+savePath;
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
        PutObjectResult putObjectResult = cosclient.putObject(putObjectRequest);
        return new Ueditor("",testConfig.getPath() + putObjectRequest.getKey(),"","");
    }


    public static Ueditor upload(MultipartFile file,String savePath) {
        String size = file.getSize()*1.0/(1024*1024) + "M";//文件大小
        String oldFileName = file.getOriginalFilename(); //原始文件名称
        String eName = "";
        if(oldFileName.lastIndexOf(".") > 0) {
            eName = oldFileName.substring(oldFileName.lastIndexOf("."));  //文件类型
        }else{
            try {
                eName =  ((FileType.toMap()).get(FileTypeJudge.getFileType(file.getInputStream()))).toString();
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }
        // 获取COSClient操作对象
        COSClient cosclient= COSClientUtil.getCOSClient();
        // bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
        String bucketName = testConfig.getBucketName();

        // 简单文件上传, 最大支持 5 GB, 适用于小文件上传, 建议 20 M 以下的文件使用该接口
        // 大文件上传请参照 API 文档高级 API 上传
        File localFile = null;
        try {
            //localFile = File.createTempFile("temp",null);
            localFile = new File(ResourceUtils.getURL("classpath:").getPath(),file.getOriginalFilename());
            OutputStream outputStream = new FileOutputStream(localFile);
            byte[] bytes = file.getBytes();
            outputStream.write(bytes);
            outputStream.close();
            /*file.transferTo(localFile);*/
            // 指定要上传到 COS 上的路径

            String key = "/"+testConfig.getQianzui()+"/"+savePath;
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
            PutObjectResult putObjectResult = cosclient.putObject(putObjectRequest);
            return new Ueditor("",testConfig.getPath() + putObjectRequest.getKey(),eName.substring(1),oldFileName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            // 关闭客户端(关闭后台线程)
            cosclient.shutdown();
        }
    }
}
