
package com.jacky.launcher.function;

import android.content.Context;
import android.content.Intent;

import com.jacky.launcher.R;
import com.jacky.launcher.app.AppUninstall;

import java.util.ArrayList;
import java.util.List;

public class FunctionModel {

    private int icon;
    private String id;
    private String name;
    private Intent mIntent;

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Intent getIntent() {
        return mIntent;
    }

    public void setIntent(Intent intent) {
        mIntent = intent;
    }

    public static List<FunctionModel> getFunctionList(Context context) {
        List<FunctionModel> functionModels = new ArrayList<>();

        FunctionModel f2 = new FunctionModel();
        f2.setName("快速搜索");
        f2.setIcon(R.drawable.h_search);
        functionModels.add(f2);


        FunctionModel f1 = new FunctionModel();
        f1.setName("随机播放");
        f1.setIcon(R.drawable.h_random);
        functionModels.add(f1);

        FunctionModel f3 = new FunctionModel();
        f3.setName("分类");
        f3.setIcon(R.drawable.h_category);
        functionModels.add(f3);


        FunctionModel appUninstall = new FunctionModel();
        appUninstall.setName("应用卸载");
        appUninstall.setIcon(R.drawable.ic_app_uninstall);
        appUninstall.setIntent(new Intent(context, AppUninstall.class));
        functionModels.add(appUninstall);


        return functionModels;
    }
}
