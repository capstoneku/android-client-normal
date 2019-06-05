package kr.ac.korea.capstoneproject.data.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CafeListResponse{
    @SerializedName("success")
    public String success;
    @SerializedName("message")
    public String message;
    @SerializedName("errors")
    public String errors;
    @SerializedName("data")
    public ArrayList<CafeList> data;

    public ArrayList<CafeList> getCafeList() {
        return data;
    }
}