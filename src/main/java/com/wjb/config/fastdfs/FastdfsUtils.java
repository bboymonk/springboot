package com.wjb.config.fastdfs;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Fastdfs工具类
 */
@Lazy(false)
@Component
public class FastdfsUtils {

	private static FastdfsUtils fastdfsUtils;
	private static String filePreviewAddress;
	private static String fastdfsAddress;
	private static String filePreviewFastdfsStr;
	@Autowired
	private FastdfsConfig fastdfsConfig;

	@PostConstruct
	public void init() {
		fastdfsUtils = this;
		fastdfsUtils.setFastdfsAddress(fastdfsConfig.fastdfsAddress);
		fastdfsUtils.setFastdfsPreviewStr(fastdfsConfig.filePreviewFastdfsStr);
		fastdfsUtils.setFilePreviewAddress(fastdfsConfig.filePreviewAddress);
	}

	/**
	 * 文件预览地址
	 * 
	 * @描述:
	 * @方法名: setFilePreviewAddress
	 * @param filePreviewAddress
	 * @since
	 * @throws
	 */
	public void setFilePreviewAddress(String filePreviewAddress) {
		FastdfsUtils.filePreviewAddress = filePreviewAddress;
	}

	public void setFastdfsAddress(String fastdfsAddress) {
		FastdfsUtils.fastdfsAddress = fastdfsAddress;
	}

	public void setFastdfsPreviewStr(String filePreviewFastdfsStr) {
		FastdfsUtils.filePreviewFastdfsStr = filePreviewFastdfsStr;
	}

	/**
	 * 获取FastDFS完整的url
	 * 
	 * 原url
	 * @return FastDFS完整的url
	 */
	public static String getUrl(String url) {
		if (StringUtils.startsWith(url, "group")) {
			url = fastdfsAddress + url;
		}
		return url;
	}

	/**
	 * 获取FastDFS文件id
	 * 
	 *            url
	 * @return 文件id
	 */
	public static String getFileId(String url) {
		if (StringUtils.isBlank(url)) {
			return url;
		}
		if (StringUtils.startsWith(url, filePreviewAddress)) {
			url = StringUtils.removeStart(url, filePreviewAddress);
		}
		if (StringUtils.startsWith(url, filePreviewFastdfsStr + "/")) {
			url = StringUtils.removeStart(url, filePreviewFastdfsStr + "/");
		} else if (StringUtils.startsWith(url, fastdfsAddress)) {
			url = StringUtils.removeStart(url, fastdfsAddress);
		}
		return url;
	}

	/**
	 * 获取FastDFS完整的url
	 * 
	 *            FastDFS文件id
	 * @return FastDFS完整的url
	 */
	public static String getUrl(String url, String fastdfsAddress) {
		if (StringUtils.startsWith(url, "group")) {
			url = fastdfsAddress + url;
		}
		return url;
	}

	/**
	 * 获取FastDFS文件id
	 * 
	 * @param url
	 * @return 文件id
	 */
	public static String getFileId(String url, String fastdfsAddress) {
		if (StringUtils.isBlank(url)) {
			return url;
		}
		if (StringUtils.startsWith(url, filePreviewAddress)) {
			url = StringUtils.removeStart(url, filePreviewAddress);
		}
		if (StringUtils.startsWith(url, filePreviewFastdfsStr + "/")) {
			url = StringUtils.removeStart(url, filePreviewFastdfsStr + "/");
		} else if (StringUtils.startsWith(url, fastdfsAddress)) {
			url = StringUtils.removeStart(url, fastdfsAddress);
		}
		return url;
	}
}
