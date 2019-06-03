package com.shenkangyun.healthcenter.BaseFolder;

import com.shenkangyun.healthcenter.DBFolder.User;
import com.shenkangyun.healthcenter.DBFolder.UserEntry;

import org.litepal.LitePal;

import java.util.List;

import cn.jpush.im.android.api.JMessageClient;

/**
 * Created by Administrator on 2018/7/5.
 */

public class Base {

    //
//    public static final String URL = "http://192.168.0.210:8080/skyapp_cfyou/api/app_patient/";
//    public static final String saveFieldURL = "http://192.168.0.210:8080/skyapp_cfyou/form/saveFieldRecordJson.json";
//    public static final String saveFieldURL = "http://192.168.0.118:8080/skyapp_cfyou/form/saveFieldRecordJson.json";
//    public static final String URL = "http://192.168.0.118:8080/skyapp_cfyou/api/app_patient/";
    public static final String saveFieldURL = "http://111.17.215.37:806/skyapp_cfyou/form/saveFieldRecordJson.json";
    public static final String URL = "http://111.17.215.37:806/skyapp_cfyou/api/app_patient/";
    //    public static final String URL = "http://192.168.0.214:8080/skyapp_cfyou/api/app_patient/";
    public static final String AppKey = "8742c996f084b5c190cf43c5";

    public static final String appKey = "dc4d8d31d749ecb86157449d2fb804e0";
    public static final String timeSpan = "20101020";

    public static final String TARGET_ID = "targetId";
    public static final String TARGET_APP_KEY = "targetAppKey";
    public static final String GROUP_ID = "groupId";
    public static final String CONV_TITLE = "conv_title";

    public static int getUserID() {
        int userID = 0;
        List<User> all = LitePal.findAll(User.class);
        for (int i = 0; i < all.size(); i++) {
            userID = all.get(i).getUserID();
        }
        return userID;
    }

    public static UserEntry getUserEntry() {
        return UserEntry.getUser(JMessageClient.getMyInfo().getUserName(), JMessageClient.getMyInfo().getAppKey());
    }
}