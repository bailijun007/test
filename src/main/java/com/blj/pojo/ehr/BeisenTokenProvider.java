package com.blj.pojo.ehr;

import org.bouncycastle.util.encoders.Base64;

import java.util.HashMap;

public class BeisenTokenProvider {

    /**
     * 默认编码格式使用统一指定字符集 UTF-8
     */
    public final static String encoding = "utf-8";

    /**
     * 初始化header json
     *
     * @param alg
     * @param public_key
     * @return
     * @throws Exception
     */
    public static String GetHeader(String alg, String public_key) throws Exception {
        String kid = GetKid(public_key);
        Jwt_header header = new Jwt_header(alg, kid);
        return header.toString();
    }

    /**
     * 初始化 payload json
     *
     * @param iss
     * @param sub
     * @param aud
     * @param exp
     * @param iat
     * @param cls
     * @return
     * @throws Exception
     */
    public static String GetPayload(String iss, String sub, String aud, long exp, long iat, HashMap<String, Object> cls) throws Exception {
        Jwt_payload payload = new Jwt_payload(iss, sub, aud, exp, iat, cls);
        return payload.toString();
    }

    /**
     * 获取id_token
     *
     * @param header      jwt header json
     * @param payload     jwt payload json
     * @param private_key
     * @return url safe base64 编码的 jwt token
     * @throws Exception
     */
    public static String GetIdToken(String header, String payload, String private_key) throws Exception {
        String[] jwt = new String[3];
        jwt[0] = new String(Base64.encode(header.getBytes(encoding)));
        jwt[1] = new String(Base64.encode(payload.getBytes(encoding)));
        jwt[2] = Crypto.Sign(String.format("%s.%s", jwt[0], jwt[1]), private_key);
        // 转为 url safe base64
        for (int i = 0; i < 3; i++) {
            jwt[i] = SafeTools.Base64StringToSafeBase64(jwt[i]);
        }
        return String.format("%s.%s.%s", jwt[0], jwt[1], jwt[2]);
    }

    /**
     * 获取公钥hash  值用于对比公钥信息与服务器端是否一致
     *
     * @param public_key
     * @return
     * @throws Exception
     */
    public static String GetKid(String public_key) throws Exception {
        return new Crypto().sha256Encode(public_key.replaceAll("\r|\n", ""));
    }

    /**
     * 生成idtoken
     *
     * @param iss         Issuer Identifier：必须。提供认证信息者的唯一标识。一般是一个https的url（不包含querystring和fragment部分）。
     * @param sub         Subject Identifier：必须。iss提供的EU的标识，在iss范围内唯一。它会被RP用来标识唯一的用户。最长为255个ASCII个字符。
     * @param aud         Audience(s)：必须。标识ID Token的受众。必须包含OAuth2的client_id。
     * @param private_key
     * @param public_key
     * @return jwt 格式token
     * @throws Exception
     */
    public static String GenerateBeisenIDToken(String iss, String sub, String aud, String private_key, String public_key) throws Exception {
        String header = GetHeader("RS256", public_key);
        HashMap<String, Object> cls = new HashMap<String, Object>();// 用户自定义可见权限内容列表
        cls.put("appid", 0);
        long iat = SafeTools.getNowTimeStamp();// Issued At Time：必须。JWT的构建的时间【Unix时间】。
        long exp = iat + 60 * 60 * 24; // Expiration time：必须。过期时间，超过此时间的ID Token会作废不再被验证通过【Unix时间】。此处使用24小时后
        String payload = GetPayload(iss, sub, aud, exp, iat, cls);
        return GetIdToken(header, payload, private_key);
    }

    /**
     * 验签token
     *
     * @param id_token   获得的 id_token
     * @param public_key
     * @return 当返回的jwt 不为空 且验证方法未见异常信息时 表示验证通过 jwt内容可信任
     * @throws Exception
     */
    public static Jwt VerifySign(String id_token, String public_key) throws Exception {
        // 实例化idtoken
        Jwt jwt = new Jwt(id_token);
        // 签名方式是否是RSA-SHA256
        if (!"RS256".equals(jwt.getHeader().getAlg())) {
            throw new Exception("错误的签名方式！");
        }
        // 验证是否过期
        if (SafeTools.ToLong(jwt.getPayload().getExp(), 0L) < SafeTools.getNowTimeStamp()) {
            throw new Exception("IDToken 已过期，请重新获取");
        }
        String kid = GetKid(public_key);
        if (!kid.equals(jwt.getHeader().getKid().toLowerCase())) {
            throw new Exception("公钥校验错误！");
        }
        // 验证签名
        if (!Crypto.Verify(jwt.getSignContent(), jwt.getRaw_signature(), public_key)) {
            throw new Exception("签名验证错误！");
        }
        // TODO: 业务内逻辑添加

        return jwt;
    }
}
