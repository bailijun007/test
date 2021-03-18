package com.blj.service.impl;


import com.blj.pojo.ehr.BeiSenSSORequest;
import com.blj.pojo.ehr.BeisenTokenProvider;
import com.blj.pojo.ehr.Openid;
import com.blj.pojo.ehr.SafeTools;
import com.blj.service.BeiSenSSOService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author:hulukun
 * @date:2020-02-11 16:21
 * @description:
 */
@Service
public class BeiSenSSOServiceImpl implements BeiSenSSOService {


    /** 北森token获取参数 start----**/
    @Value("${ehr.beisen.alg}")
    private String alg;
    @Value("${ehr.beisen.iss}")
    private String iss;
    @Value("${ehr.beisen.aud}")
    private String aud;
    @Value("${ehr.beisen.app_id}")
    private String appId;
    @Value("${ehr.beisen.public_key}")
    private String publicKey;
    @Value("${ehr.beisen.private_key}")
    private String privateKey;
    /** 北森token获取参数 end----**/

//    private final static String BEI_SEN_URL_HASH_MAP = "beiSenUrlHashMap";
//    /**
//     * url地址
//     * updateBeiSenUrlHashMap更新
//     */
//    public static HashMap<Integer,String> urlHashMap = new HashMap<Integer,String>(){
//        {
//            put("员工离职申请","https://cloud.italent.cn/tenantbase/#/indexPage?viewName=TenantBase.MyTransitionEmployeeNavView&metaObjName=TenantBase.EmploymentRecord&r_from=MyTransition&shadow_context={appModel:\\\"italent\\\",uppid:\\\"1\\\"}");
//            put("请假申请","https://cloud.italent.cn/Attendance/#/indexPage?metaObjName=Attendance.AttendanceVacationInfo&app=Attendance&navViewName=Attendance.EmployeeVacationNavView");
//            put("出差申请","https://cloud.italent.cn/Attendance/#indexPage?metaObjName=Attendance.Business&app=Attendance&navViewName=Attendance.EmployeeBusinessNavView");
//            put("销假申请","https://cloud.italent.cn/Attendance/#/indexPage?metaObjName=Attendance.AttendanceVacationInfo&app=Attendance&navViewName=Attendance.EmployeeVacationNavView");
//            put("我的工资单","https://cloud.italent.cn/Compensation/#/basePage?pageType=LoginVerificationPage");
//            put("我的工作","https://cloud.italent.cn/ApprovalCentre/#/IndexPage?navViewName=ApprovalCentre.MyApply&metaObjName=ApprovalCentre.ApprovalObject&app=ApprovalCentre");
//            put("我的休假","https://cloud.italent.cn/Attendance/#indexPage?metaObjName=Attendance.AttendanceVacationInfo&app=Attendance&navViewName=Attendance.EmployeeVacationNavView");
//            put("我的出差","https://cloud.italent.cn/Attendance/#indexPage?metaObjName=Attendance.Business&app=Attendance&navViewName=Attendance.EmployeeBusinessNavView");
//            put("我的公出","https://cloud.italent.cn/Attendance/#indexPage?metaObjName=Attendance.Outward&navViewName=Attendance.EmployeeOutwardNavView&app=Attendance");
//            put("我的审批","https://cloud.italent.cn/ApprovalCentre/#/IndexPage?metaObjName=ApprovalCentre.ApprovalTask&ApprovalPageVersion=2.0");
//            put("全部待办","http://www.italent.cn/100000/ItalentIframeHome#more/messages?messageType=1&tabAppId=-1&tabAppType=-1&isRead=1&messageSubNav=1");
//            put("审批列表","https://cloud.italent.cn/ApprovalCentre/#/IndexPage?navViewName=ApprovalCentre.ApprovalForTita&metaObjName=ApprovalCentre.ApprovalObject&app=ApprovalCentre");
//            put("我的异动","https://cloud.italent.cn/tenantbase/#/indexPage?viewName=TenantBase.MyTransitionEmployeeNavView&metaObjName=TenantBase.EmploymentRecord");
//            put("合同协议","https://cloud.italent.cn/#indexPage?viewName=TenantBase.CurrentEmploymentContractNavView&metaObjName=TenantBase.EmploymentContract");
//        }
//    };

    /**
     * 默认值初始化
     * @return
     */
    private Openid generateOpenId(BeiSenSSORequest beiSenSSORequest) {
        String exp = "", iat = "";
        long l_iat = SafeTools.getNowTimeStamp();
        long l_exp = l_iat + 60 * 60 * 24;
        //过期时间
        exp = SafeTools.TimeStamp2Date(l_exp, "yyyy-MM-dd HH:mm:ss");
        //JWT的构建的时间
        iat = SafeTools.TimeStamp2Date(l_iat, "yyyy-MM-dd HH:mm:ss");

        //cls
        //键值对格式list
        //appid=100 （必填）
        //uty=email （必填，sub使用北森用户id时此处值须为id，使用邮箱地址时值为email）
        //url_type=0 （必填）【 0=pc端首页 2=移动端首页】
        //isv_type=0 【0=租户 1=应用

        //"appid=100,uty=email,url_type=0,isv_type=0"
        String cls = "appid=" + appId +
                ",uty=id,url_type=" + beiSenSSORequest.getUrlType() +
                ",isv_type=0";

        Openid openid = new Openid(
                alg,
                iss,
                beiSenSSORequest.getSub(),
                aud,
                cls,
                privateKey,
                publicKey,
                exp,//过期时间
                iat//JWT的构建的时间
        );
        return openid;
    }

    @Override
    public String getBeiSenToken(BeiSenSSORequest beiSenSSORequest) {
        Openid openid = this.generateOpenId(beiSenSSORequest);
        try {
            String header = BeisenTokenProvider.GetHeader(openid.getAlg(), openid.getPublic_key());
            // 用户自定义claims
            HashMap<String, Object> cls = new HashMap<String, Object>();
            String[] cls_list = openid.getStr_cls().split(",");
            for (int i = 0; i < cls_list.length; i++) {
                String item = cls_list[i];
                String[] kv = (SafeTools.StringIsNullOrEmpty(item) ? "" : item).split("=");
                if (!SafeTools.StringIsNullOrEmpty(item) && kv.length == 2)
                    cls.put(kv[0], kv[1]);
            }
            String fm = "yyyy-MM-dd HH:mm:ss";
            // 构建header
            String payload = BeisenTokenProvider.GetPayload(openid.getIss(), openid.getSub(), openid.getAud(), SafeTools.Date2TimeStamp(openid.getExp(), fm), SafeTools.Date2TimeStamp(openid.getIat(), fm), cls);
            String id_token = BeisenTokenProvider.GetIdToken(header, payload, openid.getPrivate_key());
            openid.setId_token(id_token);
        } catch (Exception e) {
           e.printStackTrace();
        }

        return openid.getId_token();
    }

//    @Override
//    public String getJumpBeiSenUrl(String type) {
//        String url = BeiSenSSOServiceImpl.urlHashMap.get(type);
//        if(null == url || url.equals("")){
//            throw new BusinessException(ExceptionEnum.SYSTEM_ERROR,"北森跳转URL获取失败");
//        }
//        return url;
//    }
//
//    @Override
//    public Boolean updateBeiSenUrlHashMap() {
//        String portalMenuHashMapApollo = config.getProperty(BEI_SEN_URL_HASH_MAP, "");
//        if (null != portalMenuHashMapApollo) {
//            BeiSenSSOServiceImpl.urlHashMap = JSONObject.parseObject(portalMenuHashMapApollo,HashMap.class);
//        }
//        return true;
//    }

}
