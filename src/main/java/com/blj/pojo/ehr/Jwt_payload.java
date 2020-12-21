package com.blj.pojo.ehr;

//import com.google.gson.Gson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;

public class Jwt_payload {
    @JsonIgnore
    ObjectMapper mapper = new ObjectMapper();

    private Jwt_payload() {

    }

    @JsonCreator
    public Jwt_payload(@JsonProperty("iss") String iss,
                       @JsonProperty("sub") String sub,
                       @JsonProperty("aud") String aud,
                       @JsonProperty("exp") Long exp,
                       @JsonProperty("iat") Long iat,
                       @JsonProperty("cls") HashMap<String, Object> cls) {
        setIss(iss);
        setSub(sub);
        setAud(aud);
        setExp(String.valueOf(exp));
        setIat(String.valueOf(iat));
        setCls(cls);
    }

    private String iss;
    private String sub;
    private String aud;
    private String exp;
    private String iat;
    private HashMap<String, Object> cls;


    public void setIss(String iss) {
        this.iss = iss;
    }

    public String getIss() {
        return iss;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getSub() {
        return sub;
    }

    public void setAud(String aud) {
        this.aud = aud;
    }

    public String getAud() {
        return aud;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getExp() {
        return exp;
    }

    public void setIat(String iat) {
        this.iat = iat;
    }

    public String getIat() {
        return iat;
    }

    public HashMap<String, Object> getCls() {
        return cls;
    }

    public void setCls(HashMap<String, Object> cls) {
        this.cls = cls;
    }

    @Override
    public String toString() {
//        Gson gson = new Gson();
//        return gson.toJson(this);
        try {
            return mapper.writeValueAsString(this);
        } catch (Exception e) {
        }
        return "";
    }
}
