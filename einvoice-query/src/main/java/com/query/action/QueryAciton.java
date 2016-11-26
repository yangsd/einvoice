package com.query.action;

import com.core.bean.EinvoiceHead;
import com.core.constant.EinvoiceStatus;
import com.core.service.EinvoiceService;
import com.core.util.StringUtil;
import com.query.util.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * Created by sdyang on 2016/10/16.
 */
@Controller
public class QueryAciton {

    private static Logger logger = Logger.getLogger(QueryAciton.class);

    @Autowired
    private EinvoiceService einvoiceService;

    @Value("${einvoice.pdf.dic}")
    private String pdfDic;

    //查询首页
    @RequestMapping(value = "index",method = RequestMethod.GET)
    public String index(Model model){
        return "index";
    }


    @RequestMapping(value = "search",method = RequestMethod.POST)
    public String list(Model model, @RequestParam("orderno") String orderno){

        List<EinvoiceHead> einvoiceHeads = null;

        if(StringUtil.isNotEmpty(orderno)) {
            einvoiceHeads = einvoiceService.getByOrdernoAndStatus(orderno, EinvoiceStatus.GENPDF_SUCCESS);
        }
        if(einvoiceHeads==null || einvoiceHeads.size()==0){
            return "redirect:/index";
        }
        model.addAttribute("list",einvoiceHeads);
        model.addAttribute("bodys",einvoiceHeads.get(0).getEinvoiceBodies());
        return "list";
    }

    //下载PDF文件
    @RequestMapping(value = "download/{pk_einvoicehead}",method=RequestMethod.GET)
    public void downPdf(@PathVariable("pk_einvoicehead")Long pk_einvoicehead, HttpServletRequest request, HttpServletResponse response){

        EinvoiceHead einvoice = einvoiceService.findOne(pk_einvoicehead);
        String filedic = getFileDir(einvoice);
        String filename =  einvoice.getFpqqlsh()+".pdf";

        response.setContentType("application/pdf");
        response.setHeader("Content-Type", "application/force-download");
        try {
            response.setHeader("Content-Disposition", "filename="+ new String(filename.getBytes("GBK"), "iso8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String fullFileName = pdfDic + File.separator + filedic +filename;

        //读取文件
        InputStream in = null;
        try {
            in = new FileInputStream(fullFileName);
            OutputStream out = response.getOutputStream();

            //写文件
            int b;
            while((b=in.read())!= -1)
            {
                out.write(b);
            }
            in.close();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPdfDic() {
        return pdfDic;
    }

    public void setPdfDic(String pdfDic) {
        this.pdfDic = pdfDic;
    }

    private String getFileDir(EinvoiceHead einvoiceHead){
        String dir = null;
        dir = "einvoice"+File.separator+"pdf"+File.separator+einvoiceHead.getXsf_nsrsbh() + File.separator + DateUtil.dateToString(einvoiceHead.getSubmitdate(),"yyyy")
                + File.separator + DateUtil.dateToString(einvoiceHead.getSubmitdate(),"yyyyMMdd")+File.separator;
        return dir;
    }
}
