package kr.ac.korea.capstoneproject;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import kr.ac.korea.capstoneproject.CouponListItem;
import kr.ac.korea.capstoneproject.R;

public class CouponListAdapater extends BaseAdapter {
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList. (원본 데이터 리스트)
    private ArrayList<CouponListItem> c_listViewItemList= new ArrayList<CouponListItem>();
    // 필터링된 결과 데이터를 저장하기 위한 ArrayList. 최초에는 전체 리스트 보유.

    public CouponListAdapater() {

    }

    //Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    //ListView 에 보여질 item 수
    @Override
    public int getCount() {
        return c_listViewItemList.size() ;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return c_listViewItemList.get(position) ;
    }

    //position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_coupon, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        ImageView c_imageView = (ImageView) convertView.findViewById(R.id.c_imageView) ;
        TextView c_nameView = (TextView) convertView.findViewById(R.id.c_cafe_name) ;
        ImageView c_couponView= (ImageView) convertView.findViewById(R.id.c_current_coupon);

        CouponListItem c_pos = c_listViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        c_imageView.setImageDrawable(c_pos.getCafeImageDrawable());
        c_nameView.setText(c_pos.getNameStr());
        c_couponView.setImageDrawable(c_pos.getCouponDrawable());

        return convertView;
    }



    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(Drawable image, String cafeName, Drawable image2) {
        CouponListItem item = new CouponListItem();

        item.setCafeImageDrawable(image);
        item.setNameStr(cafeName);
        item.setCouponDrawable(image2);

        c_listViewItemList.add(item);
    }

}
