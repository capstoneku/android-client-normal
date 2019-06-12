package kr.ac.korea.capstoneproject.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import kr.ac.korea.capstoneproject.CafeAdapter;
import kr.ac.korea.capstoneproject.R;
import kr.ac.korea.capstoneproject.data.pojo.CafeList;
import kr.ac.korea.capstoneproject.data.pojo.CafeListResponse;
import kr.ac.korea.capstoneproject.data.remote.CafeListRequest;
import kr.ac.korea.capstoneproject.data.remote.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static kr.ac.korea.capstoneproject.data.remote.RetrofitClient.getInstance;

public class SearchFragment extends Fragment {

    private Retrofit mRetrofit;
    private CafeListRequest mCafeListRequest;
    CafeAdapter cafeAdapter;
    RecyclerView recyclerView;
    List<CafeList> cafeList = new ArrayList<CafeList>();
    //private OnFragmentInteractionListener listener;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    private void initConnection(){
        mRetrofit = getInstance();
        mCafeListRequest = mRetrofit.create(CafeListRequest.class);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        initConnection();
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        //View view = inflater.inflate(R.layout.fragment_search, null);
        EditText editText = view.findViewById(R.id.searchCafe);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());

            }
        });

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        cafeAdapter = new CafeAdapter(cafeList, getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(cafeAdapter);
        getCafeList();
        return view;
    }
    private void filter(String text){
        ArrayList<CafeList> filteredList = new ArrayList<>();
        for (CafeList item : cafeList){
            if(item.getName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        cafeAdapter.filterList(filteredList);
    }

    public void getCafeList() {
        CafeListRequest apiInterface = RetrofitClient.getInstance().create(CafeListRequest.class);
        Call<CafeListResponse> call = apiInterface.getCafeList();
        call.enqueue(new Callback<CafeListResponse>() {
            @Override
            public void onResponse( Call<CafeListResponse> call, Response<CafeListResponse> response) {

                if (response==null){
                    Toast.makeText(getActivity(), "Somthing Went Wrong...!!", Toast.LENGTH_SHORT).show();
                }else{
                    for (CafeList cafe:response.body().getCafeList()){
                        cafeList.add(cafe);
                    }
                    Log.i("RESPONSE: ", ""+response.toString());
                }
                cafeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<CafeListResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Unable to fetch json: " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }


/**
 public interface OnFragmentInteractionListener {
 }
 */
}