package kr.ac.korea.capstoneproject.data.pojo;

import com.google.gson.annotations.SerializedName;

public class CafePageData {
    @SerializedName("cafeId")
    public String cafeId;

    public CafePageData (String cafeId) {
        this.cafeId = cafeId;
    }
}
