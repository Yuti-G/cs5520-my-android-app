package edu.neu.madcourse.numad21s_yutinggan;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class RviewHolder extends RecyclerView.ViewHolder {
    public ImageView itemIcon;
    public TextView title;
    public TextView itemName;

    public Button editbutton;

    public RviewHolder(View itemView, final ItemClickListener listener) {
        super(itemView);
//        itemIcon = itemView.findViewById(R.id.item_icon);
//        title =  itemView.findViewById(R.id.item_title);
//        itemName = itemView.findViewById(R.id.item_name);
//
//        editbutton = itemView.findViewById(R.id.editButton);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    int position = getLayoutPosition();
                    if (position != RecyclerView.NO_POSITION) {

                        listener.onItemClick(position);
                    }
                }
            }
        });

        editbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listener != null) {
                    int position = getLayoutPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onCheckBoxClick(position);
                    }

                }
            }
        });
    }
}