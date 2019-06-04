package kr.ac.korea.capstoneproject.fragment.Home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import kr.ac.korea.capstoneproject.R;

public class HomeNearCafeAdapter extends RecyclerView.Adapter<HomeNearCafeAdapter.NearCafeViewHolder> {
    private Context mContext;
    private ArrayList<HomeNearCafeData> mDataset;

    public class NearCafeViewHolder extends RecyclerView.ViewHolder{
        public CircleImageView cafeIv;
        public TextView cafeDistanceTv;
        public TextView cafeNameTv;

        public NearCafeViewHolder(View v) {
            super(v);

            cafeIv = v.findViewById(R.id.iv_home_near_cafe_img);
            cafeDistanceTv = v.findViewById(R.id.tv_home_near_cafe_distance);
            cafeNameTv = v.findViewById(R.id.tv_home_near_cafe_name);
        }
    }

    public HomeNearCafeAdapter(Context context, ArrayList<HomeNearCafeData> mDataset) {
        this.mContext = context;
        this.mDataset = mDataset;
    }

    @NonNull
    @Override
    public NearCafeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_fragment_item_near_cafe, parent, false);

        NearCafeViewHolder vh = new NearCafeViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull NearCafeViewHolder nearCafeViewHolder, int i) {
        // glide로 이미지 적용
        Glide.with(mContext).load(mDataset.get(i).getCafeImgUrl()).into(nearCafeViewHolder.cafeIv);
        // 거리 적용

        // 이름 적용
        nearCafeViewHolder.cafeNameTv.setText(mDataset.get(i).getCafeName());
        // 클릭 이벤트 적용
    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
