package kr.ac.korea.capstoneproject.data.remote;

import kr.ac.korea.capstoneproject.data.pojo.CafePageResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CafePageRequest {
        @GET("/api/cafes/" )
        Call<CafePageResponse> cafePageRequest();
}
