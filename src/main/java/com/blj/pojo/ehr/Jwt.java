package com.blj.pojo.ehr;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.bouncycastle.util.encoders.Base64;

//import com.google.gson.Gson;

public class Jwt {
    ObjectMapper mapper = new ObjectMapper();

    public Jwt(String id_token) throws Exception {
        String[] stringArray = id_token.split("\\.");
        if (stringArray.length != 3)
            throw new Exception("非法的id_token！");
        try {
            for (int i = 0; i < stringArray.length; i++) {
                stringArray[i] = SafeTools.SafeBase64ToBase64String(stringArray[i]);
            }
            //Gson gson = new Gson();
            //setHeader(gson.fromJson(new String(Base64.decode(stringArray[0])), Jwt_header.class));
            //setPayload(gson.fromJson(new String(Base64.decode(stringArray[1])), Jwt_payload.class));
            setHeader(mapper.readValue(new String(Base64.decode(stringArray[0])), Jwt_header.class));
            setPayload(mapper.readValue(new String(Base64.decode(stringArray[1])), Jwt_payload.class));
        } catch (Exception exp) {
            throw new Exception("非法的id_token！", exp);
        }
        setRaw_header(stringArray[0]);
        setRaw_payload(stringArray[1]);
        setRaw_signature(stringArray[2]);
    }

    private Jwt_header header;

    private Jwt_payload payload;

    private String raw_header;

    private String raw_payload;

    private String raw_signature;

    public void setHeader(Jwt_header header) {
        this.header = header;
    }

    public void setPayload(Jwt_payload payload) {
        this.payload = payload;
    }

    public void setRaw_header(String raw_header) {
        this.raw_header = raw_header;
    }

    public void setRaw_payload(String raw_payload) {
        this.raw_payload = raw_payload;
    }

    public void setRaw_signature(String raw_signature) {
        this.raw_signature = raw_signature;
    }

    public Jwt_header getHeader() {
        return header;
    }

    public Jwt_payload getPayload() {
        return payload;
    }

    public String getRaw_signature() {
        return raw_signature;
    }

    public String getRaw_payload() {
        return raw_payload;
    }

    public String getRaw_header() {
        return raw_header;
    }

    /*
     * 获取待验签的文本信息
     * */
    public String getSignContent() {
        return getRaw_header() + "." + getRaw_payload();
    }

    @Override
    public String toString() {
        //Gson gson = new Gson();
        //return gson.toJson(this);
        try {
            return mapper.writeValueAsString(this);
        } catch (Exception e) {
        }
        return "";
    }
}
