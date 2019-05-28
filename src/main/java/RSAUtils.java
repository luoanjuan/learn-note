import javafx.util.Pair;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.UUID;


/**
 * <p>
 * RSA公钥/私钥/签名工具包
 * </p>
 * <p>
 * 罗纳德·李维斯特（Ron [R]ivest）、阿迪·萨莫尔（Adi [S]hamir）和伦纳德·阿德曼（Leonard [A]dleman）
 * </p>
 * <p>
 * 字符串格式的密钥在未在特殊说明情况下都为BASE64编码格式<br/>
 * 由于非对称加密速度极其缓慢，一般文件不使用它来加密而是使用对称加密，<br/>
 * 非对称加密算法可以用来对对称加密的密钥加密，这样保证密钥的安全也就保证了数据的安全
 * </p>
 *
 * @author IceWee
 * @version 1.0
 * @date 2012-4-26
 */
public class RSAUtils {

     /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;


    /**
     * 加密算法RSA
     */
    private static final String KEY_ALGORITHM = "RSA";

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAN7gkRI8J2Ow22g4FJZuKr73o2cmpkQOga+c+PSNYXpxgfnPj7ZjQ0N9FUhjd3R+iNxKcLQIUaSpHiwW2dAJzMUCAwEAAQ==";

    String privateKey = "MIIBUwIBADANBgkqhkiG9w0BAQEFAASCAT0wggE5AgEAAkEA3uCREjwnY7DbaDgUlm4qvvejZyamRA6Br5z49I1henGB+c+PtmNDQ30VSGN3dH6I3EpwtAhRpKkeLBbZ0AnMxQIDAQABAkB5mhLs3Q8ssDj1outwrAj8ioaH6Cl9JmERnNakL2NI9xJrK21/jIO59atbQzTMyFxLewgMCE4jWGZ4JoPnUSHhAiEA+WAzr8MKg6HjZaufyoyj4D+qsF7TWtxWn5cWwXmqKj0CIQDkzCuArhuP4a5/B2ZwB3C/n5XwaHLtffe20MKjWKK9KQIgMA8AJwE5h4CtaCyZENnlwxPB+1dq9/m6n+roMcUWrCkCIFsg8wbK0hGJyw2vbd6bKA46yua5Q2VScfKvzYZwDEmZAiAUrWxLdZ0js2O4wyORIpEkcBNLloF24F507OFvG826ow==";


    /**
     * <p>
     * 生成密钥对(公钥和私钥)
     * </p>
     *
     * @return Pair 私钥在前，公钥在后
     * @throws Exception
     */
    private static Pair<String, String> genKeyPair() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(512);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Pair<String, String> pair = new Pair(Base64.getEncoder().encodeToString(privateKey.getEncoded()),Base64.getEncoder().encodeToString(publicKey.getEncoded()));
        return pair;
    }

    /**
     * <p>
     * 公钥解密
     * </p>
     *
     * @param encrypted 已加密数据
     * @param publicKey     公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static String decryptByPublicKey(String encrypted, String publicKey)
            throws Exception {
        byte[] encryptedData = Base64.getDecoder().decode(encrypted);
        byte[] keyBytes = Base64.getDecoder().decode(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return new String(decryptedData);
    }


    /**
     * <p>
     * 私钥加密
     * </p>
     *
     * @param datastr       源数据
     * @param privateKey 私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static String encryptByPrivateKey(String datastr, String privateKey)
            throws Exception {
        byte[] data = datastr.getBytes("UTF-8");
        byte[] keyBytes = Base64.getDecoder().decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    public static void main(String[] args) throws Exception {
        //生成秘钥对
        Pair<String, String> keyPair = genKeyPair();
        //提取公钥

        System.out.println("生成公钥：" + keyPair.getValue());
        //提取私钥

        System.out.println("生成私钥： " + keyPair.getKey());
        //加密数据
        String text = "20190312000002_"+ UUID.randomUUID()+"_1";;

        //用私钥加密明文
        String encodeText = new String(encryptByPrivateKey(text, keyPair.getKey()));
        System.out.println("用私钥生成密文： " + encodeText);
        //用公钥解密
        String decodedText = new String(decryptByPublicKey(encodeText, keyPair.getValue()));
        System.out.println("用公钥得到明文：" + decodedText);

    }

}