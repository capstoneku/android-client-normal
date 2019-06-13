package kr.ac.korea.capstoneproject.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import kr.ac.korea.capstoneproject.OrderActivity;
import kr.ac.korea.capstoneproject.R;
import kr.ac.korea.capstoneproject.data.pojo.CafeData;
import kr.ac.korea.capstoneproject.data.pojo.CafeItemsData;
import kr.ac.korea.capstoneproject.data.pojo.CafePageResponse;
import kr.ac.korea.capstoneproject.data.pojo.CafeItemsResponse;
import kr.ac.korea.capstoneproject.data.remote.CafeItemsRequest;
import kr.ac.korea.capstoneproject.data.remote.CafePageRequest;
import kr.ac.korea.capstoneproject.recyclerview.MenuAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static kr.ac.korea.capstoneproject.data.remote.RetrofitClient.getInstance;

public class CafePageFragment extends Fragment {
    private Retrofit mRetrofit;
    private CafePageRequest mCafePageRequest;
    private CafeItemsRequest mCafeItemsRequest;
    private Button mCafePageBtn;
    private TextView cafeShop;
    private TextView cafeTogo;
    private TextView cafeName;
    private TextView cafeAddress;
    private TextView cafeTel;
    private TextView cafeMenu;
    private ImageView cafeLogo;
    private TextView cafeCongestion;
    private RecyclerView cafeItems;
    private ImageView menuImage;
    private TextView menuName;
    private TextView menuPrice;
    private MenuAdapter adapter;
    private List<CafeItemsData> cafeItemsDataList;

    private String mCafeId;


    public CafePageFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        initConnectionAndGetCafeId();

        // Inflate the layout for this fragment
        View cafeInfoView = inflater.inflate(R.layout.fragment_cafe_page, null);
        View cafeMenuView = inflater.inflate(R.layout.menu_item, null);

        initView(cafeInfoView, cafeMenuView);

        requestCafeInfo();
        requestCafeMenu();

        return cafeInfoView;
    }

    private void initConnectionAndGetCafeId() {
        mRetrofit = getInstance();
        mCafePageRequest = mRetrofit.create(CafePageRequest.class);
        mCafeItemsRequest = mRetrofit.create(CafeItemsRequest.class);

        if (getArguments() != null) {
            mCafeId = getArguments().getString("cafeId");
        }
    }

    private void initView(View cafeInfoView, View cafeMenuView) {
        mCafePageBtn = cafeMenuView.findViewById(R.id.button_order);
        cafeShop = cafeInfoView.findViewById(R.id.cafe_shop);
        cafeTogo = cafeInfoView.findViewById(R.id.cafe_togo);
        cafeName = cafeInfoView.findViewById(R.id.cafe_name);
        cafeAddress = cafeInfoView.findViewById(R.id.cafe_address);
        cafeTel = cafeInfoView.findViewById(R.id.cafe_tel);
        cafeLogo = cafeInfoView.findViewById(R.id.cafe_logo);
        cafeCongestion = cafeInfoView.findViewById(R.id.cafe_cngst_degree);
        cafeItems = cafeInfoView.findViewById(R.id.cafe_menu);
        menuImage = cafeMenuView.findViewById(R.id.menu_image);
        menuName = cafeMenuView.findViewById(R.id.menu_name);
        menuPrice = cafeMenuView.findViewById(R.id.menu_price);

        cafeItemsDataList = new ArrayList<>();

        adapter = new MenuAdapter(cafeItemsDataList, getActivity());

        cafeItems.setLayoutManager(new LinearLayoutManager(getActivity()));
        cafeItems.setItemAnimator(new DefaultItemAnimator());
        cafeItems.addItemDecoration(new DividerItemDecoration(getActivity(),LinearLayoutManager.VERTICAL));
        cafeItems.setAdapter(adapter);

        mCafePageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OrderActivity.class);
                startActivity(intent);
            }

        });
    }


    private void requestCafeInfo() {
        Call<CafePageResponse> call = mCafePageRequest.cafePageRequest(mCafeId);
        call.enqueue(new Callback<CafePageResponse>() {
            @Override
            public void onResponse(Call<CafePageResponse> call, Response<CafePageResponse> response) {
                Log.d("chenny", "onResponse: " + response.body().success);

                if (response.body().success == true) {
                    CafeData cafeData = response.body().data;

                    //매장 이용가능 여부
                    if (cafeData.getOptions().shop == true){
                        cafeShop.setText("매장 이용 가능, ");
                    } else {
                        cafeShop.setText("매장 이용 불가능, ");
                    }

                    // 테이크아웃 가능 여부
                    if (cafeData.getOptions().togo == true){
                        cafeTogo.setText("Takeout 가능");
                    } else {
                        cafeTogo.setText("Takeout 불가능");
                    }

                    cafeName.setText(cafeData.getName());
                    cafeAddress.setText(cafeData.getAddress());
                    cafeTel.setText(cafeData.getTel());

                    //카페 혼잡도
                    if (cafeData.getCongestion() < 0 | cafeData.getCongestion() >1){
                        cafeCongestion.setText("Error");
                    } else if (cafeData.getCongestion() < 0.4){
                        cafeCongestion.setText("여유");
                    } else if (cafeData.getCongestion() < 0.6){
                        cafeCongestion.setText("보통");
                    } else if (cafeData.getCongestion() < 0.9){
                        cafeCongestion.setText("혼잡");
                    } else if (cafeData.getCongestion() <= 1){
                        cafeCongestion.setText("매우 혼잡");
                    }

                    //카페 로고 이미지
                    Glide.with(CafePageFragment.this).load(cafeData.getProfileImg()).into(cafeLogo);
                }
            }

            @Override
            public void onFailure(Call<CafePageResponse> call, Throwable t) {
                Log.d("chenny", "onFailure: " + t.toString());
            }
        });

    }

    private void requestCafeMenu() {
        Call<CafeItemsResponse> call = mCafeItemsRequest.cafeItemsRequest(mCafeId);
        call.enqueue(new Callback<CafeItemsResponse>() {
            @Override
            public void onResponse(Call<CafeItemsResponse> call, Response<CafeItemsResponse> response) {
                Log.d("chenny", "onResponse: " + response.body().success);

                if (response.body().success == true) {

                    for (CafeItemsData cafe : response.body().getCafeItemsData()){
                        cafeItemsDataList.add(cafe);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<CafeItemsResponse> call, Throwable t) {
                Log.d("chenny", "onFailure: " + t.toString());

            }
        });

    }

}