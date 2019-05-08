package kr.ac.korea.capstoneproject.data.pojo;

import com.google.gson.annotations.SerializedName;

public class CafeData {

    @SerializedName("cafeId")
    public String cafeId;
    @SerializedName("geometry")
    public Geometry geometry;
    @SerializedName("name")
    public String name;
    @SerializedName("address")
    public String address;
    @SerializedName("tel")
    public String tel;
    @SerializedName("itemIds")
    public String itemIds;
    @SerializedName("deprecated")
    public boolean deprecated;

    public class Geometry{
        public String type;
        public float[] coordinates;

    }

}
