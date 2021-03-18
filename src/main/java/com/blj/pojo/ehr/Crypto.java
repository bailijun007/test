package com.blj.pojo.ehr;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMReader;
import org.bouncycastle.util.encoders.Base64;
import sun.misc.BASE64Decoder;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.security.*;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class Crypto {
    private final static String encoding = "UTF-8";

    /**
     * 将摘要信息转换为相应的编码
     *
     * @param code    编码类型
     * @param message 摘要信息
     * @return 相应的编码字符串
     */
    private String Encode(String code, String message) throws Exception {
        MessageDigest md;
        String encode = null;
        try {
            md = MessageDigest.getInstance(code);
            encode = RC4.bytesToHex(md.digest(message.getBytes(encoding)));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encode;
    }

    /**
     * 将摘要信息转换成SHA-256编码
     *
     * @param message 摘要信息
     * @return SHA-256编码之后的字符串
     */
    public String sha256Encode(String message) throws Exception {
        return Encode("SHA-256", message);
    }

    /**
     * 将摘要信息转换成MD5编码
     *
     * @param message 摘要信息
     * @return MD5编码之后的字符串
     */
    public String md5Encode(String message) throws Exception {
        return Encode("MD5", message);
    }

    /**
     * 将摘要信息转换成SHA编码
     *
     * @param message 摘要信息
     * @return SHA编码之后的字符串
     */
    public String shaEncode(String message) throws Exception {
        return Encode("SHA", message);
    }

    /**
     * 将摘要信息转换成SHA-512编码
     *
     * @param message 摘要信息
     * @return SHA-512编码之后的字符串
     */
    public String sha512Encode(String message) throws Exception {
        return Encode("SHA-512", message);
    }

    public static final String SIGN_ALGORITHMS = "sha256withrsa";
    public static final String KEY_TYPE = "RSA";
    public static final String PEM_PUBLIC_HEAD = "-----BEGIN PUBLIC KEY-----";
    public static final String PEM_PUBLIC_FOOT = "-----END PUBLIC KEY-----";

    /**
     * 签名算法
     *
     * @param content    待验证文本
     * @param sign       签名信息
     * @param public_key 公钥字符串
     * @return 是否验签成功
     */
    public static final Boolean Verify(String content, String sign, String public_key) throws Exception {
        Boolean success = false;
        // pem公钥 去掉头和结尾
        String pubkey = public_key.replace(PEM_PUBLIC_HEAD, "").replace(PEM_PUBLIC_FOOT, "");
        //获取KeyFactory，指定RSA算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_TYPE);
        //将BASE64编码的公钥字符串进行解码
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] encodeByte = decoder.decodeBuffer(pubkey);
        //将BASE64解码后的字节数组，构造成X509EncodedKeySpec对象，生成公钥对象
        PublicKey publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodeByte));
        //获取Signature实例，指定签名算法(与之前一致)
        Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
        //加载公钥
        signature.initVerify(publicKey);
        //更新原数据
        signature.update(content.getBytes(encoding));
        //公钥验签（true-验签通过；false-验签失败）
        success = signature.verify(decoder.decodeBuffer(sign));
        return success;
    }

    /**
     * RSA签名
     *
     * @param data        待签名数据
     * @param private_key 商户私钥
     * @return 签名值
     */
    public static String Sign(String data, String private_key) throws Exception {
        // 密钥格式为PKCS#1 需要转换为PKCS#8才可以使用
        byte[] keyBytes = private_key.getBytes();
        Security.addProvider(new BouncyCastleProvider());
        ByteArrayInputStream stream = new ByteArrayInputStream(keyBytes);
        PEMReader reader = new PEMReader(new InputStreamReader(stream));
        KeyPair keyPair = (KeyPair) reader.readObject();
        reader.close();
        //获取KeyFactory，指定RSA算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_TYPE);
        KeySpec keySpec8 = new PKCS8EncodedKeySpec(keyPair.getPrivate().getEncoded());
        //将BASE64解码后的字节数组，构造成PKCS8EncodedKeySpec对象，生成私钥对象
        PrivateKey privatekey = keyFactory.generatePrivate(keySpec8);
        //获取Signature实例，指定签名算法（本例使用SHA1WithRSA）
        Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
        //加载私钥
        signature.initSign(privatekey);
        //更新待签名的数据
        signature.update(data.getBytes(encoding));
        //进行签名
        byte[] signed = signature.sign();
        //将加密后的字节数组，转换成BASE64编码的字符串，作为最终的签名数据
        return new String(Base64.encode(signed));
    }
}
