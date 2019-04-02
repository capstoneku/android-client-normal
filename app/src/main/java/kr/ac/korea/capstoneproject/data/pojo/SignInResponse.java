package kr.ac.korea.capstoneproject.data.pojo;

import com.google.gson.annotations.SerializedName;

public class SignInResponse {
    @SerializedName("success")
    public boolean success;
    @SerializedName("message")
    public String message;
}
