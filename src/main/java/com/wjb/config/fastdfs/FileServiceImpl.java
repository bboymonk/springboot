package com.wjb.config.fastdfs;

import com.iflytek.sgy.filestorage.fastdfs.pool.FastdfsPool;
import com.iflytek.sgy.filestorage.fastdfs.pool.StorageClient;
import com.wjb.util.CompressPictureUtil;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.UploadStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;


@Service(value = "fileService")
public class FileServiceImpl implements FileService {

	private static Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
	
	public static final String SUPPORT_IMAGE_FORMAT = ".+\\.(jpg|png|jpeg)$";
	
	/**
	 * fastdfsPool
	 */
	@Autowired
	private FastdfsPool fastdfsPool;
	
	@Override
	public String upload(String fileName, Long fileSize, InputStream is) {
		String result = null;
		// 获取FastDFS客户端
		StorageClient client = null;
		//压缩处理后的图片
		File newFile = null;
		try {
			client = fastdfsPool.getResource();
			
			// 获取文件后缀
			String fileExtName = FilenameUtils.getExtension(fileName);
			//判断文件格式，是图片格式即压缩处理
			if(fileName != null && fileName.toLowerCase().matches(SUPPORT_IMAGE_FORMAT)){
				//压缩图片
				byte[] fileData = CompressPictureUtil.compressPicture(is);
				is = new ByteArrayInputStream(fileData);
				fileSize = (long) fileData.length;
			}
			
			// 设置FastDFS文件的NameValue
			NameValuePair[] metaList = new NameValuePair[3];
			metaList[0] = new NameValuePair("fileName", fileName);
			
			metaList[1] = new NameValuePair("fileExtName", fileExtName);
			metaList[2] = new NameValuePair("fileLength", String.valueOf(fileSize));
			result = client.upload_file1(null, fileSize, new UploadStream(is,fileSize), fileExtName, metaList);
		} catch (Exception e) {
			logger.warn("Upload file \"{}\" fails，exception:{}", fileName, e);
		} finally {
			returnResource(client);
			IOUtils.closeQuietly(is);
			if (newFile!=null && newFile.exists()) {
				newFile.delete();
			}
		}
		return result;
	}
	
	/**
     * 存储文件
     *
     * @param file 文件二进制流
     * @return
     */
	@Override
    public String storeFile(byte[] file, String ext) {
        try {
            StorageClient storageClient = fastdfsPool.getResource();
            String id = storageClient.upload_file1(file, ext, null);
            fastdfsPool.returnResource(storageClient);
            return id;
        } catch (Exception e) {
            logger.error("写入目标文件出错！", e);
            return null;
        }
    }
    
	/**
     * 删除指定路径的Fastdfs文件
     *
     * @param path
     * @return
     */
	@Override
    public int delete(String path) {
    	StorageClient storageClient = null;
        try {
            storageClient = fastdfsPool.getResource();
            int i = storageClient.delete_file1(path);
            fastdfsPool.returnResource(storageClient);
            return i;
        } catch (Exception e) {
            logger.error("文件服务器连接异常！", e);
            return 0;
        } finally {
        	returnResource(storageClient);
        }
    }
	
	/**
	 * 释放FastDFS连接
	 * @author kkfan
	 * @param client FastDFS客户端
	 */
	private void returnResource(StorageClient client){
		if(client != null){
			try {
				fastdfsPool.returnResource(client);
			} catch (Exception e) {
				logger.warn("释放FastDFS连接失败", e);
			}
		}
	}

	@Override
	public FileInfo getFileInfo(String fileId) {
		StorageClient storageClient = null;
        try {
            storageClient = fastdfsPool.getResource();
            FileInfo fileInfo = storageClient.get_file_info1(fileId);
            fastdfsPool.returnResource(storageClient);
            return fileInfo;
        } catch (Exception e) {
            logger.error("文件服务器连接异常！", e);
            return null;
        } finally {
        	returnResource(storageClient);
        }
	}
	
	@Override
	public NameValuePair[] getFileMetaInfo(String fileId){
		StorageClient storageClient = null;
        try {
            storageClient = fastdfsPool.getResource();
            NameValuePair[] metadata = storageClient.get_metadata1(fileId);
            fastdfsPool.returnResource(storageClient);
            return metadata;
        } catch (Exception e) {
            logger.error("文件服务器连接异常！", e);
            return null;
        } finally {
        	returnResource(storageClient);
        }
	}

}
