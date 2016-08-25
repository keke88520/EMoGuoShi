package com.whl.emoguoshi.viewmodel;

import android.content.Context;
import android.view.View;

import com.whl.emoguoshi.domain.WallPaperBean;
import com.whl.emoguoshi.model.PaperModel;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wanghl on 16/8/22.
 */
public class WallPaperViewModel extends BaseViewModel {
    private PaperModel paperModel;
    private Context context;
    private Subscription subscription;
    private WallPaperViewModelListener listener;


    public WallPaperViewModel(Context context,WallPaperViewModelListener listener) {
        this.listener = listener;
        this.context = context;
        paperModel = new PaperModel();
    }

    @Override
    public void onDestroy() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
            subscription = null;
        }
    }

    @Override
    public void onBackClick(View view) {
        
    }

    public void loadPaper() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
            subscription = null;
        }

       subscription =  Observable.create(new Observable.OnSubscribe<List<WallPaperBean>>() {
            @Override
            public void call(Subscriber<? super List<WallPaperBean>> subscriber) {
                subscriber.onStart();
                subscriber.onNext(paperModel.loadPapers(context));
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<WallPaperBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<WallPaperBean> wallPaperBeen) {
                        if (listener != null) {
                            listener.onPapeLoadFinish(wallPaperBeen);
                        }
                    }
                });
    }

    public interface  WallPaperViewModelListener{
        void onPapeLoadFinish(List<WallPaperBean> wallPaperBeen);
    }
}
