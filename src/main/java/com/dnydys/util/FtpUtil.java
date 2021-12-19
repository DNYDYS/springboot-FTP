package com.dnydys.util;

import io.netty.util.CharsetUtil;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

/**
 * @Classname FtpUtil
 * @Description TODO
 * @Date 2021/12/17 19:52
 * @Created by hasee
 */
public class FtpUtil {

    public FTPClient ftpClient = new FTPClient();
    //ftp服务器地址
    public static final String HOSTNAME = "101.133.000.94";
    //ftp服务器端口号
    public static final int PORT = 21;
    //登录ftp服务器的用户名和密码
    public static final String USERNAME = "ftp0000";
    public static final String PWD = "00000000";
    //ftpuser的基础目录
    //所有用户的文件都是在这个目录下
//    public static final String BASEPATH = "/var/ftp/test";

    //本地字符编码
    static String LOCAL_CHARSET = "UTF-8";
    // FTP协议里面，规定文件名编码为iso-8859-1
    static String SERVER_CHARSET = "ISO-8859-1";


    /**
     * @className FtpUtil
     * @author dnydys
     * @description 用于登录ftp服务器
     * @updateTime 2021/12/17 20:14
     * @return: void
     * @version 1.0
     */
    public Boolean ftpLogin() throws IOException {

        ftpClient.setAutodetectUTF8(true);
        ftpClient.setCharset(CharsetUtil.UTF_8);
        ftpClient.setControlEncoding(CharsetUtil.UTF_8.name());
        //服务器地址和端口
        ftpClient.connect(HOSTNAME,PORT);

        //登录的用户名和密码
        Boolean isLogin = ftpClient.login(USERNAME, PWD);
        return isLogin;
    }

    /**
     * @className FtpUtil
     * @author dnydys
     * @description 用于退出ftp服务器
     * @updateTime 2021/12/17 20:15
     * @return: void
     * @version 1.0
     */
    public Boolean ftpLogout() throws IOException {
        Boolean isLogout = ftpClient.logout();
        return isLogout;
    }

    /**
     * @className FtpUtil
     * @author dnydys
     * @description 切换目录至基础目录
     * @updateTime 2021/12/17 21:53
     * @return: java.lang.Boolean
     * @version 1.0
     */
    public Boolean changeWorkingDirectory() throws IOException {
        String BASEPATH = "/var/ftp/0000";
        boolean b = ftpClient.changeWorkingDirectory(BASEPATH);
        return b;
    }

    /**
     * @className FtpUtil
     * @author dnydys
     * @description 切换目录至用户目录
     * @updateTime 2021/12/17 21:54
     * @return: java.lang.Boolean
     * @version 1.0
     */
    public Boolean changeWorkingDirectory(String userPath) throws IOException {
        boolean b = ftpClient.changeWorkingDirectory(userPath);
        return b ;
    }

    /**
     * @className FtpUtil
     * @author dnydys
     * @description 创建用户目录 false说明用户目录已经存在
     * @updateTime 2021/12/17 21:59
     * @return: java.lang.Boolean
     * @version 1.0
     */
    public Boolean makeDirectory(String userid) throws IOException {
        boolean b = ftpClient.makeDirectory(userid);
        return b;
    }

    /**
     * @className FtpUtil
     * @author dnydys
     * @description 设置文件类型 二进制形式传文件
     * @updateTime 2021/12/17 22:07
     * @return: java.lang.Boolean
     * @version 1.0
     */
    public Boolean setFileType() throws IOException {
        return ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
    }

    /**
     * @className FtpUtil
     * @author dnydys
     * @description 1.服务器端保存的文件名，2.上传文件的inputstream
     * @updateTime 2021/12/17 22:24
     * @return: java.lang.Boolean
     * @version 1.0
     */
    public Boolean storeFile(String filename, FileInputStream inputStream) throws IOException {
//         开启服务器对UTF-8的支持，如果服务器支持就用UTF-8编码，否则就使用本地编码（GBK）.
//        if (FTPReply.isPositiveCompletion(ftpClient.sendCommand("OPTS UTF8", "ON"))) {
//            LOCAL_CHARSET = "UTF-8";
//        }
//        ftpClient.setControlEncoding(LOCAL_CHARSET);
//        return ftpClient.storeFile(new String(filename.getBytes(LOCAL_CHARSET),
//            SERVER_CHARSET),inputStream);

        return ftpClient.storeFile(filename,inputStream);
    }

    /**
     * @className FtpUtil
     * @author dnydys
     * @description 打印用户当前所在目录
     * @updateTime 2021/12/18 9:59
     * @return: java.lang.String
     * @version 1.0
     */
    public String printWorkingDirectory() throws IOException {
        return ftpClient.printWorkingDirectory();
    }
    /**
     * @className FtpUtil
     * @author dnydys
     * @description
     * @updateTime 2021/12/18 10:20 
     * @return: void

     * @version 1.0
     */
    public void enterLocalPassiveMode(){
//        每次数据连接之前ftp client告诉ftp server开通 一个端口来传输数据。
//        原因是ftp每次开启不同的端口传输数据，linux上，安全限制，某些端口没有开启，出现阻塞
//        但是不理解他说的被动和主动方式的含义。
//        今天回来查资料知道：
//        FTP协议有两种工作方式，Port和Pasv方式，主动和被动式。
//        (1) PORT（主动模式）
//        工作的原理： FTP客户端连接到FTP服务器的21端口，发送用户名和密码登录，登录成功后要list列表或者读取数据时，客户端随机开放一个端口（1024以上），发送 PORT命令到FTP服务器，告诉服务器客户端采用主动模式并开放端口；FTP服务器收到PORT主动模式命令和端口号后，通过服务器的20端口和客户端开放的端口连接，发送数据
//            (2) PASV（被动模式） 这就是上面方法的作用。
//        工作的原理：FTP客户端连接到FTP服务器的21端口，发送用户名和密码登录，登录成功后要list列表或者读取数据时，发送PASV命令到FTP服务器， 服务器在本地随机开放一个端口（1024以上），然后把开放的端口告诉客户端， 客户端再连接到服务器开放的端口进行数据传输
        ftpClient.enterLocalPassiveMode();
    }

    /**
     * @className FtpUtil
     * @author dnydys
     * @description 查询所有文件列表
     * @updateTime 2021/12/18 22:05
     * @return: void
     * @version 1.0
     */
    public FTPFile[] listFiles() throws IOException {
        if (FTPReply.isPositiveCompletion(ftpClient.sendCommand("OPTS UTF8", "ON"))) {
            LOCAL_CHARSET = "UTF-8";
        }
        ftpClient.setControlEncoding(LOCAL_CHARSET);
        return ftpClient.listFiles();
    }
    
    /**
     * @className FtpUtil
     * @author dnydys
     * @description 检索文件
     * @updateTime 2021/12/18 22:12 
     * @return: java.lang.Boolean
     * @version 1.0
     */
    public Boolean retrieveFile(String fileName, FileOutputStream outputStream) throws IOException {
        return ftpClient.retrieveFile(fileName, outputStream);
    }

    /**
     * @className FtpUtil
     * @author dnydys
     * @description
     * @updateTime 2021/12/18 23:07
     * @return: null
     * @version 1.0
     */
    public InputStream retrieveFileStream(String fileName) throws IOException {
        return ftpClient.retrieveFileStream(fileName);
    }


}
