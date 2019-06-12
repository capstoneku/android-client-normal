package kr.ac.korea.capstoneproject.data.remote;

import kr.ac.korea.capstoneproject.data.pojo.CafeListResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CafeListRequest {
    @GET("/api/cafes")
    Call<CafeListResponse> getCafeList() ;
}
