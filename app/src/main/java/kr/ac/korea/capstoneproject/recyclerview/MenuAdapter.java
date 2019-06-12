package kr.ac.korea.capstoneproject.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import kr.ac.korea.capstoneproject.R;
import kr.ac.korea.capstoneproject.data.pojo.CafeItemsData;
import kr.ac.korea.capstoneproject.data.pojo.CafeItemsResponse;
import kr.ac.korea.capstoneproject.fragment.CafePageFragment;
import retrofit2.Callback;

public class MenuAdapter extends RecyclerView.Adapter<MenuViewHolder> {

    private Context mContext;
    private List<CafeItemsData> mCafeItemsDataList;

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
    public void onBindViewHolder(@NonNull MenuViewHolder menuViewHolder, int i) {
        //카페 메뉴 리스트 (임시)
        menuViewHolder.menuName.setText(mCafeItemsDataList.get(i).getName());
        menuViewHolder.menuPrice.setText(String.valueOf(mCafeItemsDataList.get(i).getPrice()));
        Glide.with(mContext).load(mCafeItemsDataList.get(i).getImage()).into(menuViewHolder.menuImage);

    }

    @Override
    public int getItemCount() {
        return mCafeItemsDataList.size();
    }


}
