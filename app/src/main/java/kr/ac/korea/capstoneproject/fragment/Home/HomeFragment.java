package kr.ac.korea.capstoneproject.fragment.Home;


import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import kr.ac.korea.capstoneproject.R;
import kr.ac.korea.capstoneproject.data.pojo.NearCafeData;
import kr.ac.korea.capstoneproject.data.pojo.NearCafeResponse;
import kr.ac.korea.capstoneproject.data.remote.GetNearCafeRequest;
import kr.ac.korea.capstoneproject.utils.GPS;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static kr.ac.korea.capstoneproject.data.remote.RetrofitClient.getInstance;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private Retrofit mRetrofit;
    private GetNearCafeRequest mGetNearCafeRequest;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private LinearLayout mLinearLayout;

    public ArrayList<NearCafeResponse.Cafe> mDataset = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initConnection();
        initView(view);
        initRecyclerView(view);

        return view;
    }

    /**
     * 네트워크 관련 객체 초기화 함수
     */
    private void initConnection() {
        mRetrofit = getInstance();
        mGetNearCafeRequest = mRetrofit.create(GetNearCafeRequest.class);

    }

    /**
     * 홈 프래그먼트 뷰 초기화
     *
     * 주변 카페 검색 버튼 클릭시, 주변 카페가 없을경우 '근처 카페가 없습니다' 레이아웃을 숨김.
     *
     * @param view
     */
    private void initView(final View view) {
        mLinearLayout = view.findViewById(R.id.layout_no_home_near_cafe);
        ImageView myLocationIv = view.findViewById(R.id.iv_home_my_location);

        myLocationIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initRecyclerView(view);
            }
        });
    }

    /**
     * 카페 리스트 Recycler View 를 표시한
     *
     * @param view
     */
    private void initRecyclerView(View view) {
        mRecyclerView = view.findViewById(R.id.rv_home_near_cafe);
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        getNearCafeData(getContext(), new getNearCafeDataCallback() {
            @Override
            public void onSuccess(ArrayList<NearCafeResponse.Cafe> mNearCafeDataset) { // 주변에 카페가 있을경우
                mDataset.addAll(mNearCafeDataset);
                mAdapter = new HomeNearCafeAdapter(getContext(), mDataset);

                mLinearLayout.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);

                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onError() { // 주변에 카페가 없을경우
                mRecyclerView.setVisibility(View.GONE);
                mLinearLayout.setVisibility(View.VISIBLE);
            }
        });


    }

    /**
     * 주변의 카페 리스트를 서버로부터 가져온다.
     * 카페 리스트를 가져와서 콜백 함수에 전달, 리스트를 모두 가져왔을 경우 콜백 함수가 실행된다.
     *
     * @param context
     * @param callback
     */
    private void getNearCafeData(Context context, final getNearCafeDataCallback callback) {
        GPS gps = new GPS(context);
        Location location = gps.getLocation();

        NearCafeData nearCafeData = new NearCafeData(location.getLongitude(), location.getLatitude());

        Call<NearCafeResponse> call = mGetNearCafeRequest.getNearCafeRequest(nearCafeData);
        call.enqueue(new Callback<NearCafeResponse>() {
            @Override
            public void onResponse(Call<NearCafeResponse> call, Response<NearCafeResponse> response) {
                if(response.body().data.size() != 0) {
                    callback.onSuccess(response.body().data);
                } else {
                    callback.onError();
                }
            }

            @Override
            public void onFailure(Call<NearCafeResponse> call, Throwable t) {
                Log.d("zzanzu", "onFailure: " + t.toString());
            }
        });

//        return nearCafeList;
    }

    private interface getNearCafeDataCallback {
        void onSuccess(ArrayList<NearCafeResponse.Cafe> mNearCafeDataset);
        void onError();
    }
}
