package kr.ac.korea.capstoneproject.data.remote;

import kr.ac.korea.capstoneproject.data.pojo.CafePageResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CafePageRequest {
        @GET("/api/cafes/Ic5D7iayaEK7bxzD" )
        Call<CafePageResponse> cafePageRequest();
}
