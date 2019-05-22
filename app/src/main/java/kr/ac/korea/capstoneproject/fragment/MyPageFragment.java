package kr.ac.korea.capstoneproject.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import kr.ac.korea.capstoneproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyPageFragment extends Fragment {
    TextView mUserNameTv;
    TextView mUserMailTv;
    String mUserName;
    String mUserMail;


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
    }

}
