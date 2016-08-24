package com.whl.emoguoshi.viewmodel;

import android.text.Editable;
import android.text.TextWatcher;
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
 * Created by wanghl on 16/8/22.
 */
public class SearchViewModel extends BaseViewModel {
    private FruitModel fruitModel;
    private Subscription subscription;
    private SearchViewModelListener listener;

    public SearchViewModel(SearchViewModelListener listener) {
        this.listener = listener;
        this.fruitModel = new FruitModel();
    }

    @Override
    public void onDestroy() {
        this.fruitModel = null;
    }

    @Override
    public void onBackClick(View view) {

    }

    public TextWatcher getTextChangedListener() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String keyWord = editable.toString();
                getFruitByKeyword(keyWord);
            }
        };

    }

    public void getFruitByKeyword(final String keyword) {


        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
            subscription = null;
        }


        subscription = Observable.create(new Observable.OnSubscribe<List<DevilFruit>>() {
            @Override
            public void call(Subscriber<? super List<DevilFruit>> subscriber) {
                subscriber.onStart();
                subscriber.onNext(fruitModel.getFruitsByKeyWord(keyword));
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

    public interface SearchViewModelListener{
        void onFruitsLoadFinish(List<DevilFruit> devilFruits);
    }
}
