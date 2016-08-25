package com.whl.emoguoshi.viewmodel;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.whl.emoguoshi.model.RetrofitDownload;

import java.io.File;
import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.Okio;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by wanghl on 16/8/25.
 */
public class WallPaperDetailViewModel extends BaseViewModel {
    public ObservableField<String > imgUrl;
    private RetrofitDownload retrofitDownload;
    private Subscription subscription;

    public WallPaperDetailViewModel() {
        this.imgUrl = new ObservableField<>();
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl("http://img1.acgjiazu.com").
                client(genericClient()).
        addCallAdapterFactory(RxJavaCallAdapterFactory.create()).
                build();
        retrofitDownload = retrofit.create(RetrofitDownload.class);
    }

    public  OkHttpClient genericClient() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                                .addHeader("Accept-Encoding", "gzip, deflate")
                                .addHeader("Connection", "keep-alive")
                                .addHeader("Accept", "*/*")
                                .addHeader("Cookie", "add cookies here")
                                .build();
                        return chain.proceed(request);
                    }

                })
                .build();

        return httpClient;
    }
    public Observable<File> download(String id) {
        return retrofitDownload.download(id)
                .flatMap(new Func1<Response<ResponseBody>, Observable<File>>() {

                    @Override
                    public Observable<File> call(Response<ResponseBody> responseBodyResponse) {
                        return saveFile(responseBodyResponse);
                    }
                });
    }

    public Observable<File> saveFile(final Response<ResponseBody> response) {

        return Observable.create(new Observable.OnSubscribe<File>() {
            @Override
            public void call(Subscriber<? super File> subscriber) {
                subscriber.onStart();
                try {
                    // you can access headers of response
//                    String header = response.headers().get("Content-Disposition");
                    // this is specific case, it's up to you how you want to save your file
                    // if you are not downloading file from direct link, you might be lucky to obtain file name from header
//                    String fileName = header.replace("attachment; filename=", "");
                    // will create file in global Music directory, can be any other directory, just don't forget to handle permissions
                    File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).getAbsoluteFile(), "q.jpeg");

                    BufferedSink sink = Okio.buffer(Okio.sink(file));
                    // you can access body of response
                    sink.writeAll(response.body().source());
                    sink.close();
                    subscriber.onNext(file);
                    subscriber.onCompleted();
                } catch (IOException e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        });

    }
    public void onCancelClick(View view) {

    }


    public void onDownloadClick(View view) {

      subscription =   download("img/97068f707717e8d2a37490e615487ded.jpeg@!640-1136")
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<File>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
e.printStackTrace();
            }

            @Override
            public void onNext(File file) {
                Log.d("onNext", file.getAbsolutePath());
            }
        })
        ;
    }

    @Override
    public void onBackClick(View view) {

    }

    @Override
    public void onDestroy() {

        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
            subscription = null;
        }
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String url) {
        Picasso.with(view.getContext()).load(url).into(view);
    }

    public void setImgPath(String imgPath) {
        this.imgUrl.set(imgPath+"@!640-1136");
    }
}
