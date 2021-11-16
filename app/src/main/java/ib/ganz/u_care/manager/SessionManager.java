package ib.ganz.u_care.manager;

import android.content.Context;
import android.content.SharedPreferences;

import ib.ganz.u_care.dataclass.NakesData;
import ib.ganz.u_care.dataclass.OrtuData;
import ib.ganz.u_care.helper.Gzon;

public class SessionManager {

    private static SharedPreferences sp;
    private static SharedPreferences.Editor editor;

    private static final String IS_LOGIN = "islogin";
    private static final String IS_ORTU = "isortu";
    private static final String FIRE_TOKEN = "fire_base";
    private static final String ORTU_DATA = "OrtuData";
    private static final String NAKES_DATA = "NakesData";
    private static final String RAW_PASSWORD = "rawPassword";

    public static void init(Context c) {
        if (sp == null) {
            sp = c.getSharedPreferences("u_care", 0);
            editor = sp.edit();
        }
    }

    public static void loginOrtu(OrtuData o) {
        editor.putString(ORTU_DATA, Gzon.toJsonObject(o)).commit();
        editor.putBoolean(IS_LOGIN, true).commit();
        editor.putBoolean(IS_ORTU, true).commit();
    }

    public static void loginNakes(NakesData o) {
        editor.putString(NAKES_DATA, Gzon.toJsonObject(o)).commit();
        editor.putBoolean(IS_LOGIN, true).commit();
        editor.putBoolean(IS_ORTU, false).commit();
    }

    public static void logout() {
        editor.putString(ORTU_DATA, "").commit();
        editor.putString(NAKES_DATA, "").commit();
        editor.putBoolean(IS_LOGIN, false).commit();
        editor.putBoolean(IS_ORTU, false).commit();
    }

    public static OrtuData getOrtuData() {
        return Gzon.fromJsonObject(sp.getString(ORTU_DATA, ""), OrtuData.class);
    }

    public static NakesData getNakesData() {
        return Gzon.fromJsonObject(sp.getString(NAKES_DATA, ""), NakesData.class);
    }

    public static String getId() {
        return isLogin() && isOrtu() ? getOrtuData().getId() : getNakesData().getId();
    }

    public static void setToken(String token) {
        editor.putString(FIRE_TOKEN, token).commit();
    }

    public static String getToken() {
        return sp.getString(FIRE_TOKEN, "");
    }

    public static boolean isLogin() {
        return sp.getBoolean(IS_LOGIN, false);
    }

    public static boolean isOrtu() {
        return sp.getBoolean(IS_ORTU, false);
    }

    public static String getRawPassword() {
        return sp.getString(RAW_PASSWORD, "");
    }

    public static void setRawPassword(String p) {
        editor.putString(RAW_PASSWORD, p).commit();
    }
}
