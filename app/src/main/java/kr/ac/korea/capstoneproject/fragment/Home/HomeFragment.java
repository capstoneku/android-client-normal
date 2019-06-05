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
import android.widget.Toast;

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

    public ArrayList<NearCafeResponse.Cafe> mDataset = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initConnection();
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

    private void initRecyclerView(View view) {
        mRecyclerView = view.findViewById(R.id.rv_home_near_cafe);
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        getNearCafeData(getContext(), new getNearCafeDataCallback() {
            @Override
            public void onSuccess(ArrayList<NearCafeResponse.Cafe> mNearCafeDataset) {
                mDataset.addAll(mNearCafeDataset);
                mAdapter = new HomeNearCafeAdapter(getContext(), mDataset);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onError() {

            }
        });

//        if (mDataset.size() != 0) {
//            mAdapter = new HomeNearCafeAdapter(getContext(), mDataset);
//            mRecyclerView.setAdapter(mAdapter);
//        } else {
//            // TODO: 2019-06-05 근처 카페가 없을 경우 정의.
//        }
    }

    private void getNearCafeData(Context context, final getNearCafeDataCallback callback) {
//        final ArrayList<NearCafeResponse.Cafe> nearCafeList = new ArrayList<>();

        GPS gps = new GPS(context);
        Location location = gps.getLocation();

        NearCafeData nearCafeData = new NearCafeData(location.getLongitude(), location.getLatitude());

        Call<NearCafeResponse> call = mGetNearCafeRequest.getNearCafeRequest(nearCafeData);
        call.enqueue(new Callback<NearCafeResponse>() {
            @Override
            public void onResponse(Call<NearCafeResponse> call, Response<NearCafeResponse> response) {
                if(response.body().data.size() != 0) {
                    Log.d("zzanzu", "onResponse: " + response.body().getData().get(0).getName());
//                    nearCafeList.addAll(response.body().data);
                    callback.onSuccess(response.body().data);
                } else {
                    Toast.makeText(getContext(), "주변에 카페가 없습니다.", Toast.LENGTH_LONG).show();
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
