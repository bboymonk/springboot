package com.wjb.util;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/***
 * 图片压缩处理类
 */
public class CompressPictureUtil {
    protected static Logger logger = LoggerFactory.getLogger(CompressPictureUtil.class);

    //默认压缩质量，参数1为最高质量，一般0.8即可
    public final static float DEFAULT_QUALITY = 0.8f;

    public static byte[] compressPicture(InputStream inputStream) throws IOException {
        ByteArrayOutputStream outputStream = null;
        try {
            outputStream = new ByteArrayOutputStream();
            Thumbnails.of(inputStream).scale(1f).outputQuality(DEFAULT_QUALITY).toOutputStream(outputStream);
            return outputStream.toByteArray();
        } finally {
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(outputStream);
        }
    }
}

