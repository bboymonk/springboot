package com.wjb.util.qRcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 生成二维码，所需jar包(com.google.zxing  (core & javase))
*@Author:wjb
*@params:
*@Date:18:10 2017/10/26
*/
public class CreateQrcode {
    public static void main(String[] args) {
        int width = 300;
        int height = 300;
        String format = "png";
        String content = "love";
//        定义二维码的参数
        Map map = new HashMap(5);
        map.put(EncodeHintType.CHARACTER_SET,"utf-8");
        map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        map.put(EncodeHintType.MARGIN,2);

        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height);
            File file = new File("f:/wjb");
            MatrixToImageWriter.writeToFile(bitMatrix,format,file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
