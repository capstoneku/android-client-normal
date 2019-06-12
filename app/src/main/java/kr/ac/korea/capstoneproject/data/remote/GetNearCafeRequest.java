package kr.ac.korea.capstoneproject.data.remote;

import kr.ac.korea.capstoneproject.data.pojo.NearCafeData;
import kr.ac.korea.capstoneproject.data.pojo.NearCafeResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface GetNearCafeRequest {
    @POST("/api/cafes/near")
    Call<NearCafeResponse> getNearCafeRequest(@Body NearCafeData nearCafeData);
}
