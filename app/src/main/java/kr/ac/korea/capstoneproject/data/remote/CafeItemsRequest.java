package kr.ac.korea.capstoneproject.data.remote;

import kr.ac.korea.capstoneproject.data.pojo.CafeItemsResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CafeItemsRequest {
    @GET("/api/cafes/Ic5D7iayaEK7bxzD/items" )
    Call<CafeItemsResponse> cafeItemsRequest();

}
