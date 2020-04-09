package cn.gyt.bs.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 上传文件服务接口
 */
public interface UploadService {

    /**
     * 上传文件,并且重命名
     *
     * @param file    文件
     * @param newName 重命名该
     * @return 文件路径
     */
    Object upload(MultipartFile file, String newName);

    /**
     * 删除文件
     *
     * @param filePath 文件路径
     */
    void delete(String filePath, String fileName);
}
