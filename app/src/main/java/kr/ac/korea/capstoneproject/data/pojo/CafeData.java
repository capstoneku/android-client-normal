package kr.ac.korea.capstoneproject.data.pojo;

import com.google.gson.annotations.SerializedName;

public class CafeData {
    @SerializedName("success")
    public String success;
    @SerializedName("message")
    public String message;
    @SerializedName("errors")
    public String errors;
    @SerializedName("data")
    public Cafe data;

    public CafeData(String success, String message, String errors, Cafe data) {
        this.success = success;
        this.message = message;
        this.errors = errors;
        this.data = data;
    }

    public class Cafe {
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
        public String[] itemIds;
        @SerializedName("signatureItems")
        public String[] signatureItems;
        @SerializedName("profileImg")
        public String profileImg;
        @SerializedName("rating")
        public float rating;
        @SerializedName("reviewIds")
        public String[] reviewIds;
        @SerializedName("deprecated")
        public boolean deprecated;
        @SerializedName("created_at")
        public String createdAt;
        @SerializedName("updated_at")
        public String updatedAt;

        public Cafe(Geometry geometry, Options options, String cafeId, String name, String address,
                    String tel, String[] itemIds, String[] signatureItems, String profileImg,
                    float rating, String[] reviewIds, boolean deprecated, String createdAt, String updatedAt) {
            this.geometry = geometry;
            this.options = options;
            this.cafeId = cafeId;
            this.name = name;
            this.address = address;
            this.tel = tel;
            this.itemIds = itemIds;
            this.signatureItems = signatureItems;
            this.profileImg = profileImg;
            this.rating = rating;
            this.reviewIds = reviewIds;
            this.deprecated = deprecated;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
        }
    }

    public class Geometry {
        @SerializedName("type")
        public String type;
        @SerializedName("coordinates")
        public String[] coordinates;

        public Geometry(String type, String[] coordinates) {
            this.type = type;
            this.coordinates = coordinates;
        }
    }

    public class Options {
        @SerializedName("shop")
        public boolean shop;
        @SerializedName("togo")
        public boolean togo;

        public Options(boolean shop, boolean togo) {
            this.shop = shop;
            this.togo = togo;
        }
    }
}
