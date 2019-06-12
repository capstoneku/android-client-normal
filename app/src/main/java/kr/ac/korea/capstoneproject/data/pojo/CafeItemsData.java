package kr.ac.korea.capstoneproject.data.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CafeItemsData {

    @SerializedName("image")
    public String image;
    @SerializedName("options")
    public Options options;
    @SerializedName("name")
    public String name;
    @SerializedName("price")
    public int price;

    public CafeItemsData(String image, Options options, String name, int price) {
        this.image = image;
        this.options = options;
        this.name = name;
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public class Options {
        @SerializedName("ice")
        public int ice;

        public Options(int ice) {
            this.ice = ice;
        }

        public int getIce() {
            return ice;
        }
    }
}
