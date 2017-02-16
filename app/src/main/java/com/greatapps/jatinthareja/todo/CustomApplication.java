package com.greatapps.jatinthareja.todo;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by JatinThareja on 15-Feb-17.
 */

public class CustomApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
