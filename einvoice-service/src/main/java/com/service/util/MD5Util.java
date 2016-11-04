package com.service.util;


import java.util.*;

import com.core.util.StringUtil;
import com.service.constants.InvoiceConstants;
import com.service.constants.InvoiceParamKeys;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

/**
 * Created by sdyang on 2016/10/8.
 */
public class MD5Util {

    private static Logger logger = Logger.getLogger(MD5Util.class);


    /**
     * 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
     */
    public static String createSign(Map<String, String> map,String key) {
        TreeMap<String, String> treemap = new TreeMap<String, String>(map);
        StringBuffer sb = new StringBuffer();
        Set<Map.Entry<String, String>> es = treemap.entrySet();
        Iterator<Map.Entry<String, String>> it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (StringUtil.isNotEmpty(v) && !InvoiceParamKeys.MERCHANT_SIGN.equals(k) && !InvoiceParamKeys.KEY.equals(k)
                    &&!InvoiceParamKeys.INVOICE_ORDERBODY_LIST.equals(k)&&!InvoiceParamKeys.SERVER_SIGN.equals(k)) {
                sb.append(k + InvoiceConstants.SYMBOL_EQUAL + v + InvoiceConstants.SYMBOL_AND);
            }
        }
        String requestParams = sb.append(InvoiceParamKeys.KEY + InvoiceConstants.SYMBOL_EQUAL + key).toString();
        logger.info("==========original signData==========：" + requestParams);
        String sign = sign(requestParams);

        return sign.toUpperCase();
    }

    public static boolean verifySignature(String data, String signature) throws Exception {
        logger.info("-----------验证签名-------------");
        String localSign = null;
        try {
            // 签名
            localSign = DigestUtils.md5Hex(data).toLowerCase();
            if (!signature.equalsIgnoreCase(localSign)) {
                logger.error("签名串：" + data.toString());
                logger.error("本地签名：" + localSign);
                logger.error("返回签名：" + signature);
                throw new Exception("验签失败！");
            }
            // 加密
            logger.info("-----------验签成功！-------------");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String sign(String data) {
        logger.info("-----------报文签名-------------");
        String ciphertext = null;
        try {
            // 签名
            StringBuilder buff = new StringBuilder(data);
            ciphertext = DigestUtils.md5Hex(buff.toString()).toLowerCase();
            // 加密
            logger.info("密文：" + ciphertext);
            logger.info("-----------签名成功！-------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ciphertext;
    }
}
