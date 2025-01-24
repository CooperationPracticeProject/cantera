package com.bytedance.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class FileUpload {

    @Value("${upload.path}")
    private String uploadPath;

    // 允许上传的图片类型
    private static final List<String> ALLOWED_IMAGE_TYPES = Arrays.asList(
            "image/jpeg", // JPEG 图片
            "image/png",  // PNG 图片
            "image/gif"   // GIF 图片
    );

    public Result<String> upload(MultipartFile file) {
        System.out.println("上传路径 = " + uploadPath);
        // 检查文件是否为空
        if (file.isEmpty()) {
            return Result.of(Result.ResultCode.FILE_IS_EMPTY, null);
        }

        // 检查文件类型是否为图片
        String fileContentType = file.getContentType();
        if (fileContentType == null || !ALLOWED_IMAGE_TYPES.contains(fileContentType)) {
            return Result.of(Result.ResultCode.FILE_TYPE_NOT_ALLOWED, null);
        }

        // 生成唯一的文件名
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        File dest = new File(uploadPath + File.separator + fileName);

        try {
            // 检查上传路径是否存在，如果不存在则创建
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                if (!uploadDir.mkdirs()) {
                    return Result.of(Result.ResultCode.FILE_PATH_NOT_FOUND, null);
                }
            }
            // 保存文件到指定路径
            file.transferTo(dest);
            // 返回文件的访问 URL 路径
            String fileUrl = "/" + fileName;
            return Result.of(Result.ResultCode.FILE_UPLOAD_SUCCESS, fileUrl);
        } catch (IOException e) {
            return Result.of(Result.ResultCode.FILE_UPLOAD_FAIL, null);
        }
    }
}