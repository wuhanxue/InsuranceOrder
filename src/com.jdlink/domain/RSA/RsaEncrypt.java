package com.jdlink.domain.RSA;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * RSA加密
 */
public class RsaEncrypt {

    /**
     * 私钥公钥
     */
    private static final Map<Integer, String> keyMap = new HashMap<Integer, String>(){{
        // 私钥
        put(0,"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCM2CkktxwDAveAgKTHqUk9HQCUkm9IcMmzuN9sYRNHIKZGpVoMmZ+YLoad6PAa2j5oDHPVykiALKw2q8SDT1/gw8+wIk6iysnA/zUY1jO86ByHXY9e6SIJWERqagEslHM/MeTQnysugKcFH/tROplJ1eHryAY0QCMdOfOF1JTrJwIDAQAB");  // 私钥
        // 公钥
        put(1,"MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIzYKSS3HAMC94CApMepST0dAJSSb0hwybO432xhE0cgpkalWgyZn5guhp3o8BraPmgMc9XKSIAsrDarxINPX+DDz7AiTqLKycD/NRjWM7zoHIddj17pIglYRGpqASyUcz8x5NCfKy6ApwUf+1E6mUnV4evIBjRAIx0584XUlOsnAgMBAAECgYB6mCjv6Wa7hFiVBOTpWgQC6vy3VeMBhhM7mZ74QmM6VUQGWCVStGGDYHQSfVCJvw/1F2m4XJPQ5ZGdINQzi+KigonMYC7oByvesbj8LVMflM+ITmMFbui9znSMW85NHyTuuRpIa/uTXVonOfF56/YfMsf9A3JbEBMUaQiyFGM4sQJBAMIOMq7tFa5h+GIvaVSu2Fr4LfZdugaJDAJ8z9aIsWGWEfst0ZtNG9M+3F77stRdUhMLIxDmCv4z66MAuZIMuG8CQQC5zapfXV4keq1qU4ZhICrHh99B9UfyDEVA+2/6NElll6920kZBg8rqRWGruCBEOfO+u1/Kktd3sjjKt8ejH6TJAkEAmLL3avKgpvHss8Y/CiXzldHIyR6DrXTWAHcaTOSPQA0I5S/uylfo11eVrSA+XiF43gdbdoaui81rVH7Th3t+/QJAQOdb5VYrJZkI7MRig5kF+I846MRyrlWk928djYwoO2nCLEKFsisEfdNgq+h0tQB9aZnwe0XSKIjEF+Qcw5mxKQJAAVsRt+Yo7i/2KM3BgV9HMte6+fvnstNWs4XHLj/WNWm6o+Gd4gx5awcZ1FVUYup8wyXOy2fsEO6dcpmjfRbCJw==");
    }};  //用于封装随机产生的公钥与私钥


    public static Map<Integer, String> getKeyMap() {
        return keyMap;
    }

//    public static void main(String[] args) throws Exception {
//        //生成公钥和私钥
//        genKeyPair();
//        //加密字符串
//        String message = "df723820";
//        System.out.println("随机生成的公钥为:" + keyMap.get(0));
//        System.out.println("随机生成的私钥为:" + keyMap.get(1));
//        String messageEn = encrypt(message,keyMap.get(0));
//        System.out.println(message + "\t加密后的字符串为:" + messageEn);
//        String messageDe = decrypt(messageEn,keyMap.get(1));
//        System.out.println("还原后的字符串为:" + messageDe);
//    }

    /**
     * 随机生成密钥对（暂不用，已固定）
     * @throws NoSuchAlgorithmException
     */
    public static void genKeyPair() throws NoSuchAlgorithmException {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(1024,new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();   // 得到私钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  // 得到公钥
        String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
        // 得到私钥字符串
        String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));
        // 将公钥和私钥保存到Map
        keyMap.put(0,publicKeyString);  //0表示公钥
        keyMap.put(1,privateKeyString);  //1表示私钥
    }
    /**
     * RSA公钥加密
     *
     * @param str
     *            加密字符串
     * @param publicKey
     *            公钥
     * @return 密文
     * @throws Exception
     *             加密过程中的异常信息
     */
    public static String encrypt( String str, String publicKey ) throws Exception{
        //base64编码的公钥
        byte[] decoded = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        String outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));
        return outStr;
    }

    /**
     * RSA私钥解密
     *
     * @param str
     *            加密字符串
     * @param privateKey
     *            私钥
     * @return 铭文
     * @throws Exception
     *             解密过程中的异常信息
     */
    public static String decrypt(String str, String privateKey) throws Exception{
        //64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
        //base64编码的私钥
        byte[] decoded = Base64.decodeBase64(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        String outStr = new String(cipher.doFinal(inputByte));
        return outStr;
    }
}
