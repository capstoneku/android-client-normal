package kr.ac.korea.capstoneproject.data.pojo;

import com.google.gson.annotations.SerializedName;

public class SignInData {
    @SerializedName("email")
    public String email;
    @SerializedName("password")
    public String password;

    public SignInData(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
