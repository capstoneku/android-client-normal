package kr.ac.korea.capstoneproject.data.remote;

import kr.ac.korea.capstoneproject.data.pojo.SignInData;
import kr.ac.korea.capstoneproject.data.pojo.SignInResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SignInRequest {
    @POST("/login")
    Call<SignInResponse> signInRequest(@Body SignInData signInData);
}
