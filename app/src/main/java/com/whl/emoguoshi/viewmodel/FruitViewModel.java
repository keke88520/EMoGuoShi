package com.whl.emoguoshi.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.whl.emoguoshi.view.activity.FruitListActivity;

/**
 * Created by wanghl on 16/8/22.
 */
public class FruitViewModel extends BaseViewModel {
    private Context context;

    public FruitViewModel(Context context) {
        this.context = context;
    }

    public void onZiRanXiClick(View view) {
        Intent intent = new Intent(context, FruitListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("type","ziran");
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public void onDongWuXiClick(View view) {
        Intent intent = new Intent(context, FruitListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("type","dongwu");
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public void onChaoRenXiClick(View view) {
        Intent intent = new Intent(context, FruitListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("type","chaoren");
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public void onDestroy() {
//        context = null;
    }

    @Override
    public void onBackClick(View view) {

    }
}
