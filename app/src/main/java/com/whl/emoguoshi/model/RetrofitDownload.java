package com.whl.emoguoshi.model;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by wanghl on 16/8/25.
 */
public interface RetrofitDownload {
    @GET("{fileName}")
    Observable<Response<ResponseBody>> download(@Path("fileName") String fileName);
}
