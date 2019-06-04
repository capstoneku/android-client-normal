package kr.ac.korea.capstoneproject.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.util.List;

import kr.ac.korea.capstoneproject.OrderActivity;
import kr.ac.korea.capstoneproject.R;
import kr.ac.korea.capstoneproject.data.pojo.CafeData;
import kr.ac.korea.capstoneproject.data.pojo.CafePageResponse;
import kr.ac.korea.capstoneproject.data.remote.CafePageRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static kr.ac.korea.capstoneproject.data.remote.RetrofitClient.getInstance;

public class CafePageFragment extends Fragment {
    private Retrofit mRetrofit;
    private CafePageRequest mCafePageRequest;
    private Button mCafePageBtn;
    private TextView cafeShop;
    private TextView cafeTogo;
    private TextView cafeName;
    private TextView cafeAddress;
    private TextView cafeTel;
    private TextView cafeMenu;
    private ImageView cafeLogo;


    public CafePageFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        initConnection();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cafe_page, null);

        mCafePageBtn = view.findViewById(R.id.button_order);
        cafeShop = view.findViewById(R.id.cafe_shop);
        cafeTogo = view.findViewById(R.id.cafe_togo);
        cafeName = view.findViewById(R.id.cafe_name);
        cafeAddress = view.findViewById(R.id.cafe_address);
        cafeTel = view.findViewById(R.id.cafe_tel);
        cafeMenu = view.findViewById(R.id.menu_name);
        cafeLogo = view.findViewById(R.id.cafe_logo);

        initView();

        mCafePageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(getActivity(), OrderActivity.class);
            startActivity(intent);
            }

        });


        return view;
    }




    private void initConnection() {
        mRetrofit = getInstance();
        mCafePageRequest = mRetrofit.create(CafePageRequest.class);
    }



    private void initView() {
        Call<CafePageResponse> call = mCafePageRequest.cafePageRequest();
        call.enqueue(new Callback<CafePageResponse>() {
            @Override
            public void onResponse(Call<CafePageResponse> call, Response<CafePageResponse> response) {
                Log.d("chenny", "onResponse: " + response.body().success);

                if (response.body().success == true) {
                    CafeData cafeData = response.body().data;

                    //매장 이용가능 여부
                    if(cafeData.getOptions().shop == true){
                        cafeShop.setText("매장 이용 가능, ");
                    }
                    else
                        cafeShop.setText("매장 이용 불가능, ");

                    // 테이크아웃 가능 여부
                    if(cafeData.getOptions().togo == true){
                        cafeTogo.setText("Takeout 가능");
                    }
                    else
                        cafeTogo.setText("Takeout 불가능");

                    cafeName.setText(cafeData.getName());
                    cafeAddress.setText(cafeData.getAddress());
                    cafeTel.setText(cafeData.getTel());

                    //카페 로고 이미지
                    Glide.with(CafePageFragment.this).load(cafeData.getProfileImg()).into(cafeLogo);

                    //카페 메뉴 리스트 (임시)
                    List<String> itemIds = cafeData.getItemIds();
                    cafeMenu.setText(itemIds.toString());

// replace로        Intent intent = new Intent(getContext(), CafePageFragment.class);
//                  startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<CafePageResponse> call, Throwable t) {
                Log.d("chenny", "onFailure: " + t.toString());
            }
        });

    }
}