package com.sensor.modular.minio;

import com.sensor.common.utils.minio.MinioUtil;
import com.sensor.common.vo.JsonResult;
import com.sensor.common.vo.ResultTool;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author by apple
 * @Classname MinioController
 * @Description 通用服务: 文件系统
 * @Date 2022/9/7 19:43
 */
@Tag(name = "minio文件服务", description = "minio文件服务")
@RestController
@RequestMapping("/minio")
@Transactional(rollbackFor=Exception.class)
public class MinioController {

    private final MinioUtil minioUtil;

    public MinioController(MinioUtil minioUtil) {
        this.minioUtil = minioUtil;
    }

    @PostMapping("/upload")
    @Operation(summary = "上传文件")
    public String upload(String bucketName,@RequestParam("file") MultipartFile file) throws IOException {
        return minioUtil.upload(bucketName, file);
    }

    @GetMapping("/download")
    @Operation(summary = "下载文件")
    public void download(String bucketName, String fileName, HttpServletResponse response) {
        if (StringUtils.isNotBlank(bucketName)) {
            minioUtil.downloadFile(bucketName, fileName, response);
        }
    }

    @PostMapping("/preview")
    @Operation(summary = "预览文件")
    public Map<String,Object> preview(String bucketName, String fileName) {
        if (StringUtils.isNotBlank(bucketName)) {
            return minioUtil.download(bucketName, fileName);
        }
        return Collections.emptyMap();
    }

    @GetMapping("/previewImg")
    @Operation(summary = "预览图片")
    public String previewImg(String bucketName, String fileName,HttpServletResponse response) throws IOException {
        if (StringUtils.isNotBlank(bucketName)) {
            minioUtil.getImage(bucketName,fileName,response);
        }
        return null;
    }

    @PostMapping("/remove")
    @Operation(summary = "删除文件")
    public JsonResult remove(String bucketName, String fileName) {
        if (StringUtils.isNotBlank(bucketName)) {
            List<String> fileNameList = new ArrayList<>();
            fileNameList.add(fileName);
            try {
                minioUtil.removeObjects(bucketName, fileNameList);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ResultTool.success();
        }
        return null;
    }

    @PostMapping("/analysisPDF")
    @Operation(summary = "解析PDF文件")
    public String analysisPDF(String bucketName,String fileName) {
        return minioUtil.analysisPDF(bucketName, fileName);
    }

    @PostMapping("/analysisWord")
    @Operation(summary = "解析Word文件")
    public String analysisWord(String bucketName,String fileName) {
        return minioUtil.analysisWord(bucketName, fileName);
    }

}