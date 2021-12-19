package com.dnydys.service.impl;

import com.dnydys.common.baseRespone.ResultInfo;
import com.dnydys.common.code.ResponseCode;
import com.dnydys.service.FileUploadService;
import com.dnydys.util.FtpUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.stereotype.Service;

/**
 * @Classname FileUploadServiceImpl
 * @Description TODO
 * @Date 2021/12/17 20:26
 * @Created by hasee
 */
@Service
@Slf4j
public class FileUploadServiceImpl implements FileUploadService {


    @Override
    public ResultInfo fileUpload(String localPath,String userid,String fileName) throws IOException {
        ResultInfo resultInfo = new ResultInfo();
        FtpUtil ftpUtil = new FtpUtil();
        Boolean aBoolean = ftpUtil.ftpLogin();
        if (!aBoolean){
            log.error("FTP用户登录失败");
            resultInfo.setResultCode(ResponseCode.FTP_LOGIN_ERROR);
            return resultInfo;
        }

//        localPath = new String(localPath.getBytes("GBK"),"UTF-8");
//        fileName = new String(fileName.getBytes("GBK"),"UTF-8");
        FileInputStream inputStream = new FileInputStream(new File(localPath));
        //ftptest用户登陆上去之后默认是在BasePath之下，所以不用切换，后续不是BasePath的话再放开就行了
//        Boolean isChangeBaseSuccess= ftpUtil.changeWorkingDirectory();
//        if (!isChangeBaseSuccess){
//            log.error("FTP切换基础目录失败");
//            resultInfo.setResultCode(ResponseCode.FTP_CHANGE_BASEPATH_ERROR);
//            return resultInfo;
//        }

        Boolean isChangeUserSuccess = ftpUtil.changeWorkingDirectory(userid);
        if (!isChangeUserSuccess){
            log.error("FTP切换用户目录失败,该用户目录不存在，先创建用户目录");
            ftpUtil.makeDirectory(userid);
        }
        Boolean isStoreFile = ftpUtil.storeFile(fileName, inputStream);
        if(!isStoreFile){
            log.error("FTP服务器端上传文件失败");
            resultInfo.setResultCode(ResponseCode.FTP_STOREFILE_ERROR);
            return resultInfo;
        }
        Boolean isLogout = ftpUtil.ftpLogout();
        if (!isLogout){
            log.error("FTP服务器登出失败");
            resultInfo.setResultCode(ResponseCode.FTP_LOGOUT_ERROR);
            return resultInfo;
        }
        resultInfo.setResultCode(ResponseCode.SUCCESS);
        return resultInfo;
    }

    @SneakyThrows
    @Override
    public ResultInfo fileDownload(String localPath,String userid,String fileName) {
        ResultInfo resultInfo = new ResultInfo();
        FtpUtil ftpUtil = new FtpUtil();
        Boolean isLogin = ftpUtil.ftpLogin();
        if (!isLogin){
            log.error("FTP用户登录失败");
            resultInfo.setResultCode(ResponseCode.FTP_LOGIN_ERROR);
            return resultInfo;
        }

        Boolean isChangeUserSuccess = ftpUtil.changeWorkingDirectory(userid);
        if (!isChangeUserSuccess){
            log.error("FTP切换用户目录失败");
            log.error("FTP切换用户目录失败,该用户目录不存在，先创建用户目录");
            Boolean isMakeDirectory = ftpUtil.makeDirectory(userid);
            if (!isMakeDirectory){
                log.error(userid+"用户目录创建失败");
                resultInfo.setResultCode(ResponseCode.FTP_LOGIN_ERROR);
                return resultInfo;
            }
        }
        //每次数据连接之前ftp client告诉ftp server开通 一个端口来传输数据。
        ftpUtil.enterLocalPassiveMode();
        //打印当前目录
//        String s = ftpUtil.printWorkingDirectory();
        FTPFile[] ftpFiles = ftpUtil.listFiles();
        boolean b = false;
//        fileName = new String(fileName.getBytes("GBK"),"iso-8859-1");
        if (ftpFiles.length > 0){
            for (FTPFile ff : ftpFiles) {
                if (ff.getName().equals(fileName)) {
                    ftpUtil.enterLocalPassiveMode();
                    FileOutputStream outputStream = new FileOutputStream(new File(localPath+"\\"+fileName));
                    b = ftpUtil.retrieveFile(fileName, outputStream);
                    outputStream.close();
                }
            }
        }
        if (!b){
            log.error("未查找到相关文件");
            resultInfo.setResultCode(ResponseCode.FTP_RETRIEVEFILE_ERROR);
            resultInfo.setData(b);
            return resultInfo;
        }
        Boolean isLogout = ftpUtil.ftpLogout();
        if (!isLogout){
            log.error("FTP用户登出失败");
            resultInfo.setResultCode(ResponseCode.FTP_LOGOUT_ERROR);
            return resultInfo;
        }
        resultInfo.setResultCode(ResponseCode.SUCCESS);
        resultInfo.setData(b);
        return resultInfo;
    }
}
