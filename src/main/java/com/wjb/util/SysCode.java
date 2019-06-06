package com.wjb.util;

/**
 * @Description: TODO
 */
public class SysCode {
    public static String YES = "1";
    public static String NO = "0";

    //启用停用
    public static String START = "1";
    public static String STOP = "0";

    public static final String CONTENTTYPE_TEXTHTML = "text/html";
    public static final String CONTENTTYPE_TEXTJSON = "text/json";
    public static final String CONTENTTYPE_TEXTXML = "text/xml";
    public static final String CONTENT_CHARSET_GBK = "GBK";
    public static final String CONTENT_CHARSET_GB2312 = "GB2312";
    public static final String CONTENT_CHARSET_UTF8 = "UTF-8";

    public static final String DICT_KEY = "dict_";

    public static final String NAV_KEY = "navDataSJ";

    public static final String NAV_PZ_KEY = "navDataPZ";

    public static final int SEQ_SIZE = 6;


    // 图片上传的类型
    public static String[] fileTypes = new String[]{"jpg", "gif", "bmp", "png",
            "jpeg", "JPG", "GIF", "BMP", "PNG", "JPEG", "ico", "ICO"};

    // 排序的字段
    public static String[] SORT_COLUNM = new String[]{"receiveNum", "totalReceiveNum", "advisoryNum", "totalAdvisoryNum"};
    // 升序or降序
    public static String[] SORT_TYPE = new String[]{"desc", "asc"};

    /**
     * 数据库类型
     */
    public static interface JDBC_TYPE {
        String ORACLE = "oracle";
        String MYSQL = "mysql";
    }

    public static interface IS_DELETE {
        String YES = "1";
        String NO = "0";
    }

    public static interface FASTDFS_FILE_MATEDATE{
        String FILENAME = "fileName";
        String FILEEXTNAME = "fileExtName";
        String FILELENGTH = "fileLength";
    }




    /**
     * 临时文件夹配置
     */
    public static final String UPLOAD_FILE_PATH = "upload_file";

    public static interface FileUpload {
        String PICTURE = "0";
        String File = "1";
    }

    /**
     * freemark模版文件
     */
    public static interface PRINT_FTL {
        String RECEIPT = "hzd.ftl";
        String FLOW = "lzd.ftl";
        String MULTI_MATTER = "multiMatter.ftl";
        //材料清单
        String MATTERIAL_LIST = "matterial_list.ftl";
    }

    /**
     * 文件的后缀
     */
    public static interface FILE_EXT {
        String PDF = ".pdf";

        String HTML = ".html";
    }

}
