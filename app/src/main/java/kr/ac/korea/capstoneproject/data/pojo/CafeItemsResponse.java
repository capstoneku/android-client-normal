package kr.ac.korea.capstoneproject.data.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CafeItemsResponse {

        @SerializedName("success")
        public boolean success;
        @SerializedName("message")
        public String message;
        @SerializedName("errors")
        public String errors;
        @SerializedName("data")
        public List<CafeItemsData> data;

        public List<CafeItemsData> getCafeItemsData() { return data; }


}
