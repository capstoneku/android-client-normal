package kr.ac.korea.capstoneproject.data.pojo;

import com.google.gson.annotations.SerializedName;

public class SignInData {
    @SerializedName("email")
    public String email;
    @SerializedName("nickname")
    public String nickname;
    @SerializedName("password")
    public String password;
    @SerializedName("passwordConfirmation")
    public String passwordConfirmation;

    public SignInData(String email, String nickname, String password, String passwordConfirmation) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
    }
}
