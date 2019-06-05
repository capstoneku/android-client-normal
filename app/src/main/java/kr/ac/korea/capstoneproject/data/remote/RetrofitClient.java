package kr.ac.korea.capstoneproject.data.remote;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "http://13.209.42.209";

    private static Retrofit mRetrofit;

    public static synchronized Retrofit getInstance() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.connectTimeout(200, TimeUnit.SECONDS);
        client.readTimeout(200, TimeUnit.SECONDS);
        client.writeTimeout(200, TimeUnit.SECONDS);

        if(mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client.build())
                    .build();
        }

        return mRetrofit;
    }
}
