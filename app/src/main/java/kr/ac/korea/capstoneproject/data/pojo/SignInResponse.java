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
    public Data data;

    public class Data {
        @SerializedName("email")
        String email;
        @SerializedName("nickname")
        String nickname;
        @SerializedName("token")
        String token; // JSON Web Token

        public String getEmail() {
            return email;
        }

        public String getNickname() {
            return nickname;
        }

        public String getToken() {
            return token;
        }
    }
}
