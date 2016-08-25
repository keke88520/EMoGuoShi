package com.whl.emoguoshi.view.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.whl.emoguoshi.R;
import com.whl.emoguoshi.databinding.ActivityWallPaperDetailBinding;
import com.whl.emoguoshi.viewmodel.WallPaperDetailViewModel;

import uk.co.senab.photoview.PhotoViewAttacher;

public class WallPaperDetailActivity extends AppCompatActivity {

    private ActivityWallPaperDetailBinding binding;
    private WallPaperDetailViewModel viewModel;
    PhotoViewAttacher mAttacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_wall_paper_detail);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_wall_paper_detail);
        viewModel = new WallPaperDetailViewModel();
        binding.setViewModel(viewModel);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String imgPath = bundle.getString("imgPath", null);
            if (!TextUtils.isEmpty(imgPath)) {
                viewModel.setImgPath(imgPath);
            }
        }

        mAttacher = new PhotoViewAttacher(binding.ivWallPaper);
    }

    @Override
    protected void onDestroy() {
        if (viewModel!= null) {
            viewModel.onDestroy();
            viewModel = null;
        }
        super.onDestroy();
    }
}
