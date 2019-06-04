package kr.ac.korea.capstoneproject.fragment.Home;

public class HomeNearCafeData {
    String cafeImgUrl;
    String cafeDistance;
    String cafeName;

    public HomeNearCafeData(String cafeImgUrl, String cafeDistance, String cafeName) {
        this.cafeImgUrl = cafeImgUrl;
        this.cafeDistance = cafeDistance;
        this.cafeName = cafeName;
    }

    public String getCafeImgUrl() {
        return cafeImgUrl;
    }

    public String getCafeDistance() {
        return cafeDistance;
    }

    public String getCafeName() {
        return cafeName;
    }
}
