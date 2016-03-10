package com.chs.myrecycleview;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * 作者：chs on 2016/3/10 18:15
 * 邮箱：657083984@qq.com
 */
public class APP extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }
}
