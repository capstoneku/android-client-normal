package kr.ac.korea.capstoneproject.data.pojo;

import com.google.gson.annotations.SerializedName;

public class CafeList {
    @SerializedName("cafeIds")
    public String cafeIds;
    @SerializedName("name")
    public String name;
    @SerializedName("rating")
    public String[] rating;
    @SerializedName("reviews")
    public String reviews;
    @SerializedName("signatures")
    public String[] signatures;
    @SerializedName("img")
    public String Img;
    @SerializedName("congestion")
    public int congestion;

    public CafeList(String cafeIds, String name, String[] rating, String reviews, String[] signatures, String img, int congestion) {
        this.cafeIds = cafeIds;
        this.name = name;
        this.rating = rating;
        this.reviews = reviews;
        this.signatures = signatures;
        Img = img;
        this.congestion = congestion;
    }

    public String getCafeIds() {
        return cafeIds;
    }

    public String getName() {
        return name;
    }

    public String[] getRating() {
        return rating;
    }

    public String getReviews() {
        return reviews;
    }

    public String[] getSignatures() {
        return signatures;
    }

    public String getImg() {
        return Img;
    }

    public int getCongestion() {
        return congestion;
    }
}
