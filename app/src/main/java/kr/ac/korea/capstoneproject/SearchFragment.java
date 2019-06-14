package kr.ac.korea.capstoneproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

import kr.ac.korea.capstoneproject.data.pojo.CafeList;
import kr.ac.korea.capstoneproject.data.pojo.CafeListResponse;
import kr.ac.korea.capstoneproject.data.remote.RetrofitClient;
import kr.ac.korea.capstoneproject.data.remote.SearchListRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static kr.ac.korea.capstoneproject.data.remote.RetrofitClient.getInstance;

public class SearchFragment extends Fragment {
    private Retrofit mRetrofit;
    private SearchListRequest mSearchListRequest;
    //private ListView listview;
    private ArrayList<CafeList> cafeList;
    private CafeListAdapter adapter;
    private RecyclerView recyclerView;

    public SearchFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_search, null);

        initConnection();
        requestCafe();
        initView(view);

        return view;
    }

    /**
     * 네트워크 관련 객체 초기화 함수
     */
    private void initConnection() {
        mRetrofit = getInstance();
        mSearchListRequest = mRetrofit.create(SearchListRequest.class);
    }

    private void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CafeListAdapter();
        //recyclerView.setAdapter(adapter);
        /**
         *EditText 텍스트 변경 이벤트 처리
         *EditText(@id/searchCafe)를 통해 필터링할 텍스트 입력받은 다음, 필터링 수행
         */

        EditText editTextFilter = (EditText) view.findViewById(R.id.searchCafe) ;
        editTextFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable edit) {
                String filterText = edit.toString() ;
                ((CafeListAdapter)recyclerView.getAdapter()).getFilter().filter(filterText) ;
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        }) ;
    }

    private void requestCafe() {
        cafeList = new ArrayList<>();

        Call<CafeListResponse> call = mSearchListRequest.searchListRequest();
        call.enqueue(new Callback<CafeListResponse>() {
            @Override
            public void onResponse(Call<CafeListResponse> call, Response<CafeListResponse> response) {
                Log.d("TAG","Response = "+ cafeList);

                cafeList.addAll(response.body().getCafeList());
                adapter.CafeListAdapter(getContext(), cafeList);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<CafeListResponse> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });
    }

}
