package com.blj.service;


import com.blj.pojo.ehr.BeiSenSSORequest;

public interface BeiSenSSOService {
    /**@GetMapping("/testGetSignInfo")
    public ResultBean<String> testGetSignInfo(){
    BeiSenSSOService beiSenSSOService=new BeiSenSSOServiceImpl();


    BeiSenSSORequest beiSenSSORequest=new BeiSenSSORequest();
    beiSenSSORequest.setUrlType("0");
    beiSenSSORequest.setSub("126554157");
    //        beiSenSSORequest.setSub("128300875");


    String beiSenToken = beiSenSSOService.getBeiSenToken(beiSenSSORequest);
    System.out.println("beiSenToken = " + beiSenToken);
    return ResultBean.build(beiSenSSOService.getBeiSenToken(beiSenSSORequest));

    }
     * 获取北森token
     * @param beiSenSSORequest
     * @return
     */
    String getBeiSenToken(BeiSenSSORequest beiSenSSORequest);

//    /**
//     * 获取北森跳转url
//     * @param type
//     * @return
//     */
//    String getJumpBeiSenUrl(String type);
//
//    /**
//     * 更新北森url
//     * @return
//     */
//    Boolean updateBeiSenUrlHashMap();
}
