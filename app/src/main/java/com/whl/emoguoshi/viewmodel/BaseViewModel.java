package com.whl.emoguoshi.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.view.View;

/**
 * Created by wanghl on 16/7/6.
 */
public abstract class BaseViewModel extends BaseObservable implements IViewModel {
    public ObservableField<String > title;

    public abstract void onBackClick(View view);
}
