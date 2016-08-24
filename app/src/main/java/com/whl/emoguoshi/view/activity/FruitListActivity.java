package com.whl.emoguoshi.view.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.whl.emoguoshi.R;
import com.whl.emoguoshi.adapter.FruitListAdapter;
import com.whl.emoguoshi.databinding.ActivityFruitListBinding;
import com.whl.emoguoshi.db.DevilFruit;
import com.whl.emoguoshi.recycler.MyItemDecoration;
import com.whl.emoguoshi.viewmodel.AdItemViewModel;
import com.whl.emoguoshi.viewmodel.FruitListViewModel;

import java.util.List;

public class FruitListActivity extends BaseActivity implements FruitListViewModel.FruitListViewModelListener {

    private ActivityFruitListBinding binding;
    private FruitListAdapter adapter;
    private FruitListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_fruit_list);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_fruit_list);
        viewModel = new FruitListViewModel(this);
        binding.setViewModel(viewModel);

        setUpRecyclerView(binding.rvFruit);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String type = bundle.getString("type", null);
            if (!TextUtils.isEmpty(type)) {
                viewModel.getFruitByType(type);
            }
        }
    }

    private void setUpRecyclerView(RecyclerView recyclerView) {
        recyclerView.addItemDecoration(new MyItemDecoration(this));
//        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        adapter = new FruitListAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
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
    public void onFruitsLoadFinish(List<DevilFruit> fruits) {
        adapter.setData(fruits);
    }

    @Override
    public void onBackClick() {
        finish();
    }
}
