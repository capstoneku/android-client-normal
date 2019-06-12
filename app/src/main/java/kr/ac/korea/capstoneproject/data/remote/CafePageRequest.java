package kr.ac.korea.capstoneproject.data.remote;

import kr.ac.korea.capstoneproject.data.pojo.CafePageResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CafePageRequest {
        @GET("/api/cafes/{cafeId}")
        Call<CafePageResponse> cafePageRequest(@Path("cafeId") String cafeId);
}
