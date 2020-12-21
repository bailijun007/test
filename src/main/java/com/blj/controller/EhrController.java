package com.blj.controller;

import com.blj.pojo.ehr.BeiSenSSORequest;
import com.blj.pojo.ehr.ResultBean;
import com.blj.service.BeiSenSSOService;
import com.blj.service.impl.BeiSenSSOServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author BaiLiJun on 2020/12/21 4:57 下午
 */
@RestController
@RequestMapping("/api/ehr")
public class EhrController {
   @Autowired
    private BeiSenSSOService beiSenSSOService;

    @GetMapping("/testGetSignInfo")
    public ResultBean<String> testGetSignInfo(){
        BeiSenSSORequest beiSenSSORequest=new BeiSenSSORequest();
        beiSenSSORequest.setUrlType("0");
        beiSenSSORequest.setSub("126554157");
//        beiSenSSORequest.setSub("128300875");


        String beiSenToken = beiSenSSOService.getBeiSenToken(beiSenSSORequest);
        System.out.println("beiSenToken = " + beiSenToken);
        return ResultBean.build(beiSenSSOService.getBeiSenToken(beiSenSSORequest));

    }

}
