package com.wjb.controller;

import com.alibaba.fastjson.JSONObject;
import com.wjb.base.BaseController;
import com.wjb.config.fastdfs.FastdfsConfig;
import com.wjb.config.fastdfs.FastdfsUtils;
import com.wjb.config.fastdfs.FileService;
import com.wjb.model.UploadBase64Dto;
import com.wjb.util.FilePreviewUtil;
import com.wjb.util.SysCode;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.csource.common.NameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 共通方法
 */
@RestController
@RequestMapping(value = "/commer", method = {RequestMethod.POST, RequestMethod.GET})
public class CommonController extends BaseController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    // 图片上传的类型
    private String[] fileTypes = new String[]{"jpg", "gif", "bmp", "png", "jpeg", "JPG", "GIF", "BMP", "PNG", "JPEG",
            "ico", "ICO"};

    private String[] fileTypeArr = new String[]{"txt", "docx", "doc", "xlsx", "xls", "pdf", "rar", "zip", "jpg", "ppt", "pptx", "wps",
            "gif", "bmp", "png", "jpeg", "TXT", "DOCX", "DOC", "XLSX", "XLS", "PDF", "RAR", "ZIP", "JPG", "PPTX", "GIF", "BMP", "PPT", "WPS",
            "PNG", "JPEG"};

    /**
     * 文件上传服务
     */
    @Autowired
    private FileService fileService;

    /**
     * fastdfs配置
     */
    @Autowired
    private FastdfsConfig fastdfsConfig;

    /**
     * 上传文件
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/uploadbase64", method = RequestMethod.POST)
    public String uploadbase64(@RequestBody List<UploadBase64Dto> picList) {
        logger.info("调用uploadbase64....");
        List<Map<String, Object>> res = new ArrayList<>();
        for (int k = 0; k < picList.size(); k++) {
            UploadBase64Dto file = picList.get(k);
            Map<String, Object> map = new HashMap<>();
            if (StringUtils.isBlank(file.getData())) {
                map.put("flag", false);
                map.put("erroMsg", "文件base64编码为空!");
                res.add(map);
                continue;
            }
            if (StringUtils.isBlank(file.getFileName())) {
                map.put("flag", false);
                map.put("erroMsg", "文件名为空!");
                res.add(map);
                continue;
            }
            logger.debug("Base64上传文件...");
            // Base64解码
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] b = null;
            try {
                b = decoder.decodeBuffer(file.getData());
                for (int i = 0; i < b.length; ++i) {
                    if (b[i] < 0) {// 调整异常数据
                        b[i] += 256;
                    }
                }
            } catch (IOException e) {
                map.put("flag", false);
                map.put("erroMsg", "文件" + file.getFileName() + "图片解码失败！");
                res.add(map);
                continue;
            }
            String[] fstr = file.getFileName().split("\\.");
            String ext = fstr[fstr.length - 1];
            if (Arrays.asList(fileTypes).contains(ext)) {
                String path = fileService.storeFile(b, ext);
                String url = FastdfsUtils.getUrl(path, fastdfsConfig.fastdfsAddress);
                logger.debug("文件 {} 上传成功，文件ID为 {}", file.getFileName(), path);
                map.put("size", fileService.getFileInfo(path).getFileSize());
                map.put("fileName", file.getFileName());
                // 是否可预览
                if (StringUtils.equals(file.getIsPreView(), "1")) {
                    url = FilePreviewUtil.getPreviewAddress(url);
                }
                map.put("filePath", url);
                map.put("upLoadTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                map.put("flag", true);
            } else {
                map.put("flag", false);
                map.put("erroMsg", "文件" + file.getFileName() + "检测到非法文件，终止上传!");
                res.add(map);
                continue;
            }
            res.add(map);
        }
        return SUCCESS_FAIL_N(true, res, null);

    }

    /**
     * 文件上传接口
     *
     * @param flag 0 图片上传 1文件上传
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(HttpServletRequest session, MultipartHttpServletRequest request, @RequestParam String flag, String isPreView) {
        long startTime = System.currentTimeMillis();
        logger.info("文件开始上传");

        List<Map<String, Object>> mapPath = new ArrayList<>();
        // 取得request中的所有文件名
        Iterator<String> iter = request.getFileNames();
        // 文件查看路径
        String fileId = null;
        // 上传文件名
        String fileName = null;
        // 文件后缀名
        String fileSuffix = null;
        // 文件大小
        long size = 0;
        // 文件上传时间
        String upLoadTime = null;

        InputStream is = null;

        // 遍历上传的文件列表
        while (iter.hasNext()) {
            // 取得上传文件
            MultipartFile file = request.getFile(iter.next());
            if (file == null) {
                continue;
            }

            // 取得当前上传文件的文件名称
            fileName = file.getOriginalFilename();
            fileSuffix = FilenameUtils.getExtension(fileName);
            // 如果名称不为空,说明该文件存在，否则说明该文件不存在
            if (StringUtils.isBlank(fileName)) {
                continue;
            }
            // 0 图片上传 1文件上传
            if (SysCode.FileUpload.PICTURE.equals(flag)) {
                if (Arrays.asList(fileTypes).contains(fileSuffix)) {
                    try {
                        if (file.getBytes() != null) {
                            is = new ByteArrayInputStream(file.getBytes());
                            size = (long) file.getSize();
                            upLoadTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                            fileId = fileService.upload(fileName, size, is);
                        } else {
                            try {
                                size = (long) file.getSize();
                                upLoadTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                                fileId = fileService.upload(fileName, file.getSize(), file.getInputStream());
                            } catch (Exception e) {
                                logger.info("文件上传失败", e);
                                return SUCCESS_FAIL_N(false, null, "文件上传失败");
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        return SUCCESS_FAIL_N(false, null, "文件上传失败");
                    }
                    long endTime = System.currentTimeMillis();
                    logger.info("上传文件完成，耗时{}ms", endTime - startTime);
                    logger.info("上传文件的地址是：{}", FastdfsUtils.getUrl(fileId, fastdfsConfig.fastdfsAddress));
                    // 文件地址
                    String url = getUrl(isPreView, fileId);
                    Map<String, Object> map = new HashMap<>();
                    map.put("fileName", fileName);
                    map.put("filePath", url);
                    map.put("size", size);
                    map.put("upLoadTime", upLoadTime);
                    mapPath.add(map);
                }
            } else if (SysCode.FileUpload.File.equals(flag)) {

                if (Arrays.asList(fileTypeArr).contains(fileSuffix)) {
                    try {
                        if (file.getBytes() != null) {
                            is = new ByteArrayInputStream(file.getBytes());
                            size = (long) file.getSize();
                            upLoadTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                            fileId = fileService.upload(fileName, (long) file.getSize(), is);
                        } else {
                            try {
                                size = (long) file.getSize();
                                upLoadTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                                fileId = fileService.upload(fileName, file.getSize(), file.getInputStream());
                            } catch (IOException e) {
                                logger.info("文件上传失败");
                                return SUCCESS_FAIL_N(false, null, "文件上传失败");
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        return SUCCESS_FAIL_N(false, null, "文件上传失败");
                    } finally {
                        try {
                            is.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    // 文件地址
                    String url = getUrl(isPreView, fileId);
                    Map<String, Object> map = new HashMap<>();
                    map.put("fileName", fileName);
                    map.put("filePath", url);
                    map.put("size", size);
                    map.put("upLoadTime", upLoadTime);
                    mapPath.add(map);
                }
            }
        }
        if (mapPath.size() > 0) {
            long endTime = System.currentTimeMillis();
            logger.info("上传文件完成..........，耗时{}ms", endTime - startTime);
            Map<String, List<Map<String, Object>>> listMap = new HashMap<>();
            listMap.put("fileInfo", mapPath);
            return SUCCESS_FAIL_N(true, JSONObject.toJSON(listMap), null);
        }
        return SUCCESS_FAIL_N(false, null, "上传文件失败，请检查请求参数或稍后重试");
    }

    /**
     * @param isPreView
     * @param url
     * @return
     * @throws
     * @描述: 获取预览地址
     * @方法名: getUrl
     * @返回类型 String
     * @创建人 zpma
     * @创建时间 2018年12月8日上午11:13:55
     * @修改人 zpma
     * @修改时间 2018年12月8日上午11:13:55
     * @修改备注
     */
    private String getUrl(String isPreView, String url) {
        // 是否可预览
        if (StringUtils.equals(isPreView, SysCode.YES)) {
            url = FilePreviewUtil.getPreviewAddress(url);
        } else {
            url = FastdfsUtils.getUrl(url, fastdfsConfig.fastdfsAddress);
        }
        return url;
    }


    /**
     * 删除Fastdfs文件
     * 要删除的文件路径
     *
     * @param
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/fileDelete", method = RequestMethod.POST)
    public String removeFile(@RequestParam String filePath, String isPreView) throws UnsupportedEncodingException {
        if (filePath.contains("*")) {
            return SUCCESS_FAIL_N(false, null, "禁止使用特殊通配符！");
        }
        String[] paths = filePath.split(",");
        logger.info(String.valueOf(paths.length));
        logger.info(filePath);
        String deleteFailedFileNames = "";
        for (String p : paths) {
            if (StringUtils.isNotBlank(p)) {
                if (StringUtils.equals(isPreView, SysCode.YES)) {
                    p = FilePreviewUtil.getFastdfsAddress(p);
                }
                String fileName = "";
                NameValuePair[] mateData = fileService.getFileMetaInfo(FastdfsUtils.getFileId(p, fastdfsConfig.fastdfsAddress));
                for (int i = 0; mateData != null && i < mateData.length; i++) {
                    if (SysCode.FASTDFS_FILE_MATEDATE.FILENAME.equals(mateData[i].getName())) {
                        fileName = mateData[i].getValue();
                    }
                }
                int res = fileService.delete(FastdfsUtils.getFileId(p, fastdfsConfig.fastdfsAddress));
                if (res == 0) {
                    logger.info("文件path:{},文件名：{} 删除成功！", p, fileName);
                } else {
                    deleteFailedFileNames += "," + fileName;
                    logger.error("文件path:{},文件名：{} 删除失败！", p, fileName);
                }
            }
        }
        if (StringUtils.isNotBlank(deleteFailedFileNames)) {
            return SUCCESS_FAIL_N(false, null, "文件" + deleteFailedFileNames.substring(1) + "删除失败！");
        }
        return SUCCESS_FAIL_N(false, null, "文件删除成功！");
    }


}
