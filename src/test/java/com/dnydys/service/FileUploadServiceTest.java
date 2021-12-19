package com.dnydys.service;

import com.dnydys.common.baseRespone.ResultInfo;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Classname FileUploadServiceTest
 * @Description TODO
 * @Date 2021/12/17 23:28
 * @Created by hasee
 */
@SpringBootTest
public class FileUploadServiceTest {

    @Autowired
    private FileUploadService fileUploadService;

    //文件上传
    @Test
    public void fileUpload() throws IOException {

//        String localPath = "C:\\Users\\hasee\\Desktop\\DNYDIS\\微信图片_20210702155401.jpg";
        String localPath = "C:\\Users\\hasee\\Desktop\\DNYDIS\\testfile.txt";
        System.out.println("+++++++++++++++++++++"+localPath);
//        String str1 = new String(localPath.getBytes("ISO-8859-1"), "utf-8");
//        System.out.println(str1);
//        String str2 = new String(localPath.getBytes("gb2312"), "utf-8");
//        System.out.println(str2);
//        String str3 = new String(localPath.getBytes("gbk"), "utf-8");
//        System.out.println(str3);

        String fileName = "testfile.txt";
        String userid = "22";
        ResultInfo resultInfo = fileUploadService.fileUpload(localPath, userid, fileName);
        System.out.println(resultInfo.getResultCode());
    }

    @Test
    public void fileDownload() throws IOException {

        String localPath = "C:\\Users\\hasee\\Desktop\\download";
        System.out.println("+++++++++++++++++++++"+localPath);
//        String str1 = new String(localPath.getBytes("ISO-8859-1"), "utf-8");
//        System.out.println(str1);
//        String str2 = new String(localPath.getBytes("gb2312"), "utf-8");
//        System.out.println(str2);
//        String str3 = new String(localPath.getBytes("gbk"), "utf-8");
//        System.out.println(str3);

        String fileName = "testfile.txt";
        String userid = "22";
        ResultInfo resultInfo = fileUploadService.fileDownload(localPath, userid, fileName);
        System.out.println(resultInfo.getResultCode());
    }
}
