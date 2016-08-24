package com.whl.emoguoshi.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.whl.emoguoshi.R;
import com.whl.emoguoshi.databinding.ActivityFruitDetailBinding;
import com.whl.emoguoshi.db.DevilFruit;
import com.whl.emoguoshi.viewmodel.FruitDetailViewModel;

import java.io.Serializable;

public class FruitDetailActivity extends BaseActivity implements FruitDetailViewModel.FruitDetailViewModelListener {

    private ActivityFruitDetailBinding binding;
    private FruitDetailViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_fruit_detail);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_fruit_detail);
        viewModel = new FruitDetailViewModel(this);
        binding.setViewModel(viewModel);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Serializable obj = bundle.getSerializable("fruit");
            if (obj instanceof DevilFruit) {
                DevilFruit devilFruit = (DevilFruit) obj;
                viewModel.setFruit(devilFruit);
            }
        }
    }

    @Override
    protected void onDestroy() {
        if (viewModel != null) {
            viewModel.onDestroy();
            viewModel = null;
        }
        super.onDestroy();
    }

    @Override
    public void onBackClick() {
        finish();
    }
}
