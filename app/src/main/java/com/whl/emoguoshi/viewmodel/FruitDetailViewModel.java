package com.whl.emoguoshi.viewmodel;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.whl.emoguoshi.db.DevilFruit;

/**
 * Created by wanghl on 16/8/23.
 */
public class FruitDetailViewModel extends BaseViewModel {
    public ObservableField<String > imgUrl;
    public ObservableField<String > type;
    public ObservableField<String > fruitName;
    public ObservableField<String > roleName;
    public ObservableField<String > apearChapter;
    public ObservableField<String > descript;

    private FruitDetailViewModelListener listener;

    public FruitDetailViewModel(FruitDetailViewModelListener listener) {
        this.listener = listener;imgUrl = new ObservableField<>();
        title = new ObservableField<>();
        type = new ObservableField<>();
        fruitName = new ObservableField<>();
        roleName = new ObservableField<>();
        apearChapter = new ObservableField<>();
        descript = new ObservableField<>();
    }



    @Override
    public void onDestroy() {
        listener = null;
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String url) {
        Picasso.with(view.getContext()).load(url).into(view);
    }

    public void setFruit(DevilFruit devilFruit) {
        String url = String.format("file:///android_asset/fruit/%s", devilFruit.getImgUrl());
        imgUrl.set(url);
        title.set(devilFruit.getFruitName());
        this.fruitName.set(devilFruit.getFruitName());
        this.roleName.set(devilFruit.getRoleName());
        this.apearChapter.set(devilFruit.getApearChapter());
        if ("chaoren".equalsIgnoreCase(devilFruit.getType())) {
            type.set("超人系");
        } else if ("ziran".equalsIgnoreCase(devilFruit.getType())) {
            type.set("自然系");
        } else if ("dongwu".equalsIgnoreCase(devilFruit.getType())) {
            type.set("动物系");
        }

        this.descript.set(devilFruit.getDescript());
    }

    @Override
    public void onBackClick(View view) {
        if (listener != null) {
            listener.onBackClick();
        }
    }

    public interface FruitDetailViewModelListener{
        void onBackClick();
    }
}
