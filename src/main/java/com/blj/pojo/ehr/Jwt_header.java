package com.blj.pojo.ehr;

// import com.google.gson.Gson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Auto-generated: 2018-04-02 17:10:58
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Jwt_header {
    @JsonIgnore
    ObjectMapper mapper = new ObjectMapper();

    private Jwt_header() {

    }

    @JsonCreator
    public Jwt_header(@JsonProperty("alg") String alg, @JsonProperty("kid") String kid) {
        setAlg(alg);
        setKid(kid);
    }
//    public Jwt_header(String alg, String kid) {
//        setAlg(alg);
//        setKid(kid);
//    }

    private String alg;
    private String kid;

    public void setAlg(String alg) {
        this.alg = alg;
    }

    public String getAlg() {
        return alg;
    }

    public void setKid(String kid) {
        this.kid = kid;
    }

    public String getKid() {
        return kid;
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