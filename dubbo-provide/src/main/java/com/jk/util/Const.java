package com.jk.util;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Company 
 * @Author shangfeng
 * @Date 2013-11-7 下午07:10:45
 * @Description 常量
 */
public class Const {

    


    public static final String shiro_cache_prefix = "shiro-cache-";
    public static final String shiro_cache_prefix_keys = "shiro-cache-*";
    public static final String github_client_id = "**";
    public static final String github_client_secret = "**";
    public static final String github_oauth_url = "**";

    //阿里大于短信发送url
    public static final String aLiDaYuRegisterUrl = "aLiDaYuRegisterUrl";
    //阿里大于短信注册key
    public static final String aLiDaYuRegisterAppKey = "aLiDaYuRegisterAppKey";
    //阿里大于短信注册密钥
    public static final String aLiDaYuRegisterSecret = "aLiDaYuRegisterSecret";
    //阿里大于短信注册模板签名
    public static final String aLiDaYuRegisterTemplateSign = "aLiDaYuRegisterTemplateSign";
    //阿里大于短信模板编码
    public static final String aLiDaYuRegisterTemplateCode = "aLiDaYuRegisterTemplateCode";


    public static final List<String> headTitil = new ArrayList<String>() {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;

        {
            add("权限字");
            add("权限名称");
            add("描述");
        }

    };
    @SuppressWarnings("serial")
    public static final List<Map<String, String>> NFTEMP = new ArrayList<Map<String, String>>() {
        {
            add(new HashMap<String, String>() {
                {
                    put("key", "2017");
                    put("value", "2017");
                }
            });
        }

    };
    /** 扫描保存时解析的数据对应的实体对象 */
    public static final Map<String, String> TSCANDATA = new HashMap<String, String>();
    static {
        TSCANDATA.put("yszj", "bh");
        TSCANDATA.put("wtsqbh", "cWtsqBh");
        TSCANDATA.put("cxh", "cxh");
        TSCANDATA.put("sqdw", "sqdw");
        TSCANDATA.put("sqr", "sqr");
        TSCANDATA.put("sqsj", "sqsj");
        TSCANDATA.put("cxdx", "dxlx");
        TSCANDATA.put("xm", "xm");
        TSCANDATA.put("xgxm", "xgxm");
        TSCANDATA.put("zjl", "zjlx");
        TSCANDATA.put("zjh", "zjhm");
        TSCANDATA.put("dz", "address");
        TSCANDATA.put("dh", "phone");
        TSCANDATA.put("ssdw", "dwmc");
        TSCANDATA.put("cxlb", "Ccxlb");
    };
    /** （条件类别）一键查询默认 */
    public static final String YJCXTJ_MR = "1";
}
