package com.task.service;

import com.core.bean.EinvoiceHead;
import com.core.constant.EinvoiceStatus;
import com.core.service.EinvoiceService;
import com.google.zxing.WriterException;
import com.itextpdf.text.DocumentException;
import com.task.util.DateUtil;
import com.task.util.FileUtil;
import com.task.util.PdfUtil;
import com.task.util.XmlUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by sdyang on 2016/10/7.
 */
@Service
public class EinvoiceXmlPdfService {

    private static Logger logger = Logger.getLogger(EinvoiceXmlPdfService.class);

    @Autowired
    private EinvoiceService einvoiceService;

    private final static String HomeDirectory = "einvoice";//主目录

    private final static String Template = "template";//模板文件目录

    private final static String XML = "xml";//XML文件目录

    private final static String XML_Request = "request";//请求开票xml文件

    private final static String XML_Result = "result";//开票结果xml文件

    private final static String PDF = "pdf";//生成电子发票pdf文件

    @Value("${einvoiceDir}")
    private String einvoiceDir;

    //XML文件后缀
    private final static String SUFFIX_XML = ".xml";

    //开票结果文件名
    private final static String INVOICE_RESULT = "_开票结果";

    //PDF文件后缀
    private final static String SUFFIX_PDF = ".pdf";

    //生成开票XML文件
    public void genXml(){
        String filename = null;
        List<EinvoiceHead> einvoiceHeadList = einvoiceService.getByStatus(EinvoiceStatus.RECEVICE_SUCCESS);

        if(einvoiceHeadList == null) return;

        for(EinvoiceHead head:einvoiceHeadList){
            filename = getHomeDirectory() + XML + File.separator + XML_Request + File.separator + getFileName(head) + SUFFIX_XML;
            if(XmlUtil.createXML(head,filename)) {
                einvoiceService.updateEinvoiceStatus(head.getPk_einvoicehead(), EinvoiceStatus.GENXML_SUCCESS);
            }
        }
    }

    //读取税控机的开票结果，更新发票信息
    public void readXml() throws org.dom4j.DocumentException {
        String filename = null;
        List<EinvoiceHead> einvoiceHeadList = einvoiceService.getByStatus(EinvoiceStatus.GENXML_SUCCESS);

        if(einvoiceHeadList == null) return;

        for(EinvoiceHead head:einvoiceHeadList){

            filename = getHomeDirectory() + XML + File.separator + XML_Result + File.separator + getFileName(head) + INVOICE_RESULT + SUFFIX_XML;
            if(XmlUtil.readXML(filename,head)) {

                if ("0000".equals(head.getReturncode())) {
                    head.setStatus(EinvoiceStatus.BILLING_SUCCESS.getIndex());//修改发票状态：开票成功
                } else {
                    head.setStatus(EinvoiceStatus.BILLING_FIAL.getIndex());
                }

                einvoiceService.updateEinvoiceHead(head);//更新发票信息
            }else{
                logger.error("");
            }

        }
    }

    //生成PDF文件
    public void genPdf() throws DocumentException, IOException, WriterException {
        String filename = null;
        String templateFile = null;
        List<EinvoiceHead> einvoiceHeadList = einvoiceService.getByStatus(EinvoiceStatus.BILLING_SUCCESS);

        if(einvoiceHeadList == null) return;

        for(EinvoiceHead head:einvoiceHeadList){
            filename = getHomeDirectory() + PDF + File.separator + getFileName(head) + SUFFIX_PDF;//生成的pdf文件目录
            if(FileUtil.isExist(filename)) {
                templateFile = getTemplateFile(head);
                PdfUtil.createPDF(head, templateFile, filename);
                einvoiceService.updateEinvoiceStatus(head.getPk_einvoicehead(), EinvoiceStatus.GENPDF_SUCCESS);//更新发票状态
            }
        }
    }

    //根据发票信息产生文件名  纳税人识别号 + 年 + 开票日期 + 发票流水号
    private String getFileName(EinvoiceHead einvoiceHead){
        String filename = null;
        filename = einvoiceHead.getXsf_nsrsbh() + File.separator + DateUtil.dateToString(einvoiceHead.getSubmitdate(),"yyyy")
                + File.separator + DateUtil.dateToString(einvoiceHead.getSubmitdate(),"yyyyMMdd")+File.separator
                + einvoiceHead.getFpqqlsh();
        return filename;
    }

    public String getEinvoiceDir() {
        return einvoiceDir;
    }

    public void setEinvoiceDir(String einvoiceDir) {
        this.einvoiceDir = einvoiceDir;
    }

    //主目录
   public String getHomeDirectory(){
       return getEinvoiceDir() + File.separator + HomeDirectory + File.separator;
   }

   //模板文件名
   public String getTemplateFile(EinvoiceHead einvoiceHead){
       return getHomeDirectory() + Template + File.separator+einvoiceHead.getXsf_nsrsbh() + SUFFIX_PDF;
   }
}
