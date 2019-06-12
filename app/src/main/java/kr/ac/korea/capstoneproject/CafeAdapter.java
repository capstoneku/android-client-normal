package kr.ac.korea.capstoneproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import kr.ac.korea.capstoneproject.data.pojo.CafeList;


/**
 * Created by RIFAN on 22-Mar-17.
 */

public class CafeAdapter extends RecyclerView.Adapter<CafeAdapter.MyViewHolder> {

    List<CafeList> cafeList;
    Context context;

    public CafeAdapter(List<CafeList> cafeList, Context context) {
        this.cafeList = cafeList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CafeList cafe = cafeList.get(position);

        holder.cafeName.setText(cafe.getName());
        holder.rating.setText(cafe.getRating().toString());
        holder.sigMenu.setText(cafe.getSignatures().toString());
        Glide.with(context)
                .load(cafe.getImg())
                .centerCrop()
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return cafeList.size();
    }

    public void filterList(ArrayList<CafeList> filteredList){
        cafeList = filteredList;
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView cafeName;
        public TextView rating;
        public TextView sigMenu;
        public ImageView imageView;


        public MyViewHolder(View view) {
            super(view);
            cafeName = (TextView) view.findViewById(R.id.cafeName);
            rating = (TextView) view.findViewById(R.id.rating);
            sigMenu = (TextView) view.findViewById(R.id.sigMenu);
            imageView = (ImageView) view.findViewById(R.id.imageView);
        }
    }

}