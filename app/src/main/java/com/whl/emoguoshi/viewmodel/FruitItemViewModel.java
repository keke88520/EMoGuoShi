package com.whl.emoguoshi.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.view.View;

import com.whl.emoguoshi.db.DevilFruit;
import com.whl.emoguoshi.view.activity.FruitDetailActivity;

/**
 * Created by wanghl on 16/8/23.
 */
public class FruitItemViewModel extends BaseViewModel {
    public ObservableField<String> title;
    public ObservableField<String> content;

    private Context context;
    private DevilFruit devilFruit;

    public FruitItemViewModel(Context context) {
        this.context = context;
        title = new ObservableField<>("自然系果实title");
        content = new ObservableField<>("果实简介info 果实简介info 果实简介info果实简介info果实简介info");
    }

    public void onItemClick(View view) {
        Intent intent = new Intent(context, FruitDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("fruit",devilFruit);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public void onDestroy() {

    }

    public void setFruit(DevilFruit devilFruit) {
        this.devilFruit = devilFruit;
        title.set(devilFruit.getFruitName());
        content.set(devilFruit.getDescript());
    }

    @Override
    public void onBackClick(View view) {

    }
}
