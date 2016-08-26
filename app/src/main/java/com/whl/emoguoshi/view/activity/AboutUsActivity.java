package com.whl.emoguoshi.view.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.whl.emoguoshi.R;
import com.whl.emoguoshi.databinding.ActivityAboutUsBinding;
import com.whl.emoguoshi.viewmodel.AboutUsViewModel;


public class AboutUsActivity extends AppCompatActivity implements AboutUsViewModel.AboutUsViewModelListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_about_us);
        ActivityAboutUsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_about_us);
        AboutUsViewModel viewModel = new AboutUsViewModel(this);
        binding.setViewModel(viewModel);
    }

    @Override
    public void onBackClick() {
        finish();
    }
}
