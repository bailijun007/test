package com.blj.pojo.ehr;



public class Openid {
    public Openid(String alg, String iss, String sub, String aud, String str_cls, String private_key, String public_key, String exp, String iat) {
        this.alg = alg;
        this.iss = iss;
        this.sub = sub;
        this.aud = aud;
        this.str_cls = str_cls;
        this.private_key = private_key;
        this.public_key = public_key;
        this.exp = exp;
        this.iat = iat;

    }

    private Openid() {
    }

    public boolean IsEmpty() {
        if (SafeTools.StringIsNullOrEmpty(this.alg) ||
                SafeTools.StringIsNullOrEmpty(this.public_key) ||
                SafeTools.StringIsNullOrEmpty(this.private_key) ||
                SafeTools.StringIsNullOrEmpty(this.iss) ||
                SafeTools.StringIsNullOrEmpty(this.sub) ||
                SafeTools.StringIsNullOrEmpty(this.aud) ||
                SafeTools.StringIsNullOrEmpty(this.exp) ||
                SafeTools.StringIsNullOrEmpty(this.iat) ||
                SafeTools.StringIsNullOrEmpty(this.str_cls)) {
            return true;
        }
        return false;
    }

    private String alg;
    private String iss;
    private String sub;
    private String aud;
    private String str_cls;
    private String private_key;
    private String public_key;
    private String exp;
    private String iat;

    public String getId_token() {
        return id_token;
    }

    public void setId_token(String id_token) {
        this.id_token = id_token;
    }

    private String id_token;


    public String getAlg() {
        return alg;
    }

    public void setAlg(String alg) {
        this.alg = alg;
    }

    public String getIss() {
        return iss;
    }

    public void setIss(String iss) {
        this.iss = iss;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getAud() {
        return aud;
    }

    public void setAud(String aud) {
        this.aud = aud;
    }

    public String getStr_cls() {
        return str_cls;
    }

    public void setStr_cls(String str_cls) {
        this.str_cls = str_cls;
    }

    public String getPrivate_key() {
        return private_key;
    }

    public void setPrivate_key(String private_key) {
        this.private_key = private_key;
    }

    public String getPublic_key() {
        return public_key;
    }

    public void setPublic_key(String public_key) {
        this.public_key = public_key;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getIat() {
        return iat;
    }

    public void setIat(String iat) {
        this.iat = iat;
    }
}
