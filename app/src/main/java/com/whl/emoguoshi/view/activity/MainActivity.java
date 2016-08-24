package com.whl.emoguoshi.view.activity;

import android.annotation.TargetApi;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.whl.emoguoshi.R;
import com.whl.emoguoshi.databinding.ActivityMainBinding;
import com.whl.emoguoshi.view.fragment.FruitFragment;
import com.whl.emoguoshi.view.fragment.SearchFragment;
import com.whl.emoguoshi.view.fragment.SettingFragment;
import com.whl.emoguoshi.view.fragment.WallPaperFragment;
import com.whl.emoguoshi.viewmodel.MainViewModel;


public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {

    private ActivityMainBinding binding;
    private MainViewModel viewModel;

    private FruitFragment fruitFragment;
    private SearchFragment searchFragment;
    private WallPaperFragment wallPaperFragment;
    private SettingFragment settingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new MainViewModel();
        binding.setViewModel(viewModel);
        setUpBottomMenu(binding.bottomNavigationBar);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        if (fruitFragment==null){
            fruitFragment = FruitFragment.newInstance(null,null);
        }
        transaction.replace(R.id.ll_container,fruitFragment);
        transaction.commit();//提交事务
    }

    private void setUpBottomMenu(BottomNavigationBar bottomNavigationBar) {
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.ic_bottom_menu_0_normal, "恶魔果实")).setActiveColor(R.color.colorActive)
                .addItem(new BottomNavigationItem(R.mipmap.ic_bottom_menu_1_normal,"查询")).setActiveColor(R.color.colorActive)
                .addItem(new BottomNavigationItem(R.mipmap.ic_bottom_menu_2_normal,"壁纸")).setActiveColor(R.color.colorActive)
                .addItem(new BottomNavigationItem(R.mipmap.ic_bottom_menu_3_normal,"设置")).setActiveColor(R.color.colorActive)
                .setFirstSelectedPosition(0)
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(int position) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        switch (position){
            case 0:
                if (fruitFragment==null){
                    fruitFragment = FruitFragment.newInstance(null,null);
                }
                transaction.replace(R.id.ll_container,fruitFragment);
                break;
            case 1:
                if (searchFragment==null){
                    searchFragment = SearchFragment.newInstance(null,null);
                }
                transaction.replace(R.id.ll_container,searchFragment);
                break;
            case 2:
                if (wallPaperFragment==null){
                    wallPaperFragment = WallPaperFragment.newInstance(null,null);
                }
                transaction.replace(R.id.ll_container,wallPaperFragment);
                break;
            case 3:
                if (settingFragment==null){
                    settingFragment = SettingFragment.newInstance(null,null);
                }
                transaction.replace(R.id.ll_container,settingFragment);
                break;
        }
        transaction.commit();//提交事务
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
