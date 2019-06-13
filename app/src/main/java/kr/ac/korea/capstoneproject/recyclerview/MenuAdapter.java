package kr.ac.korea.capstoneproject.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bumptech.glide.Glide;

import java.util.List;

import kr.ac.korea.capstoneproject.OrderActivity;
import kr.ac.korea.capstoneproject.R;
import kr.ac.korea.capstoneproject.data.pojo.CafeItemsData;

public class MenuAdapter extends RecyclerView.Adapter<MenuViewHolder> {

    private Context mContext;
    private List<CafeItemsData> mCafeItemsDataList;
//    private Button mCafePageBtn;



    public MenuAdapter(List<CafeItemsData> cafeItemsDataList, Context context){
        mCafeItemsDataList = cafeItemsDataList;
        mContext = context;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View baseView = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.menu_item, viewGroup, false);

        MenuViewHolder menuViewHolder = new MenuViewHolder(baseView);

        return menuViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder menuViewHolder, final int i) {
        //카페 메뉴 리스트 (임시)
        menuViewHolder.menuName.setText(mCafeItemsDataList.get(i).getName());
        menuViewHolder.menuPrice.setText(String.valueOf(mCafeItemsDataList.get(i).getPrice()));
        Glide.with(mContext).load(mCafeItemsDataList.get(i).getImage()).into(menuViewHolder.menuImage);


        menuViewHolder.mCafePageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, OrderActivity.class);

                intent.putExtra("name",mCafeItemsDataList.get(i).getName());
                intent.putExtra("price", mCafeItemsDataList.get(i).getPrice());
//                intent.putExtra("price",String.valueOf(mCafeItemsDataList.get(i).getPrice()));
                intent.putExtra("image",mCafeItemsDataList.get(i).getImage());

                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mCafeItemsDataList.size();
    }




}
