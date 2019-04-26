package kr.ac.korea.capstoneproject.data.remote;

import kr.ac.korea.capstoneproject.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "";

    private static Retrofit mRetrofit;

    public static synchronized Retrofit getInstance() {
        if(mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return mRetrofit;
    }
}
