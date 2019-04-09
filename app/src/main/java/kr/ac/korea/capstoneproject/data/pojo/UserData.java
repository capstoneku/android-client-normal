package kr.ac.korea.capstoneproject.data.pojo;

import com.google.gson.annotations.SerializedName;

public class UserData {
    @SerializedName("isStaff")
    public boolean isStaff;
    @SerializedName("isOwner")
    public boolean isOwner;
    @SerializedName("myCafeId")
    public String myCafeId;
    @SerializedName("myOwnCoffeeShopId")
    public String myOwnCoffeeShopId;
    @SerializedName("email")
    public String email;
    @SerializedName("password")
    public String password;
    @SerializedName("created_at")
    public String createdAt;
    @SerializedName("updated_at")
    public String updatedAt;
}
