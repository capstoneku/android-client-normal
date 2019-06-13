package kr.ac.korea.capstoneproject.fragment.Home;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import kr.ac.korea.capstoneproject.MainActivity;
import kr.ac.korea.capstoneproject.R;
import kr.ac.korea.capstoneproject.data.pojo.NearCafeResponse;
import kr.ac.korea.capstoneproject.fragment.CafePageFragment;
import kr.ac.korea.capstoneproject.utils.GPS;

public class HomeNearCafeAdapter extends RecyclerView.Adapter<HomeNearCafeAdapter.NearCafeViewHolder> {
    private Context mContext;
    private ArrayList<NearCafeResponse.Cafe> mDataset = new ArrayList<>();

    public class NearCafeViewHolder extends RecyclerView.ViewHolder{
        public LinearLayout cafeLayout;
        public CircleImageView cafeIv;
        public TextView cafeDistanceTv;
        public TextView cafeNameTv;
        private GPS mGPS;
        private Location mLocation;

        public NearCafeViewHolder(View v) {
            super(v);

            mGPS = new GPS(v.getContext());
            mLocation = mGPS.getLocation();

            cafeLayout = v.findViewById(R.id.layout_home_near_cafe);
            cafeIv = v.findViewById(R.id.iv_home_near_cafe_img);
            cafeDistanceTv = v.findViewById(R.id.tv_home_near_cafe_distance);
            cafeNameTv = v.findViewById(R.id.tv_home_near_cafe_name);
        }
    }

    public HomeNearCafeAdapter(Context context, ArrayList<NearCafeResponse.Cafe> mDataset) {
        this.mContext = context;
        this.mDataset.addAll(mDataset);
    }

    @NonNull
    @Override
    public HomeNearCafeAdapter.NearCafeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_fragment_item_near_cafe, parent, false);

        NearCafeViewHolder vh = new NearCafeViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull NearCafeViewHolder nearCafeViewHolder, final int i) {
        // glide로 이미지 적용
        Glide.with(mContext)
                .load(mDataset.get(i).getImg())
                .error(R.drawable.img_default_coffee_shop)
                .into(nearCafeViewHolder.cafeIv);
        // 거리 적용
        nearCafeViewHolder.cafeDistanceTv.setText(nearCafeViewHolder.mGPS.getDistance(mDataset.get(i).geometry.getCoordinates()[0], mDataset.get(i).geometry.getCoordinates()[1]) + "m"); // TODO: 2019-06-08 거리 구하는 로직 구현
        // 이름 적용
        nearCafeViewHolder.cafeNameTv.setText(mDataset.get(i).getName());
        // 클릭 이벤트 적용
        nearCafeViewHolder.cafeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = ((MainActivity)mContext).getSupportFragmentManager().beginTransaction();

                Bundle bundle = new Bundle();
                bundle.putString("cafeId", mDataset.get(i).getCafeId());

                Fragment fragment = new CafePageFragment();
                fragment.setArguments(bundle);

                transaction.replace(R.id.fragment_main, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}
