package ib.ganz.u_care.manager;

import androidx.multidex.MultiDexApplication;

public class AppManager extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        SessionManager.init(this);
    }
}