package edu.neu.madcourse.numad21s_yutinggan;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class RviewHolder extends RecyclerView.ViewHolder {
//    public ImageView itemIcon;
    public TextView title;
    public TextView url;

    public Button editButton;

    public RviewHolder(View itemView, final ItemClickListener listener) {
        super(itemView);
//        itemIcon = itemView.findViewById(R.id.item_icon);
//        title =  itemView.findViewById(R.id.item_title);
        url = itemView.findViewById(R.id.item_name);
//
        editButton = itemView.findViewById(R.id.editButton);


        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listener != null) {
                    int position = getLayoutPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onEditButtonClick(position);
                    }

                }
            }
        });

        url.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if (listener != null) {
                    int position = getLayoutPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onEditButtonClick(position);
                    }

                }
            }
        });



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


    }


}