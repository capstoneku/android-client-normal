package kr.ac.korea.capstoneproject.data.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CafeList {
    /**
     * "cafeId": "Ic5D7iayaEK7bxzD",
     * "name": "PIKA COFFEE",
     * "rating": [],
     * "signatures": [],
     * "img": "http://13.209.42.209:80/uploads/Ic5D7iayaEK7bxzD/PIKA COFFEE.png",
     * "congestion": 0
     */
    @SerializedName("cafeId")
    private String cafeId;
    @SerializedName("name")
    private String name;
    @SerializedName("rating")
    private float rating;
    @SerializedName("signatures")
    private List<String> signatures;
    @SerializedName("img")
    private String img;
    @SerializedName("congestion")
    private int congestion;


    public String getCafeId() {
        return cafeId;
    }

    public void setCafeId(String cafeId) {
        this.cafeId = cafeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public List<String> getSignatures() {
        return signatures;
    }

    public void setSignatures(List<String> signatures) {
        this.signatures = signatures;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getCongestion() {
        return congestion;
    }

    public void setCongestion(int congestion) {
        this.congestion = congestion;
    }


}
