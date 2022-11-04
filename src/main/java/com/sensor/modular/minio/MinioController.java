package com.sensor.modular.minio;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by 张益豪
 * @Classname MinioController
 * @Description 通用服务: 文件系统
 * @Date 2022/9/7 19:43
 */
@Tag(name = "minio", description = "minio文件服务")
@RestController
@RequestMapping("/minio")
@Transactional(rollbackFor=Exception.class)
public class MinioController {
    //
    //private final MinioUtil minioUtil;
    //private final GraphBucketServiceImpl graphBucketService;
    //
    //public MinioController(MinioUtil minioUtil, GraphBucketServiceImpl graphBucketService) {
    //    this.minioUtil = minioUtil;
    //    this.graphBucketService = graphBucketService;
    //}
    //
    //@PostMapping("/upload")
    //@Operation(summary = "上传文件")
    //public String upload(String projectId,@RequestParam("file") MultipartFile file) throws IOException {
    //    String bucketName = graphBucketService.selBucketByProjectId(projectId);
    //    return minioUtil.upload(bucketName, file);
    //}
    //
    //@GetMapping("/download")
    //@Operation(summary = "下载文件")
    //public void download(String projectId, String fileName, HttpServletResponse response) {
    //    String bucketName = graphBucketService.existBucket(projectId);
    //    if (StringUtils.isNotBlank(bucketName)) {
    //        minioUtil.downloadFile(bucketName, fileName, response);
    //    }
    //}
    //
    //@PostMapping("/preview")
    //@Operation(summary = "预览文件")
    //public Map<String,Object> preview(String projectId, String fileName) {
    //    String bucketName = graphBucketService.existBucket(projectId);
    //    if (StringUtils.isNotBlank(bucketName)) {
    //        return minioUtil.download(bucketName, fileName);
    //    }
    //    return Collections.emptyMap();
    //}
    //
    //@GetMapping("/previewImg")
    //@Operation(summary = "预览图片")
    //public String previewImg(String projectId, String fileName,HttpServletResponse response) throws IOException {
    //    String bucketName = graphBucketService.existBucket(projectId);
    //    if (StringUtils.isNotBlank(bucketName)) {
    //        minioUtil.getImage(bucketName,fileName,response);
    //    }
    //    return null;
    //}
    //
    //@PostMapping("/remove")
    //@Operation(summary = "删除文件")
    //public JsonResult remove(String projectId, String fileName) {
    //    String bucketName = graphBucketService.existBucket(projectId);
    //    if (StringUtils.isNotBlank(bucketName)) {
    //        List<String> fileNameList = new ArrayList<>();
    //        fileNameList.add(fileName);
    //        try {
    //            minioUtil.removeObjects(bucketName, fileNameList);
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
    //        return ResultTool.success();
    //    }
    //    return null;
    //}
    //
    //@PostMapping("/analysisPDF")
    //@Operation(summary = "解析PDF文件")
    //public String analysisPDF(String projectId,String fileName) throws IOException {
    //    String bucketName = graphBucketService.selBucketByProjectId(projectId);
    //    return minioUtil.analysisPDF(bucketName, fileName);
    //}
    //
    //@PostMapping("/analysisWord")
    //@Operation(summary = "解析Word文件")
    //public String analysisWord(String projectId,String fileName) throws IOException {
    //    String bucketName = graphBucketService.selBucketByProjectId(projectId);
    //    return minioUtil.analysisWord(bucketName, fileName);
    //}

}