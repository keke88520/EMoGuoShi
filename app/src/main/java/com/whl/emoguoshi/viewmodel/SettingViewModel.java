package com.whl.emoguoshi.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.whl.emoguoshi.view.activity.AboutUsActivity;

/**
 * Created by wanghl on 16/8/22.
 */
public class SettingViewModel extends BaseViewModel {
    private Context context;

    public SettingViewModel(Context context) {
        this.context = context;
    }

    public void onAboutUs(View view) {
        Intent intent = new Intent(context, AboutUsActivity.class);
        this.context.startActivity(intent);

    }
    @Override
    public void onDestroy() {

    }

    @Override
    public void onBackClick(View view) {

    }
}
