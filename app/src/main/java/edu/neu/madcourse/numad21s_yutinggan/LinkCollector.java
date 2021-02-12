package edu.neu.madcourse.numad21s_yutinggan;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LinkCollector extends AppCompatActivity {
    //Creating the essential parts needed for a Recycler view to work: RecyclerView, Adapter, LayoutManager
    private ArrayList<ItemCard> itemList = new ArrayList<>();
    ;

    private RecyclerView recyclerView;
    private RviewAdapter rviewAdapter;
    private RecyclerView.LayoutManager rLayoutManger;
    private Button addButton;
    private TextView urlText;

    private static final String KEY_OF_INSTANCE = "KEY_OF_INSTANCE";
    private static final String NUMBER_OF_ITEMS = "NUMBER_OF_ITEMS";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_collector);

        init(savedInstanceState);

        urlText = findViewById(R.id.inputText);

        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = urlText.getText().toString();

                int pos = 0;
                itemList.add(pos, new ItemCard(R.drawable.empty, itemName));

                Toast.makeText(LinkCollector.this, "Add a URL", Toast.LENGTH_SHORT).show();
                urlText.setText("");

                rviewAdapter.notifyItemInserted(pos);
            }
        });

        //Specify what action a specific gesture performs, in this case swiping right or left deletes the entry
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                Toast.makeText(LinkCollector.this, "Delete an item", Toast.LENGTH_SHORT).show();
                int position = viewHolder.getLayoutPosition();
                itemList.remove(position);

                rviewAdapter.notifyItemRemoved(position);

            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }


    // Handling Orientation Changes on Android
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {


        int size = itemList == null ? 0 : itemList.size();
        outState.putInt(NUMBER_OF_ITEMS, size);

        // Need to generate unique key for each item
        // This is only a possible way to do, please find your own way to generate the key
        for (int i = 0; i < size; i++) {
            // put image information id into instance
            outState.putInt(KEY_OF_INSTANCE + i + "0", itemList.get(i).getImageSource());
            // put itemName information into instance
            outState.putString(KEY_OF_INSTANCE + i + "1", itemList.get(i).getItemName());
            // put isChecked information into instance

        }
        super.onSaveInstanceState(outState);

    }

    private void init(Bundle savedInstanceState) {

        initialItemData(savedInstanceState);
        createRecyclerView();
    }

    private void initialItemData(Bundle savedInstanceState) {

        // Not the first time to open this Activity
        if (savedInstanceState != null && savedInstanceState.containsKey(NUMBER_OF_ITEMS)) {
            if (itemList == null || itemList.size() == 0) {

                int size = savedInstanceState.getInt(NUMBER_OF_ITEMS);

                // Retrieve keys we stored in the instance
                for (int i = 0; i < size; i++) {
                    Integer imgId = savedInstanceState.getInt(KEY_OF_INSTANCE + i + "0");
                    String itemName = savedInstanceState.getString(KEY_OF_INSTANCE + i + "1");
                    boolean isChecked = savedInstanceState.getBoolean(KEY_OF_INSTANCE + i + "2");

                    // We need to make sure names such as "XXX(checked)" will not duplicate
                    // Use a tricky way to solve this problem, not the best though
                    if (isChecked) {
                        itemName = itemName.substring(0, itemName.lastIndexOf("("));
                    }
                    ItemCard itemCard = new ItemCard(imgId, itemName);

                    itemList.add(itemCard);
                }
            }
        }
        // The first time to open this Activity
//        else {
//            ItemCard item1 = new ItemCard(R.drawable.pic_gmail_01, "Gmail");
//            ItemCard item2 = new ItemCard(R.drawable.pic_google_01, "Google");
//            ItemCard item3 = new ItemCard(R.drawable.pic_youtube_01, "Youtube");
//            itemList.add(item1);
//            itemList.add(item2);
//            itemList.add(item3);
//        }

    }

    private void createRecyclerView() {
        rLayoutManger = new LinearLayoutManager(this);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        rviewAdapter = new RviewAdapter(itemList);
        ItemClickListener itemClickListener = new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //attributions bond to the item has been changed
                itemList.get(position).onItemClick(position);

                String url = itemList.get(position).getItemName();
                if (url.startsWith("https://") || url.startsWith("http://")) {
                    Uri uri = Uri.parse(url);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }else{
                    Toast.makeText(LinkCollector.this,"Invalid Url", Toast.LENGTH_SHORT).show();
                }

                rviewAdapter.notifyItemChanged(position);
            }

            @Override
            public void onCheckBoxClick(int position) {
                //attributions bond to the item has been changed
                itemList.get(position).onCheckBoxClick(position);

                String url = itemList.get(position).getItemName();
                if (url.startsWith("https://") || url.startsWith("http://")) {
                    Uri uri = Uri.parse(url);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }else{
                    Toast.makeText(LinkCollector.this, "Invalid Url", Toast.LENGTH_SHORT).show();
                }



                rviewAdapter.notifyItemChanged(position);
            }
        };
        rviewAdapter.setOnItemClickListener(itemClickListener);

        recyclerView.setAdapter(rviewAdapter);
        recyclerView.setLayoutManager(rLayoutManger);


    }

}
