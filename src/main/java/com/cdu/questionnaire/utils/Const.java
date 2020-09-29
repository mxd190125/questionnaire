package com;

/**
 * @ClassName Const
 * @Version 1.0
 * @Author dell
 * @Date 2020/4/6 23:54
 * @Description TODO
 * Modification User:
 * Modification Date:
 */

public class Const {

    public static final String APP_ID="wx72b3ad70520300f9";
    public static final String APP_SECRET="ea439103d00b32b1bc9d0b198e45bb14";

    /**
     * GET
     */
    public static final String JS_CODE_2_SESSION_URL="https://api.weixin.qq.com/sns/jscode2session" +
            "?appid=APP_ID" +
            "&secret=APP_SECRET" +
            "&js_code=CODE" +
            "&grant_type=authorization_code";

    /**
     * POST: access_token touser template_id page data miniprogram_state lang
     */
    public static final String SUB_SCRIBE_URL="https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=ACCESS_TOKEN";
    /**
     * GET: 获取access_token {"access_token":"ACCESS_TOKEN","expires_in":7200} or {"errcode":40013,"errmsg":"invalid appid"}
     */
    public static final String TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token" +
            "?grant_type=client_credential" +
            "&appid=APP_ID" +
            "&secret=APP_SECRET";


//
//
//    public static final String ACCESS_TOKEN_URL="https://api.weixin.qq.com/sns/oauth2/access_token" +
//            "?appid=APP_ID" +
//            "&secret=APP_SECRET" +
//            "&code=CODE" +
//            "&grant_type=authorization_code";
//    public static final String USER_INFO_URL="https://api.weixin.qq.com/sns/userinfo" +
//            "?access_token=ACCESS_TOKEN" +
//            "&openid=OPEN_ID" +
//            "&lang=zh_CN";

}
