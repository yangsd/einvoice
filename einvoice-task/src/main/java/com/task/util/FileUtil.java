package com.task.util;

import com.task.service.EinvoiceXmlPdfService;
import org.apache.log4j.Logger;

import java.io.File;

/**
 * Created by sdyang on 2016/10/7.
 */
public class FileUtil {

    private static Logger logger = Logger.getLogger(FileUtil.class);

    public static boolean isExist(String filePath) {
        String paths[] = filePath.split("\\\\");
        String dir = paths[0];
        for (int i = 0; i < paths.length - 2; i++) {
            try {
                dir = dir + "/" + paths[i + 1];
                File dirFile = new File(dir);
                if (!dirFile.exists()) {
                    dirFile.mkdir();
                }
            } catch (Exception err) {
                logger.error(err.toString());
            }
        }
        File fp = new File(filePath);
        if(!fp.exists()){
            return true;
        }else{
            return false;
        }
    }
}
