package com.sensor.common.utils.minio;

import cn.hutool.http.ContentType;
import com.sensor.common.config.minio.MinioClientConfig;
import com.sensor.common.constant.SchemaUsedEnum;
import com.sensor.common.vo.ObjectItemVo;
import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.ooxml.extractor.POIXMLTextExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author by apple
 * @Classname MinioUtil
 * @Description TODO
 * @Date 2022/9/7 19:31
 */
@Slf4j
@Component
public class MinioUtil {

    public static final String DOC = ".doc";
    public static final String DOCX = ".docx";

    private final MinioClientConfig minioClientConfig;
    private final MinioClient minioClient;

    public MinioUtil(MinioClientConfig minioClientConfig, MinioClient minioClient) {
        this.minioClientConfig = minioClientConfig;
        this.minioClient = minioClient;
    }

    public String defaultConfig(String bucketName) {
        return "{\"Version\":\"2012-10-17\"," +
                "\"Statement\":[{\"Effect\":\"Allow\"," +
                "\"Principal\":{\"AWS\":[\"*\"]}," +
                "\"Action\":[\"s3:GetBucketLocation\",\"s3:ListBucket\"]," +
                "\"Resource\":[\"arn:aws:s3:::"+bucketName+"\"]}," +
                "{\"Effect\":\"Allow\"," +
                "\"Principal\":{\"AWS\":[\"*\"]}," +
                "\"Action\":[\"s3:GetObject\"]," +
                "\"Resource\":[\"arn:aws:s3:::"+bucketName+"/*\"]}]}";
    }

