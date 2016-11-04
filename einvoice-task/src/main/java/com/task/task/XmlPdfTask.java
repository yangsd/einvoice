package com.task.task;

import com.task.service.EinvoiceXmlPdfService;
import com.google.zxing.WriterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * 根据数据库表里的发票信息生成电子发票
 * Created by sdyang on 2016/10/7.
 */
@Component
public class XmlPdfTask {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private EinvoiceXmlPdfService xmlPdfService;

    //@Scheduled(fixedRate = 3000)
    public void genXml(){
        xmlPdfService.genXml();
    }

    //@Scheduled(fixedRate = 5000)
    public void readXml() {
        try {
            xmlPdfService.readXml();
        } catch (org.dom4j.DocumentException e) {
            e.printStackTrace();
        }

    }

    @Scheduled(fixedRate = 5000)
    public void genPdf(){
        try {
            xmlPdfService.genPdf();
        } catch (com.itextpdf.text.DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }


}
