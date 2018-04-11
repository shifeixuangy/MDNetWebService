package commonUtil;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.Key;

/**
 * Created by liudongdong on 2015/4/11.
 */
public class DESEncrypt {
    private static final byte[]bKey="medoservicemedoserviceme".getBytes(StandardCharsets.UTF_8);
    /**
     * 给定一段文本，返回加密后的文本
     * @param content 待加密文本
     * @return  加密后的文本
     */
    public static String Encrypt(String content) throws Exception
    {
        byte[]bContent=content.getBytes(StandardCharsets.UTF_8);
        byte[]encryptBytes=des3EncodeECB(bKey,bContent);
        return new BASE64Encoder().encode(encryptBytes);
    }

    /**
     * 给定一段加密后的文本，返回解密后的文本
     * @param eContent
     * @return
     */
    public static String Decrypt(String eContent) throws Exception
    {
        byte[]bContent=new BASE64Decoder().decodeBuffer(eContent);
        byte[]sByte=ees3DecodeECB(bKey,bContent);
        return  new String(sByte,StandardCharsets.UTF_8);
    }
    /**
     * ECB加密,不要IV
     * @param key 密钥
     * @param data 明文
     * @return Base64编码的密文
     * @throws Exception
     */
    private static byte[] des3EncodeECB(byte[] key, byte[] data)
            throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede" + "/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, deskey);
        byte[] bOut = cipher.doFinal(data);
        return bOut;
    }
    /**
     * ECB解密,不要IV
     * @param key 密钥
     * @param data Base64编码的密文
     * @return 明文
     * @throws Exception
     */
    private static byte[] ees3DecodeECB(byte[] key, byte[] data)
            throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede" + "/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, deskey);
        byte[] bOut = cipher.doFinal(data);
        return bOut;
    }
}
