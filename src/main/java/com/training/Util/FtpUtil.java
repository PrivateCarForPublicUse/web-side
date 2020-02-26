package com.training.Util;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;

@Component//这就是我们刚才加入的依赖
public class FtpUtil {

    //ftp服务器ip地址
    private static final String FTP_ADDRESS = "192.168.159.130";
    //端口号
    private static final int FTP_PORT = 21;
    //用户名
    private static final String FTP_USERNAME = "cloud2";
    //密码
    private static final String FTP_PASSWORD = "123";
    //路径都是/home/加上用户名
    public final String FTP_BASEPATH = "/home/cloud2/Documents/";
    //
//    @Autowired
//    private ResourceRepository resourceRepository;

    //参数传过来了文件和文件的输入流
    public boolean uploadFile(String originFileName, InputStream input) {
        boolean success = false;
        FTPClient ftp = new FTPClient();//这是最开始引入的依赖里的方法
        ftp.setControlEncoding("utf-8");
        try {
            int reply;
            ftp.connect(FTP_ADDRESS, FTP_PORT);// 连接FTP服务器
            ftp.login(FTP_USERNAME, FTP_PASSWORD);// 登录
            ftp.enterLocalPassiveMode();
            reply = ftp.getReplyCode();//连接成功会的到一个返回状态码
            System.out.println(reply);//可以输出看一下是否连接成功
            ftp.setControlEncoding("gbk");
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);//设置文件类型
            ftp.changeWorkingDirectory(FTP_BASEPATH);//修改操作空间
//对了这里说明一下你所操作的文件夹必须要有可读权限，chomd 777 文件夹名//这里我就是用的我的home文件夹
            ftp.storeFile(originFileName, input);//这里开始上传文件
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                System.out.println("连接失败");
                return success;
            }
            System.out.println("连接成功！");

            input.close();
            ftp.logout();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return success;
    }

    public boolean downloadFile(String pathname, String filename, String localpath) {
        localpath=System.getProperty("user.home")+System.getProperty("file.separator")+"Documents"+System.getProperty("file.separator")+"PrivateCarPublicUse";
        System.out.println(localpath);
        boolean flag = false;
        FTPClient ftp = new FTPClient();
        OutputStream os = null;
        try {
            System.out.println("开始下载文件");
            ftp.connect(FTP_ADDRESS, FTP_PORT);// 连接FTP服务器
            ftp.login(FTP_USERNAME, FTP_PASSWORD);// 登录
            int reply = ftp.getReplyCode();//得到连接成功的返回状态码
            System.out.println(reply);
            ftp.enterLocalActiveMode();//主动，一定要加上这几句设置为主动
//下面是将这个文件夹的所有文件都取出来放在ftpFiles这个文件数组里面
            ftp.changeWorkingDirectory(FTP_BASEPATH);//修改操作空间
            FTPFile[] ftpFiles = ftp.listFiles();
//然后便利这个数组找出和我们要下载的文件的文件名一样的文件
            for (FTPFile file : ftpFiles) {
                byte[] bytes = file.getName().getBytes("ISO-8859-1");
                file.setName(new String(bytes, "utf-8"));
                System.out.println("name: " + file.getName());//
                if (filename.equalsIgnoreCase(file.getName())) {//判断找到所下载的文件，file.getName就是服务器上对应的文件
//下面就是通过文件id再去数据库查找文件的中文名，将这个作为文件名下载到本地目录
                    String fileName = file.getName().substring(0, file.getName().lastIndexOf("."));
                    System.out.println("该文件的id："+fileName);
//                    Resource resource = resourceRepository.findResourceById(fileName);
//                    fileName = resource.getName();
                    File localFile = new File(localpath + "/" + fileName+".jpg");
                    if(!localFile.getParentFile().exists()||!localFile.getParentFile().isDirectory())
                        localFile.getParentFile().mkdir();
                    os = new FileOutputStream(localFile);//得到文件的输出流
                    ftp.retrieveFile(file.getName(), os);//开始下载文件
                    os.close();
                }
            }
            ftp.logout();
            flag = true;
            System.out.println("下载文件成功");
        } catch (Exception e) {
            System.out.println("下载文件失败");
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }
}