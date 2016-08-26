package com.whl.emoguoshi.viewmodel;

import android.databinding.ObservableField;
import android.view.View;

/**
 * Created by wanghl on 16/8/26.
 */
public class AboutUsViewModel extends BaseViewModel {
    private AboutUsViewModelListener listener;



    public AboutUsViewModel(AboutUsViewModelListener listener) {
        this.listener = listener;
        title = new ObservableField<>("关于我们");
    }

    @Override
    public void onBackClick(View view) {
        if (listener != null) {
            listener.onBackClick();
        }
    }

    @Override
    public void onDestroy() {

    }

    public interface AboutUsViewModelListener{
        void onBackClick();
    }
}
