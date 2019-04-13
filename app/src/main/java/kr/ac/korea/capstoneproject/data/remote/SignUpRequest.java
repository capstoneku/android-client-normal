package kr.ac.korea.capstoneproject.data.remote;

import kr.ac.korea.capstoneproject.data.pojo.SignUpData;
import kr.ac.korea.capstoneproject.data.pojo.SignUpResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SignUpRequest {
    @POST("/api/users")
    Call<SignUpResponse> signUpRequest(@Body SignUpData signUpData);
}
