package com.wjb.util;

import com.wjb.config.fastdfs.FastdfsConfig;
import com.wjb.config.fastdfs.FastdfsUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

@Lazy(false)
@Component
public class FilePreviewUtil {
	// 文件预览地址
	private static String filePreviewAddress;
	// 文件预览是否开启
	private static String filePreviewOpen;
	
	private static String filePreviewFastdfsStr;
	
	/**
	 * rest的文件 预览地址+fasdst服务名
	 */
	public static String restFilePreviewAddress;
	
	public static String restFilePreviewFastdfsStr;

	@Autowired
	private FastdfsConfig config;

	@PostConstruct
	public void init() {
		FilePreviewUtil.filePreviewAddress = config.filePreviewAddress;
		FilePreviewUtil.filePreviewFastdfsStr = config.filePreviewFastdfsStr;
		FilePreviewUtil.filePreviewOpen = config.filePreviewOpen;
		FilePreviewUtil.restFilePreviewAddress = config.restFilePreviewAddress;
		FilePreviewUtil.restFilePreviewFastdfsStr = config.restFilePreviewFastdfsStr;
	}
	/**
	 * 获取预览地址
	 * 
	 * @param url
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String getPreviewAddress(String url) {
		if (StringUtils.equals(filePreviewOpen, SysCode.NO)) {
			return url;
		}
		String fileAdd = null;
		try {
			url = filePreviewFastdfsStr + "/" + FastdfsUtils.getFileId(url);
			fileAdd = filePreviewAddress + URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileAdd;
	}

	/**
	 * 去除rest里的预览地址，添加为web里的预览地址
	 * 
	 * @param url
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String getWebPreviewAddress(String url) {
		if (StringUtils.equals(filePreviewOpen, SysCode.NO)) {
			return url;
		}
		try {
			url =filePreviewAddress+URLEncoder.encode(filePreviewFastdfsStr+getRestFileId(url), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return url;
	}
	
	/**
	 * 获取FastDFS文件id
	 * 
	 * @author ycli7
	 * @param url url
	 * @return 文件id
	 */
	public static String getRestFileId(String url) {
		if (StringUtils.isBlank(url)) {
			return url;
		}
		//除去rest中的文件预览地址
		if (StringUtils.startsWith(url, restFilePreviewAddress)) {
			url = StringUtils.removeStart(url, restFilePreviewAddress);
			try {
				url = URLDecoder.decode(url,"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			if(StringUtils.startsWith(url, restFilePreviewFastdfsStr)){
				url = StringUtils.removeStart(url, restFilePreviewFastdfsStr);
			}		
		}
		return url;
	}
	
	/**
	 * 获取去除预览地址后的fastdfs地址
	 * 
	 * @param url
	 * @return
	 */
	public static String getFastdfsAddress(String url) {
		if (StringUtils.equals(filePreviewOpen, SysCode.NO)) {
			return url;
		}
		String fastDfsFileAdd = null;
		try {
			if (StringUtils.startsWith(url,filePreviewAddress)) {
				url = StringUtils.removeStart(url, filePreviewAddress);
			}
			fastDfsFileAdd =  URLDecoder.decode(url,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fastDfsFileAdd;
	}
}
