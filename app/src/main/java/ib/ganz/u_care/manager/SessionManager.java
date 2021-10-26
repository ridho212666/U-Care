package ib.ganz.u_care.manager;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private static SharedPreferences sp;
    private static SharedPreferences.Editor editor;

    private static final String IS_LOGIN = "islogin";
    private static final String FIRE_TOKEN = "fire_base";
    private static final String JWT_TOKEN = "jwt_token";
    private static final String ORTU_DATA = "OrtuData";
    private static final String RAW_PASSWORD = "rawPassword";

    private static final String DUMMY_JWT = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

    public static void init(Context c){
        if (sp == null) {
            sp = c.getSharedPreferences("u_care", 0);
            editor = sp.edit();
        }
    }

    public static void setToken(String token) {
        editor.putString(FIRE_TOKEN, token).commit();
    }

    public static String getToken() {
        return sp.getString(FIRE_TOKEN, "");
    }

    public static String getJwtToken() {
        return sp.getString(JWT_TOKEN, DUMMY_JWT);
    }

    public static void setJwtToken(String s) {
        editor.putString(JWT_TOKEN, s).commit();
    }

    public static boolean isLogin(){
        return sp.getBoolean(IS_LOGIN, false);
    }

    public static String getRawPassword() {
        return  sp.getString(RAW_PASSWORD, "");
    }

    public static void setRawPassword(String p) {
        editor.putString(RAW_PASSWORD, p).commit();
    }
}
