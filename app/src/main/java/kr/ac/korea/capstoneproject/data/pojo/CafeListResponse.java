package kr.ac.korea.capstoneproject.data.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CafeListResponse{
    @SerializedName("success")
    public String success;
    @SerializedName("message")
    public String message;
    @SerializedName("errors")
    public String errors;
    @SerializedName("data")
    public List<CafeList> data;

    public List<CafeList> getCafeList() {
        return data;
    }
}