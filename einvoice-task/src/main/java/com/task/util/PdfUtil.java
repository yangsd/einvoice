package com.task.util;

import com.core.bean.EinvoiceBody;
import com.core.bean.EinvoiceHead;
import com.core.util.StringUtil;
import com.google.zxing.WriterException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by sdyang on 2016/10/1.
 */
public class PdfUtil {

//    private static String templateFile = "C://invoicetemplate1.pdf";

//    private static String desFile = "C://invoiceresult.pdf";//

    public static  void createPDF(EinvoiceHead head,String templateFile,String desFile) throws IOException, DocumentException, WriterException {

        PdfReader reader = new PdfReader(templateFile); // 模版文件目录
        PdfStamper ps = new PdfStamper(reader, new FileOutputStream(desFile)); // 生成的输出流

        ArrayList<BaseFont> fontList = new ArrayList<BaseFont>();
        BaseFont bf =BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);

        AcroFields s = ps.getAcroFields();

        Iterator<String> it = s.getFields().keySet().iterator();
        while(it.hasNext()){
            String name = it.next().toString();
            s.setFieldProperty(name,"textfont",bf,null);
        }

        s.setField("invoicecode", head.getFp_dm());//发票代码
        s.setField("invoicenumber", head.getFp_hm());//发票号码
        s.setField("invoicedate", DateUtil.DateToString_cn(head.getKprq()));//开票日期
        s.setField("checkcode", head.getJym());//校验码
        s.setField("machineno",head.getJqbh());//税控设备编号

        s.setField("buyername", head.getGmf_mc());//购买方米高处
        s.setField("passwordarea", head.getFpmw());//发票密文

        EinvoiceBody  body = head.getEinvoiceBodies().get(0);

        s.setField("product", body.getXmmc());//项目名称
        s.setField("specmodel", body.getGgxh());//规格型号
        s.setField("unit", body.getDw());//单位
        s.setField("quantity", body.getXmsl()+"");//项目数量
        s.setField("price", body.getXmdj()+"");//项目单价
        s.setField("amount", body.getXmje()+"");//项目金额
        s.setField("taxrate", body.getSl()*100+"%");//税率
        s.setField("taxamount", body.getSe()+"");//税额


        s.setField("totalamount", "￥"+head.getHjje()+"");//合计金额
        s.setField("totaltax", "￥"+head.getHjse()+"");//合计税额

        s.setField("capitalamount", NumberToCN.change(head.getJshj()));//价税合计（大写）
        s.setField("lowercaseamount", "￥"+head.getJshj());//价税合计（小写）

        s.setField("salesname", head.getXsf_mc());//销售方名称
        s.setField("saletaxno", head.getXsf_nsrsbh());//销售方纳税识别号
        s.setField("saleaddtel", head.getXsf_dzdh());//销售方地址电话
        s.setField("salebank", StringUtil.getValue(head.getXsf_yhzh()));//销售方银行账户

        s.setField("payee", StringUtil.getValue(head.getSkr()));//收款人
        s.setField("check", StringUtil.getValue(head.getFhr()));//复核人
        s.setField("drawer", head.getKpr());//开票人
        s.setField("remark", StringUtil.getValue(head.getBz()));//备注

        //二维码可为空
        if(StringUtil.isNotEmpty(head.getEwm())) {
            BufferedImage bufferedImage = QrcodeUtil.createQrcode(head.getEwm());//二维码
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            boolean flag = ImageIO.write(bufferedImage, "png", out);
            byte[] b = out.toByteArray();

            //生成图片
            Image img = Image.getInstance(b);
            img.setAlignment(1);

            //酷友
//        img.scaleAbsolute(70,70);//控制图片大小
//        img.setAbsolutePosition(40,475);//控制图片位置


            //江财
            img.scaleAbsolute(60, 60);//控制图片大小
            img.setAbsolutePosition(40, 335);//控制图片位置

            PdfContentByte over = ps.getOverContent(1);

            over.addImage(img);
        }

        ps.setFormFlattening(true); //不可编辑
        ps.close();
        reader.close();
    }

}
