package kr.ac.korea.capstoneproject.fragment.MyPage;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import kr.ac.korea.capstoneproject.R;

public class MyPageAdapter extends RecyclerView.Adapter<MyPageAdapter.MyPageViewHolder> {
    private String[] mDataset;

    public class MyPageViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public MyPageViewHolder(View v) {
            super(v);

            textView = (TextView) v.findViewById(R.id.tv_my_page_row);
        }
    }

    MyPageAdapter(String[] mDataset) {
        this.mDataset = mDataset;
    }

    @NonNull
    @Override
    public MyPageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_my_page, parent, false);

        MyPageViewHolder vh = new MyPageViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyPageAdapter.MyPageViewHolder myPageViewHolder, final int position) {
        myPageViewHolder.textView.setText(mDataset[position]);

        myPageViewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), mDataset[position], Toast.LENGTH_LONG).show();

                // TODO: 2019-05-27 메뉴 클릭시 액티비티 전환 구현
//                switch (position) {
//                    case 0 :
//                        break;
//                    case 1 :
//                        break;
//                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
