package com.wjb.config.fastdfs;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.FileInfo;
import javax.validation.constraints.NotNull;
import java.io.InputStream;

public interface FileService {
	
	/**
	 * upload:(上传文件). <br/>
	 * @author kkfan
	 * @param fileName 文件名
	 * @param fileSize 文件大小
	 * @param is 文件流
	 * @return 文件id
	 */
	String upload(String fileName, Long fileSize, @NotNull InputStream is);

	/**
     * 存储文件
     *
     * @param file 文件二进制流
     * @return
     */
	public String storeFile(byte[] file, String ext);
	/**
     * 删除指定路径的Fastdfs文件
     *
     * @param path
     * @return
     */
	int delete(String p);

	FileInfo getFileInfo(String path);
	NameValuePair[] getFileMetaInfo(String fileId);
}
