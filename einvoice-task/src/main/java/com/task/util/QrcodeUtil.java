package com.task.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.itextpdf.text.pdf.BarcodePDF417;
import org.apache.commons.lang.StringUtils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Hashtable;

/**
 * 二维码工具类
 * Created by sdyang on 2016/4/23.
 */
public class QrcodeUtil {

    public final static int WIDTH = 215;
    public final static int HEIGHT = 215;

    public static void createQrcode(String regUrl,HttpServletResponse response) throws WriterException {

        String format = "jpg"; //二维码的图片格式
        Hashtable hints = new Hashtable();

        hints.put(EncodeHintType.CHARACTER_SET, "gbk"); //内容所使用编码

        BitMatrix bitMatrix = new MultiFormatWriter().encode(regUrl,
                BarcodeFormat.QR_CODE, WIDTH, 215, hints);
        MatrixToImageWriter.toWrite(bitMatrix, format, response);
    }

    /**
     * 根据url链接生成二维码
     * @param url
     * @return
     * @throws WriterException
     */
    public static BufferedImage createQrcode(String url) throws WriterException {
        Hashtable hints = new Hashtable();

        hints.put(EncodeHintType.CHARACTER_SET, "gbk");//内容所使用编码

        hints.put(EncodeHintType.MARGIN, 1);

        BitMatrix bitMatrix = new MultiFormatWriter().encode(url,
                BarcodeFormat.QR_CODE, 100, 100, hints);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    public static byte[] generatePdf417Image(String strInfo, String encode,
                                      String imgFileExt) throws Exception {
        if (StringUtils.isEmpty(strInfo)) {
            throw new Exception("二维条码的文本信息参数不能为空！");
        }
        if (StringUtils.isEmpty(encode)) {
            encode = "UTF-8";
        }

        BarcodePDF417 barcodePDF417 = new BarcodePDF417();

        barcodePDF417.setText(strInfo.getBytes(encode));

        Image pdfImg = barcodePDF417.createAwtImage(Color.black, Color.white);

        BufferedImage img = new BufferedImage((int) pdfImg.getWidth(null),
                (int) pdfImg.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics g = img.getGraphics();

        g.drawImage(pdfImg, 0, 0, Color.white, null);

        ByteArrayOutputStream os = new ByteArrayOutputStream();

        ImageIO.write(img, imgFileExt, os);

        byte[] buffs = os.toByteArray();

        os.close();

        return buffs;
    }

}
