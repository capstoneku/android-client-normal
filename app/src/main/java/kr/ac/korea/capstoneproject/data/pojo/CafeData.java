package kr.ac.korea.capstoneproject.data.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CafeData {

    @SerializedName("geometry")
    public Geometry geometry;
    @SerializedName("options")
    public Options options;
    @SerializedName("cafeId")
    public String cafeId;
    @SerializedName("name")
    public String name;
    @SerializedName("address")
    public String address;
    @SerializedName("tel")
    public String tel;
    @SerializedName("itemIds")
    public List<String> itemIds;
    @SerializedName("profileImg")
    public String profileImg;
    @SerializedName("deprecated")
    public boolean deprecated;
    @SerializedName("congestion")
    public float congestion;


    public class Geometry{
        public String type;
        public float[] coordinates;
    }

    public class Options{
        public boolean shop;
        public boolean togo;
    }

    public CafeData(
            Geometry geometry, Options options, String cafeId, String name, String address, String tel, List<String> itemIds, String profileImg, boolean deprecated, float congestion) {
        this.cafeId = cafeId;
        this.geometry = geometry;
        this.options = options;
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.itemIds = itemIds;
        this.profileImg = profileImg;
        this.deprecated = deprecated;
        this.congestion = congestion;
    }

    public Geometry getGeometry() { return geometry;}

    public Options getOptions() { return options;}

    public String getCafeId() { return cafeId;}

    public String getName() { return name;}

    public String getAddress() { return address;}

    public String getTel() { return tel;}

    public List<String> getItemIds() { return itemIds;}

    public String getProfileImg() { return profileImg;}

    public boolean getdeprecated() { return deprecated;}

    public float getCongestion() { return congestion;}

}
