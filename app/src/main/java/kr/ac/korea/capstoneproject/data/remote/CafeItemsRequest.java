package kr.ac.korea.capstoneproject.data.remote;

import kr.ac.korea.capstoneproject.data.pojo.CafeItemsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CafeItemsRequest {
    @GET("/api/cafes/{cafeId}/items" )
    Call<CafeItemsResponse> cafeItemsRequest(@Path("cafeId") String cafeId);

}
