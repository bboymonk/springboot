/**	
 * <br>
 * Copyright 2014 IFlyTek. All rights reserved.<br>
 * <br>			 
 * Package: com.iflytek.sgy.yzt.utils <br>
 * FileName: FileUtils.java <br>
 * <br>
 * @version
 * @author xjchen@iflytek.com
 * @created 2016-2-24
 * @last Modified 
 * @history
 */

package com.wjb.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 *  文件工具包
 *  
 *  @author xjchen@iflytek.com
 *  @created 2016-2-24 上午09:35:29
 *  @lastModified       
 *  @history           
 */

public class FileUtils {
	
	private static Logger logger = LoggerFactory.getLogger(FileUtils.class);

	/**
	 *  写入文件
	 *  @param path
	 *  @param sqlStream
	 *  @author xjchen@iflytek.com
	 *  @created 2016-2-24 上午09:23:32
	 *  @lastModified       
	 *  @history           
	 */
	public static void writeFileDic(String path, InputStream sqlStream, String fileName, String fileType) {
		FileOutputStream o = null;
		try {
			String sqlFileName = path + "/" + fileName + fileType;
			o = new FileOutputStream(sqlFileName);
			int len = 0;
			byte[] buffer = new byte[1024];
			while ((len = sqlStream.read(buffer)) != -1) {
				o.write(buffer, 0, len);
			}
			o.flush();
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(),e);;
		} finally {
			if (null != o) {
				try {
					o.close();
					o = null;
				} catch (IOException e) {
					logger.error(e.getLocalizedMessage(),e);;
				}
			}
		}

	}

	/**
	 *  文件转byte[]
	 *  @param path 文件路径
	 *  @return
	 *  @author xjchen@iflytek.com
	 *  @created 2016-2-24 上午09:45:35
	 *  @lastModified       
	 *  @history           
	 */
	public static byte[] fileToBytes(String path) {
		File file = new File(path);
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			file.delete();
			return bos.toByteArray();
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(),e);;
		}
		return null;

	}

	/**
	 * 递归删除目录下的所有文件及子目录下所有文件
	 * @param dir 将要删除的文件目录
	 * @return boolean Returns "true" if all deletions were successful.
	 *                 If a deletion fails, the method stops attempting to
	 *                 delete and returns "false".
	 */
	public static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		// 目录此时为空，可以删除
		return dir.delete();
	}

	/**
	 *  读取文件内容
	 *  @param path
	 *  @return
	 *  @author xjchen@iflytek.com
	 *  @created 2016-3-3 下午01:54:21
	 *  @lastModified       
	 *  @history           
	 */
	public static String readFile(String path) {
		File file = new File(path);
		StringBuilder result = new StringBuilder();
		try {
			FileInputStream fin = new FileInputStream(file);
			InputStreamReader reader = new InputStreamReader(fin, "utf-8");
			char[] buffer = new char[1024];
			int len = 0;
			while ((len = reader.read(buffer)) != -1) {
				result.append(buffer, 0, len);
			}
			reader.close();
			fin.close();
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(),e);;
		}
		return result.toString();
	}

	/**
	 * <p>方法名称：字节流写入文件</p>
	 * <p>方法说明：</p>
	 * @param data 字节流
	 * @param filePath 文件名
	 * @autho yangzhang7
	 * @time 2016年7月12日 下午1:48:20
	 */
	public static void bytesToFile(byte[] data, String filePath) {
		File file = new File(filePath);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}

		FileOutputStream o = null;
		try {
			o = new FileOutputStream(filePath);
			o.write(data, 0, data.length);
			o.flush();
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(),e);;
		} finally {
			if (null != o) {
				try {
					o.close();
					o = null;
				} catch (IOException e) {
					logger.error(e.getLocalizedMessage(),e);;
				}
			}
		}

	}

	/**
	 * <p>方法名称：删除特定目录下pattern所对应的文件</p>
	 * <p>方法说明：</p>
	 * @param dirPath
	 * @param pattern
	 * @autho yangzhang7
	 * @time 2016年7月12日 下午1:58:15
	 */
	public static void deleteFile(File dirPath, String pattern) {
		if (dirPath.isDirectory()) {
			String[] children = dirPath.list();
			for (int i = 0; i < children.length; i++) {
				if (children[i].matches(pattern)) {
					new File(dirPath + File.separator + children[i]).delete();
				}
			}
		}
	}

	/**
	 * <p>方法名称：如果文件夹不存在在创建</p>
	 * <p>方法说明：</p>
	 * @param tempPath
	 * @autho yangzhang7
	 * @time 2016年8月22日 下午3:24:43
	 */
	public static void mkDirIfNotExsits(String dirPath) {
		File file = new File(dirPath);
		if (!file.exists()) {
			file.mkdirs();
		}

	}
	
	/** 
     * 从输入流中获取数据 
     * @param inStream 输入流 
     * @return 
     * @throws Exception 
     */  
	public static byte[] readInputStream(InputStream fis) throws IOException {
		 ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
	        byte[] buffer = new byte[1024];  
	        int len = 0;  
	        while( (len=fis.read(buffer)) != -1 ){  
	            outStream.write(buffer, 0, len);  
	        }  
	        fis.close();  
	        return outStream.toByteArray();
	}

}
