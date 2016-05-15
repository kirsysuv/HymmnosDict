package com.binasphere.hymmnosdict;

import android.app.Application;
import android.content.Context;

import com.binasphere.hymmnosdict.common.TextViewUtils;
import com.binasphere.hymmnosdict.dao.HymmnosDaoRx;
import com.binasphere.hymmnosdict.dao.HymmnosLogicRx;

/**
 * Created by Kerstin on 2015/12/6.
 */
public class App extends Application {
    public static HymmnosLogicRx DAO;
    public static TextViewUtils TVU;
    public static Context CONTEXT;


    @Override
    public void onCreate() {
        super.onCreate();
        DAO = new HymmnosDaoRx(this);
        TVU = new TextViewUtils(this);
        CONTEXT = this;
    }

}