    /**
     * 判断bucket是否存在，不存在则创建
     * @param name:
     **/
    public void existBucket(String name) {
        try {
            boolean exists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(name).build());
            if (!exists) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(name).build());
                minioClient.setBucketPolicy(SetBucketPolicyArgs.builder().bucket(name).config(defaultConfig(name)).build());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建存储bucket
     * @param name:
     **/
    public Boolean makeBucket(String name) {
        try {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(name).build());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    /**
     * 清空某个bucket
     * @param bucketName
     */
    public void clearBucket(String bucketName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        boolean flag = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (flag) {
            try {
                // 递归列举某个bucket下的所有文件，然后循环删除
                Iterable<Result<Item>> iterable = minioClient.listObjects(ListObjectsArgs.builder()
                        .bucket(bucketName)
                        .recursive(true)
                        .build());
                for (Result<Item> itemResult : iterable) {
                    minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(itemResult.get().objectName()).build());
                }
                minioClient.removeBucket(RemoveBucketArgs.builder()
                        .bucket(bucketName)
                        .build());
            } catch (Exception e) {
                log.error("", e);
            }
        }
    }
    /**
     * 删除存储bucket
     * @param name 存储bucket名称
     * @return Boolean
     */
    public Boolean removeBucket(String name) {
        try {
            clearBucket(name);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String upload(String bucketName, MultipartFile file) throws IOException {
        existBucket(bucketName);
        return upload(bucketName, List.of(file));
    }

    /**
     * 批量上传文件
     *
     * @param multipartFile
     **/
    public String upload(String bucketName, List<MultipartFile> multipartFile) throws IOException{
        StringBuilder fileName = new StringBuilder();
        for (MultipartFile file : multipartFile) {
            String originalFilename = file.getOriginalFilename();
            if (StringUtils.isBlank(originalFilename)) {
                break;
            }
            String[] split = originalFilename.split("\\.");
            if (split.length > 1) {
                fileName.append(split[0]).append("_").append(System.currentTimeMillis()).append(".").append(split[split.length - 1]);
            } else {
                fileName.append(split[0]).append(System.currentTimeMillis());
            }
            InputStream in = null;
            try {
                in = file.getInputStream();
                minioClient.putObject(PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(String.valueOf(fileName))
                        .stream(in, in.available(), -1)
                        .contentType(file.getContentType())
                        .build()
                );
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return String.valueOf(fileName);
    }

    public void getImage(String bucketName, String fileName, HttpServletResponse response) throws IOException {
        InputStream in = null;
        try {
            in = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .build()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (in == null){
            response.sendError(404, "未能找到图片");
        }
        //图片类型
        String[] fileArr = fileName.split("\\.");
        String contentType = "";
        StringBuilder originalFileName = new StringBuilder();
        if (fileArr.length > 1){
            contentType = "image/" + fileArr[fileArr.length - 1];
            for (int i = 0; i < fileArr.length - 1; i++) {
                originalFileName.append(fileArr[i]);
                if (i != fileArr.length - 2){
                    originalFileName.append(".");
                }
            }
        }else {
            contentType = "application/octet-stream";
            originalFileName = new StringBuilder(fileName);
        }
        try {
            response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
            response.addHeader("X-Original-File-Name", originalFileName.toString());
            response.setContentType(contentType);
            ServletOutputStream outputStream = response.getOutputStream();
            if (in != null) {
                IOUtils.copy(in, outputStream);
            }
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 下载文件
     *
     * @param fileName:
     **/
    public Map<String,Object> download(String bucketName, String fileName) {
        Map<String,Object> resultMap = new HashMap<>();
        InputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            in = minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(fileName).build());
            out = new ByteArrayOutputStream();
            IOUtils.copy(in, out);
            //封装返回值
            byte[] bytes = out.toByteArray();
            resultMap.put("data",new String(bytes));
            resultMap.put("length",bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultMap;
    }

    /**
     * 查看bucketName里面的文件对象
     * @param bucketName:
     **/
    public List<ObjectItemVo> listObjects(String bucketName) {
        Iterable<Result<Item>> results = minioClient.listObjects(
                ListObjectsArgs.builder().bucket(bucketName).build());
        List<ObjectItemVo> objectItems = new ArrayList<>();
        try {
            for (Result<Item> result : results) {
                Item item = result.get();
                ObjectItemVo objectItem = new ObjectItemVo();
                objectItem.setObjectName(item.objectName());
                objectItem.setSize(item.size());
                objectItems.add(objectItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return objectItems;
    }

    /**
     * 解析PDF
     * @param fileName
     * @return
     */
    public String analysisPDF(String bucketName, String fileName){
        try (InputStream inputStream = minioClient.getObject(GetObjectArgs
                .builder()
                .bucket(bucketName)
                .object(fileName)
                .build()))
        {
            //multipartFile为multipartFile文件类型，将文件转化为文件流被PDDocument加载
            PDDocument document = PDDocument.load(inputStream);
            //使用PDFTextStripper 工具
            PDFTextStripper tStripper = new PDFTextStripper();
            //设置文本排序，有规则输出
            tStripper.setSortByPosition(true);
            //获取所有文字信息
            return tStripper.getText(document);
        }
        catch (Exception e)
        {
            log.error("文件解析异常");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解析Word
     * @param fileName
     * @return
     */
    public String analysisWord(String bucketName, String fileName) {
        String resullt = "";
        try (InputStream inputStream = minioClient.getObject(GetObjectArgs
                .builder()
                .bucket(bucketName)
                .object(fileName)
                .build())) {
            //首先判断文件中的是doc/docx
            if (fileName.endsWith(DOC)) {
                WordExtractor re = new WordExtractor(inputStream);
                resullt = re.getText();
                re.close();
            } else if (fileName.endsWith(DOCX)) {
                XWPFDocument doc = new XWPFDocument(inputStream);
                POIXMLTextExtractor extractor = new XWPFWordExtractor(doc);
                resullt = extractor.getText();
                extractor.close();
            } else {
                log.error("此文件不是word文件");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resullt;
    }

    /**
     * 文件下载
     * @param fileName 文件路径 (上传时返回的路径)
     * @param res response
     * @return Boolean
     */
    public void downloadFile(String bucketName,String fileName, HttpServletResponse res) {
        GetObjectArgs objectArgs = GetObjectArgs.builder().bucket(bucketName)
                .object(fileName).build();
        try (GetObjectResponse response = minioClient.getObject(objectArgs)){
            byte[] buf = new byte[1024];
            int len;
            try (FastByteArrayOutputStream os = new FastByteArrayOutputStream()){
                while ((len=response.read(buf))!=-1){
                    os.write(buf,0,len);
                }
                os.flush();
                byte[] bytes = os.toByteArray();
                res.setCharacterEncoding("utf-8");
                res.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
                try (ServletOutputStream stream = res.getOutputStream()){
                    stream.write(bytes);
                    stream.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 批量删除文件对象
     * @param bucketName:
     * @param objects: 对象名称集合
     **/
    public void removeObjects(String bucketName, List<String> objects) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        for (String object : objects) {
            minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(object).build());
        }
    }

    /**
     * 获取文件转换String
     * @param multipartFile:
     **/
    public List<String> changeStr(MultipartFile multipartFile){
        List<String> resultList = new ArrayList<>();
        try {
            if (multipartFile!=null) {
                InputStream bb = multipartFile.getInputStream();
                InputStreamReader streamReader = new InputStreamReader(bb);
                BufferedReader reader = new BufferedReader(streamReader);
                String line;
                while ((line = reader.readLine()) != null) {
                    resultList.add(line);
                }
                reader.close();
                bb.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

    /**
     * 获取文件转换String
     * @param
     **/
    public MultipartFile generateJson(String jsonName, String content) throws IOException{
        Writer write = null;
        File file = null;
        try {
            file  = new File(jsonName);
            // 如果文件存在，则删除文件
            if (file.exists()) {
                if (!file.delete()) {
                    log.error("文件删除失败");
                }
            }
            // 创建文件
            boolean newFile = file.createNewFile();
            if (newFile) {
                // 写入文件
                write = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
                write.write(content);
                write.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(write != null){
                write.close();
            }
        }
        MultipartFile multipartFile = getMultipartFile(file);
        if (!file.delete()) {
            log.error("文件删除失败");
        }
        return multipartFile;
    }

    private MultipartFile getMultipartFile(File file) throws IOException {
        FileInputStream fileInputStream = null;
        MultipartFile multipartFile = null;
        try {
            fileInputStream = new FileInputStream(file);
            multipartFile = new MockMultipartFile(file.getName(),file.getName(), ContentType.OCTET_STREAM.toString(),fileInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        }
        return multipartFile;
    }

    public boolean fileContentType(MultipartFile file, int fileType) {
        boolean flag = false;
        String contentType = file.getContentType();
        if(SchemaUsedEnum.FILE_TYPE_IMG.getIndex().equals(fileType)){
            if (SchemaUsedEnum.FILE_PNG.getDescribe().equals(contentType)) {
                flag = true;
            }
            if (SchemaUsedEnum.FILE_JPG.getDescribe().equals(contentType)) {
                flag = true;
            }
            if (SchemaUsedEnum.FILE_JPEG.getDescribe().equals(contentType)) {
                flag = true;
            }
        }
        if(SchemaUsedEnum.FILE_TYPE_DATA.getIndex().equals(fileType)){
            if (SchemaUsedEnum.FILE_CSV.getDescribe().equals(contentType)) {
                flag = false;
            }
            if (SchemaUsedEnum.FILE_TXT.getDescribe().equals(contentType)) {
                flag = true;
            }
        }
        return flag;
    }
}