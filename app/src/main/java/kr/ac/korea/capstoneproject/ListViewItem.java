package kr.ac.korea.capstoneproject;


import android.graphics.drawable.Drawable;

// ListView의 아이템에 표시될 데이터 클래스 정의

public class ListViewItem {
    private Drawable iconDrawable ;
    private String nameStr ;
    private String revStr ;
    private String menuStr;

    public void setImage(Drawable image) {
        iconDrawable = image ;
    }
    public void setName(String cafeName) {
        nameStr = cafeName;
    }
    public void setRev(String review) {
        revStr = review ;
    }
    public void setMenu(String hitMenu) {
        menuStr = hitMenu ;
    }

    public Drawable getImage() {
        return this.iconDrawable ;
    }
    public String getName() {
        return this.nameStr ;
    }
    public String getRev() {
        return this.revStr ;
    }
    public String getMenu() {
        return this.menuStr ;
    }
}