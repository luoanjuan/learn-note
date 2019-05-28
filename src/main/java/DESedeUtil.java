import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.security.Key;
import java.util.Base64;

/**
 * Created by wb-zj373670 on 2019/3/1.
 */
public class DESedeUtil {

    public static String getKeyString(){
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");//Key的生成器
            keyGenerator.init(168);//指定keySize
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] bytesKey = secretKey.getEncoded();
            return  new String(Base64.getEncoder().encode(bytesKey));
        }catch (Exception e){
            //
        }

        return null;
    }

    /**
     * 加密
     * @param content
     * @param key
     * @return
     */
    public static String desEncode(String content, String key){
        try {
            DESedeKeySpec sedeKeySpec = new DESedeKeySpec(Base64.getDecoder().decode(key));
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DESede");
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeyFactory.generateSecret(sedeKeySpec));
            return Base64.getEncoder().encodeToString(cipher.doFinal(content.getBytes()));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String desDecode(String content, String key){
        try {
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, getKey(key));
            return new String(cipher.doFinal(Base64.getDecoder().decode(content.getBytes())));

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static Key getKey(String key){
        try {
            DESedeKeySpec sedeKeySpec = new DESedeKeySpec(Base64.getDecoder().decode(key));
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DESede");
            return  secretKeyFactory.generateSecret(sedeKeySpec);
        }catch (Exception e){
            //
        }
        return null;
    }
}

