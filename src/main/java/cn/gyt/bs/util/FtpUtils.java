package cn.gyt.bs.util;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

/**
 * 文件上传工具类
 *
 * @author Administrator
 */
@Component
@Slf4j
public class FtpUtils {

    /**
     * ftp服务器ip地址
     */
    private static String host;

    @Value("${ftp.host}")
    public void setHost(String host) {
        FtpUtils.host = host;
    }

    /**
     * 端口
     */
    private static int port;

    @Value("${ftp.port}")
    public void setPort(int port) {
        FtpUtils.port = port;
    }

    /**
     * 用户名
     */
    private static String username;

    @Value("${ftp.username}")
    public void setUsername(String username) {
        FtpUtils.username = username;
    }

    /**
     * 密码
     */
    private static String password;

    @Value("${ftp.password}")
    public void setPassword(String password) {
        FtpUtils.password = password;
    }

    /**
     * 存放文件的根目录
     */
    private static String rootPath;

    @Value("${ftp.rootPath}")
    public void setRootPath(String rootPath) {
        FtpUtils.rootPath = rootPath;
    }

    /**
     * 存放文件的路径
     */
    private static String url;

    @Value("${ftp.document.url}")
    public void setUrl(String url) {
        FtpUtils.url = url;
    }

    /**
     * 获取连接
     *
     * @return
     * @throws Exception
     */
    private static ChannelSftp getChannel() throws Exception {
        JSch jsch = new JSch();

        //-->ssh root@host:port
        Session sshSession = jsch.getSession(username, host, port);
        //密码
        sshSession.setPassword(password);

        Properties sshConfig = new Properties();
        sshConfig.put("StrictHostKeyChecking", "no");
        sshSession.setConfig(sshConfig);
        sshSession.connect();

        Channel channel = sshSession.openChannel("sftp");
        channel.connect();

        return (ChannelSftp) channel;
    }

    /**
     * ftp上传文件
     *
     * @param inputStream
     * @param filePath
     * @param fileName
     * @return
     */
    public static String upload(InputStream inputStream, String filePath, String fileName) {
        try {
            ChannelSftp sftp = getChannel();
            String path = rootPath + filePath + "/";
//            createDir(path, sftp);

            //上传文件
            sftp.put(inputStream, path + fileName);
            log.info("上传成功！");
            sftp.quit();
            sftp.exit();

            //处理返回的路径
            String resultFile;
            resultFile = url + fileName;
            return resultFile;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("上传失败：" + e.getMessage());
        }
        return null;
    }

    /**
     * 删除文件
     *
     * @param filePath 文件路径
     */
    public static void deleteDocument(String filePath,String fileName) {
        try {
            ChannelSftp sftp = getChannel();
            String path = rootPath + filePath + "/" + fileName;
            sftp.rm(path);
            sftp.quit();
            sftp.exit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
