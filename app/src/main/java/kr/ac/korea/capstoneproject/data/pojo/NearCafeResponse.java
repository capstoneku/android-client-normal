package kr.ac.korea.capstoneproject.data.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NearCafeResponse {
    @SerializedName("success")
    public String success;
    @SerializedName("message")
    public String message;
    @SerializedName("errors")
    public String errors;
    @SerializedName("data")
    public ArrayList<Cafe> data;

    public NearCafeResponse(String success, String message, String errors, ArrayList<Cafe> data) {
        this.success = success;
        this.message = message;
        this.errors = errors;
        this.data = data;
    }

    public ArrayList<Cafe> getData() {
        return this.data;
    }

    public class Cafe {
        @SerializedName("cafeId")
        public String cafeId;
        @SerializedName("name")
        public String name;
        @SerializedName("signatures")
        public String[] signatures;
        @SerializedName("img")
        public String img;
        @SerializedName("geometry")
        public Geometry geometry;
        @SerializedName("congestions")
        public String congestions;

        public Cafe(String cafeId, String name, String[] signatures, String img, String congestions) {
            this.cafeId = cafeId;
            this.name = name;
            this.signatures = signatures;
            this.img = img;
            this.congestions = congestions;
        }

        public String getCafeId() {
            return cafeId;
        }

        public String getName() {
            return name;
        }

        public String[] getSignatures() {
            return signatures;
        }

        public String getImg() {
            return img;
        }

        public String getCongestions() {
            return congestions;
        }

        public class Geometry {
            String type;
            double[] coordinates;

            public Geometry(String type, double[] coordinates) {
                this.type = type;
                this.coordinates = coordinates;
            }

            public String getType() {
                return type;
            }

            public double[] getCoordinates() {
                return coordinates;
            }
        }
    }
}
