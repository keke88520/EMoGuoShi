package com.whl.emoguoshi.viewmodel;

import android.databinding.ObservableField;
import android.view.View;

import com.whl.emoguoshi.db.DevilFruit;
import com.whl.emoguoshi.model.FruitModel;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wanghl on 16/8/23.
 */
public class FruitListViewModel extends BaseViewModel {
    private FruitModel fruitModel;
    private Subscription subscription;
    private FruitListViewModelListener listener;

    public FruitListViewModel(FruitListViewModelListener listener) {
        this.listener = listener;
        this.fruitModel = new FruitModel();
        this.title = new ObservableField<>();
    }

    @Override
    public void onDestroy() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
            subscription = null;
        }
        fruitModel = null;
        listener = null;

    }

    public void getFruitByType(final String type) {
        if ("chaoren".equalsIgnoreCase(type)) {
            title.set("超人系");
        } else if ("ziran".equalsIgnoreCase(type)) {
            title.set("自然系");
        } else if ("dongwu".equalsIgnoreCase(type)) {
            title.set("动物系");
        }

        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
            subscription = null;
        }


        subscription = Observable.create(new Observable.OnSubscribe<List<DevilFruit>>() {
            @Override
            public void call(Subscriber<? super List<DevilFruit>> subscriber) {
                subscriber.onStart();
                subscriber.onNext(fruitModel.getFruitsByType(type));
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<DevilFruit>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<DevilFruit> devilFruits) {
                        if (listener != null) {
                            listener.onFruitsLoadFinish(devilFruits);
                        }
                    }
                });
    }

    @Override
    public void onBackClick(View view) {
        if (listener != null) {
            listener.onBackClick();
        }
    }

    public interface FruitListViewModelListener{
        void onFruitsLoadFinish(List<DevilFruit> fruits);

        void onBackClick();
    }
}
