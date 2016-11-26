import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

/**
 * Created by sdyang on 2016/11/5.
 */
public class ReadTest {
    //certmgr.msc

    public static void main(String[] args) throws FileNotFoundException, CertificateException {
        CertificateFactory certificatefactory= CertificateFactory.getInstance("X.509");
        FileInputStream bais=new FileInputStream("c:/test.cer");
        X509Certificate Cert = (X509Certificate)certificatefactory.generateCertificate(bais);
        PublicKey pk = Cert.getPublicKey();
        BASE64Encoder bse=new BASE64Encoder();
        System.out.println("公钥:"+bse.encode(pk.getEncoded()));
    }

    public static byte[] sign(X509Certificate certificate,
                              PrivateKey privateKey, byte[] plainText) {
                 /** 如果要从密钥库获取签名算法的名称，只能将其强制转换成X509标准，JDK 6只支持X.509类型的证书 */
                 try {
                         Signature signature = Signature.getInstance(certificate
                                         .getSigAlgName());
                         signature.initSign(privateKey);
                         signature.update(plainText);
                         return signature.sign();
                     } catch (NoSuchAlgorithmException | InvalidKeyException
                         | SignatureException e) {
                         // TODO Auto-generated catch block
                         e.printStackTrace();
                     }

                 return null;
             }

}
