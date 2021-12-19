package com.dnydys.service;

import com.dnydys.common.baseRespone.ResultInfo;
import java.io.IOException;

/**
 * @Classname FileUploadService
 * @Description TODO
 * @Date 2021/12/17 20:26
 * @Created by hasee
 */
public interface FileUploadService {

    public ResultInfo fileUpload(String localPath,String userid,String fileName) throws IOException;

    public ResultInfo fileDownload(String localPath,String userid,String fileName);

}
