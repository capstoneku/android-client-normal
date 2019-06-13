package kr.ac.korea.capstoneproject.data.pojo;

import com.google.gson.annotations.SerializedName;

public class NearCafeData {
    @SerializedName("longitude")
    public double longitude;
    @SerializedName("latitude")
    public double latitude;

    public NearCafeData(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
