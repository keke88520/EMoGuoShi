package com.whl.emoguoshi.viewmodel;

import android.app.WallpaperManager;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

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
    private final String baseUrl;
    public ObservableField<String > imgUrl;
    public ObservableField<String > text;
    private RetrofitDownload retrofitDownload;
    private Subscription subscription;

    private String fileName;

    private String imgPath;

    private boolean fileExists = false;

    private Context context;

    private WallPaperDetailViewModelListener listener;

    public WallPaperDetailViewModel(Context context,WallPaperDetailViewModelListener listener) {
        this.listener = listener;
        this.context = context;
        this.imgUrl = new ObservableField<>();
        this.text = new ObservableField<>();
        baseUrl = "http://img1.acgjiazu.com";
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(baseUrl).
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
                    File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"emoguoshi");
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    File file = new File(dir.getAbsolutePath(), fileName);

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
        if (listener != null) {
            listener.onCancleClick();
        }
    }


    public void onDownloadClick(View view) {

        if (!fileExists) {
            String path = imgPath.substring(baseUrl.length() + 1) + "@!640-1136";
            subscription = download(path)
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
                            checkFile();
                        }
                    })
            ;
        } else {

            WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);
            Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"emoguoshi"+File.separator+fileName);
            try {
                wallpaperManager.setBitmap(bitmap);
                Toast.makeText(context, "设置成功",Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                Toast.makeText(context, "设置失败",Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
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
        this.imgPath = imgPath;
        fileName =  imgPath.substring(baseUrl.length()+5);
        this.imgUrl.set(imgPath+"@!640-1136");

        checkFile();


    }

    private void checkFile() {
        File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"emoguoshi");

        File file = new File(dir.getAbsolutePath(), fileName);

        fileExists = file.exists();

        if (fileExists) {
            text.set("设置为壁纸");
        } else {
            text.set("下载");
        }
    }

    public interface WallPaperDetailViewModelListener{
        void onCancleClick();
    }
}
