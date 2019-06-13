package kr.ac.korea.capstoneproject.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import kr.ac.korea.capstoneproject.CouponListAdapater;
import kr.ac.korea.capstoneproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CouponFragment extends Fragment {
    ListView couponListView = null;


    public CouponFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_coupon, container, false);

        CouponListAdapater adapter;
        adapter = new CouponListAdapater();

        couponListView = (ListView) view.findViewById(R.id.listview_coupon);
        couponListView.setAdapter(adapter);

        adapter.addItem(ContextCompat.getDrawable(getActivity(),R.drawable.mammoth),
                "매머드커피", ContextCompat.getDrawable(getActivity(),R.drawable.coupon333));
        adapter.addItem(ContextCompat.getDrawable(getActivity(),R.drawable.ftgram),
                "14gram", ContextCompat.getDrawable(getActivity(),R.drawable.coupon419));
        adapter.addItem(ContextCompat.getDrawable(getActivity(),R.drawable.soulkit),
                "소울키친", ContextCompat.getDrawable(getActivity(),R.drawable.coupon418));
        adapter.addItem(ContextCompat.getDrawable(getActivity(),R.drawable.ninehertz),
                "9hertz", ContextCompat.getDrawable(getActivity(),R.drawable.coupon420));
        adapter.addItem(ContextCompat.getDrawable(getActivity(),R.drawable.tousles),
                "뚜레쥬르 카페 안암역점", ContextCompat.getDrawable(getActivity(),R.drawable.coupon311));
        adapter.addItem(ContextCompat.getDrawable(getActivity(),R.drawable.tomtom),
                "탐앤탐스 고대점", ContextCompat.getDrawable(getActivity(),R.drawable.coupon419));
        adapter.addItem(ContextCompat.getDrawable(getActivity(),R.drawable.ding),
                "만화카페 딩굴", ContextCompat.getDrawable(getActivity(),R.drawable.coupon420));
        // Inflate the layout for this fragment

        couponListView = (ListView) view.findViewById(R.id.listview_coupon);
        couponListView.setAdapter(adapter);
        return view;
    }

}
