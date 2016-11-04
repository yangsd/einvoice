package com.service.service;

import com.core.bean.User;
import com.core.dao.IUserDao;
import com.service.constants.InvoiceParamKeys;
import com.service.constants.SignType;
import com.service.util.MD5Util;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by sdyang on 2016/10/10.
 */
@Service
@Transactional
public class SignVerifyService {

    private static Logger logger = Logger.getLogger(SignVerifyService.class);

    @Autowired
    private IUserDao userDao;

    /**
     * 生成签名
     * @param mapData
     * @return
     */
    public String sign(Map<String,String > mapData){
        String sign = null;
        String sign_type = mapData.get(InvoiceParamKeys.SIGN_TYPE);
        String key = getMD5Key(mapData);

        if(SignType.MD5.equals(sign_type)) {
            sign = MD5Util.createSign(mapData, key);
        }
        return sign;
    }

    /**
     * 验签
     * @param mapData
     * @return
     */
    public boolean verify(Map<String,String> mapData){
        boolean result = false;
        String localsign = null;
        String key = getMD5Key(mapData);
        String sign_type = mapData.get(InvoiceParamKeys.SIGN_TYPE);
        String signature = mapData.get(InvoiceParamKeys.MERCHANT_SIGN);
        logger.debug("原签名"+signature);
        if(SignType.MD5.equals(sign_type)) {
            localsign = MD5Util.createSign(mapData, key);
            logger.debug("本地签名："+localsign);
        }
        if(localsign.equals(signature)){
            result = true;
        }
        return result;
    }

    /**
     * 获取MD5 key
     * @param mapData
     * @return
     */
    private String getMD5Key(Map<String,String> mapData){
        String key = "";
        String merchant_code = mapData.get(InvoiceParamKeys.MERCHANT_CODE);
        User user = userDao.getByMerchant_code(merchant_code);
        if(user!=null) {
            key = user.getKey();
            logger.debug("商户："+merchant_code+"的key："+key);
        }
        return key;
    }
}
