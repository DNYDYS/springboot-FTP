package com.dnydys.common.code;

/**
 * @Classname ResponseCode
 * @Description TODO
 * @Date 2021/12/17 20:46
 * @Created by hasee
 */
public enum ResponseCode {
    SUCCESS(20000000, "成功"),
    GEN_INTERNAL(20000001, "操作失败"),
    FTP_LOGIN_ERROR(2000003,"FTP服务器登录失败"),
    FTP_LOGOUT_ERROR(2000004,"FTP服务器登出失败"),
    FTP_CHANGE_BASEPATH_ERROR(2000005,"FTP切换基础目录失败"),
    FTP_CHANGE_USERPATH_ERROR(2000006,"FTP切换用户目录失败"),
    FTP_STOREFILE_ERROR(2000007,"FTP服务器端上传文件失败"),
    FTP_RETRIEVEFILE_ERROR(2000008,"FTP服务器下载文件失败"),
    FTP_MAKEDIRECTORY_ERROR(2000009,"用户目录创建失败");
    private int code;
    private String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "ResponseCode{" +
            "code=" + code +
            ", desc='" + desc + '\'' +
            '}';
    }
}
