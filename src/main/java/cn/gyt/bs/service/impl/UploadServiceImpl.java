package cn.gyt.bs.service.impl;

import cn.gyt.bs.service.UploadService;
import cn.gyt.bs.util.FtpUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public class UploadServiceImpl implements UploadService {

    @Override
    public Object upload(MultipartFile file, String newName) {
        //todo 校验文件名
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        newName = newName + fileName.substring(fileName.lastIndexOf("."));
        //上传文件到服务器
        InputStream input = null;
        try {
            input = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //调用工具类上传文件
        return FtpUtils.upload(input, "/image", newName);
    }

    /**
     * 删除文件
     *
     * @param filePath 文件路径
     */
    @Override
    public void delete(String filePath, String fileName) {
        FtpUtils.deleteDocument(filePath, fileName);
    }
}
