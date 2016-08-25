package com.whl.emoguoshi.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.whl.emoguoshi.domain.WallPaperBean;
import com.whl.emoguoshi.view.activity.WallPaperDetailActivity;

/**
 * Created by wanghl on 16/8/23.
 */
public class WallPaperItemViewModel extends BaseViewModel {
    public ObservableField<String > imgUrl;

    private Context context;

    private String imgPath;


    public WallPaperItemViewModel(Context context) {
        this.context = context;
        imgUrl = new ObservableField<>();
    }

    public void onItemClick(View view) {
        Intent intent = new Intent(context, WallPaperDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("imgPath",imgPath);
        intent.putExtras(bundle);
        context.startActivity(intent);

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onBackClick(View view) {

    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String url) {
        Picasso.with(view.getContext()).load(url).into(view);
    }

    public void setPaper(WallPaperBean paperBean) {
        this.imgPath = paperBean.getUrl();
        imgUrl.set(paperBean.getUrl()+"@!640-1136-thumb");
    }
}
