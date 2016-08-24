package com.whl.emoguoshi.app;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.whl.emoguoshi.BuildConfig;
import com.whl.emoguoshi.db.DaoMaster;
import com.whl.emoguoshi.db.DaoSession;
import com.whl.emoguoshi.db.GuoShiDevOpenHelper;

import org.greenrobot.greendao.query.QueryBuilder;


/**
 * Created by wanghl on 16/2/25.
 */
public class App extends Application {
    private static final String TAG = "DicApp";
    public static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
//        LeakCanary.install(this);

        GuoShiDevOpenHelper dicDevOpenHelper = new GuoShiDevOpenHelper(this, "Fruit", null);

        DaoMaster daoMaster = new DaoMaster(dicDevOpenHelper.getWritableDatabase());

        daoSession = daoMaster.newSession();
        QueryBuilder.LOG_SQL = BuildConfig.DEBUG;
        QueryBuilder.LOG_VALUES = BuildConfig.DEBUG;




    }


}
