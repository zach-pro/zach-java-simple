package com.zach.common.utils.minio;

import cn.hutool.http.ContentType;
import com.zach.common.config.minio.MinioClientConfig;
import com.zach.common.constant.SchemaUsedEnum;
import com.zach.common.vo.ObjectItemVo;
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
     * ??????bucket?????????????????????????????????
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
     * ????????????bucket
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
     * ????????????bucket
     * @param bucketName
     */
    public void clearBucket(String bucketName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        boolean flag = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (flag) {
            try {
                // ??????????????????bucket???????????????????????????????????????
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
     * ????????????bucket
     * @param name ??????bucket??????
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
     * ??????????????????
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
            response.sendError(404, "??????????????????");
        }
        //????????????
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
     * ????????????
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
            //???????????????
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
     * ??????bucketName?????????????????????
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
     * ??????PDF
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
            //multipartFile???multipartFile?????????????????????????????????????????????PDDocument??????
            PDDocument document = PDDocument.load(inputStream);
            //??????PDFTextStripper ??????
            PDFTextStripper tStripper = new PDFTextStripper();
            //????????????????????????????????????
            tStripper.setSortByPosition(true);
            //????????????????????????
            return tStripper.getText(document);
        }
        catch (Exception e)
        {
            log.error("??????????????????");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * ??????Word
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
            //???????????????????????????doc/docx
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
                log.error("???????????????word??????");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resullt;
    }

    /**
     * ????????????
     * @param fileName ???????????? (????????????????????????)
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
     * ????????????????????????
     * @param bucketName:
     * @param objects: ??????????????????
     **/
    public void removeObjects(String bucketName, List<String> objects) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        for (String object : objects) {
            minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(object).build());
        }
    }

    /**
     * ??????????????????String
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
     * ??????????????????String
     * @param
     **/
    public MultipartFile generateJson(String jsonName, String content) throws IOException{
        Writer write = null;
        File file = null;
        try {
            file  = new File(jsonName);
            // ????????????????????????????????????
            if (file.exists()) {
                if (!file.delete()) {
                    log.error("??????????????????");
                }
            }
            // ????????????
            boolean newFile = file.createNewFile();
            if (newFile) {
                // ????????????
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
            log.error("??????????????????");
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