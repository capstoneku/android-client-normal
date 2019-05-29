package kr.ac.korea.capstoneproject.data.pojo;

import com.google.gson.annotations.SerializedName;

public class SignInResponse {
    @SerializedName("success")
    public boolean success;
    @SerializedName("message")
    public String message;
    @SerializedName("errors")
    public String errors;
    @SerializedName("data")
    public SignInResponseData data; // JSON Web Token

    public class SignInResponseData {
        String nickname;
        String token;

        public String getToken() {
            return token;
        }
    }
}
