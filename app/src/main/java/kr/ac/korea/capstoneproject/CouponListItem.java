package kr.ac.korea.capstoneproject;

import android.graphics.drawable.Drawable;

public class CouponListItem {

    private Drawable imageDrawable ;
    private Drawable couponDrawable ;
    private String nameStr ;

    public Drawable getCafeImageDrawable(){
        return imageDrawable;

    }


    public void setCafeImageDrawable(Drawable imageDrawable) {
        this.imageDrawable = imageDrawable;
    }

    public Drawable getCouponDrawable() {
        return couponDrawable;
    }

    public void setCouponDrawable(Drawable couponDrawable) {
        this.couponDrawable = couponDrawable;
    }

    public String getNameStr() {
        return nameStr;
    }

    public void setNameStr(String nameStr) {
        this.nameStr = nameStr;
    }

}
