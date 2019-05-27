package kr.ac.korea.capstoneproject.fragment.MyPage;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import kr.ac.korea.capstoneproject.R;


public class MyPageFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private TextView mUserNameTv;
    private TextView mUserMailTv;
    private String mUserName;
    private String mUserMail;

    private String[] mDataset;

    public MyPageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_page, container, false);

        initView(view);

        return view;
    }

    /**
     * 마이페이지의 화면을 초기화한다.
     *
     * @author Chanjoo Lee
     * @version 0.1
     * @param view : 마이페이지 프래그먼트
     */
    private void initView(View view) {
        mUserNameTv = view.findViewById(R.id.tv_user_name);
        mUserMailTv = view.findViewById(R.id.tv_user_mail);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        mUserName = sharedPreferences.getString("nickname", "none");
        mUserMail = sharedPreferences.getString("email", "none");

        if (mUserName != "none" && mUserMail != "none") {
            mUserNameTv.setText(mUserName + " 님");
            mUserMailTv.setText(mUserMail);
        }

        initRecyclerView(view);
    }

    /**
     * 마이 페이지의 메뉴를 표시한다.
     *
     * @author Chanjoo Lee
     * @version 0.1
     * @param view : 마이 페이지 프래그먼트
     */
    private void initRecyclerView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_my_page);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mDataset = new String[]{"현재 위치 수정", "결제수단 변경", "My Cafe", "My Menu", "My Review", "환경설정"};

        mAdapter = new MyPageAdapter(mDataset);
        mRecyclerView.setAdapter(mAdapter);

        // row마다 수평선 삽입
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                LinearLayoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(dividerItemDecoration);
    }

}
