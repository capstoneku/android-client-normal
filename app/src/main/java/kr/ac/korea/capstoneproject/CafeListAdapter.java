package kr.ac.korea.capstoneproject;


import android.content.Context;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import kr.ac.korea.capstoneproject.data.pojo.CafeList;

public class CafeListAdapter extends RecyclerView.Adapter<CafeListAdapter.MyViewHolder> implements Filterable {
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList. (원본 데이터 리스트)
    private Context context;
    private ArrayList<CafeList> cafeList;
    // 필터링된 결과 데이터를 저장하기 위한 ArrayList. 최초에는 전체 리스트 보유.
    private ArrayList<CafeList> cafeListFiltered;

    public void CafeListAdapter(Context context,final ArrayList<CafeList> cafeList){
        this.context = context;
        if(this.cafeList == null){
            this.cafeList = cafeList;
            this.cafeListFiltered = cafeList;
            notifyItemChanged(0, cafeListFiltered.size());
        } else {
            final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return CafeListAdapter.this.cafeList.size();
                }

                @Override
                public int getNewListSize() {
                    return cafeList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return CafeListAdapter.this.cafeList.get(oldItemPosition).getName() == cafeList.get(newItemPosition).getName();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {

                    CafeList newCafe = CafeListAdapter.this.cafeList.get(oldItemPosition);

                    CafeList oldCafe = cafeList.get(newItemPosition);

                    return newCafe.getName() == oldCafe.getName() ;
                }
            });
            this.cafeList = cafeList;
            this.cafeListFiltered = cafeList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public CafeListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CafeListAdapter.MyViewHolder holder, int position) {
        holder.textName.setText(cafeListFiltered.get(position).getName());
//        holder.imageView.setImageDrawable(cafeListFiltered.get(position).getProfileImg());
        holder.textRev.setText(cafeListFiltered.get(position).getReviews());
        holder.textRat.setText(cafeListFiltered.get(position).getRating()[0]);
        holder.textMenu.setText(cafeListFiltered.get(position).getSignatures()[0]);
    }

    @Override
    public int getItemCount() {

        if(cafeList != null){
            return cafeListFiltered.size();
        } else {
            return 0;
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    cafeListFiltered = cafeList;
                } else {
                    ArrayList<CafeList> filteredList = new ArrayList<>();
                    for (CafeList cafe : cafeList) {
                        if (cafe.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(cafe);
                        }
                    }
                    cafeListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = cafeListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                cafeListFiltered = (ArrayList<CafeList>) filterResults.values;

                notifyDataSetChanged();
            }
        };
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textName;
        ImageView imageView;
        TextView  textRev;
        TextView  textRat;
        TextView  textMenu;

        public MyViewHolder(View view) {
            super(view);
            textName = (TextView) view.findViewById(R.id.textName);
            imageView = (ImageView)view.findViewById(R.id.imageView);
            textRat = (TextView) view.findViewById(R.id.textRat);
            textRev = (TextView) view.findViewById(R.id.textRev);
            textMenu = (TextView) view.findViewById(R.id.textMenu);

        }
    }
}

