package kr.ac.korea.capstoneproject.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import kr.ac.korea.capstoneproject.R;

public class MenuViewHolder extends RecyclerView.ViewHolder{

    public ImageView menuImage;
    public TextView menuName, menuPrice;

    public MenuViewHolder(@NonNull View itemView) {
        super(itemView);

        menuImage = itemView.findViewById(R.id.menu_image);
        menuName = itemView.findViewById(R.id.menu_name);
        menuPrice = itemView.findViewById(R.id.menu_price);
    }
}
