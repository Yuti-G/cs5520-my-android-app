package edu.neu.madcourse.numad21s_yutinggan;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class LinkCollector extends AppCompatActivity {
    //Creating the essential parts needed for a Recycler view to work: RecyclerView, Adapter, LayoutManager
    private ArrayList<ItemCard> itemList = new ArrayList<>();


    private RecyclerView recyclerView;
    private RviewAdapter rviewAdapter;
    private RecyclerView.LayoutManager rLayoutManger;
    private FloatingActionButton addButton;
    private Button editButton;


    private static final String KEY_OF_INSTANCE = "KEY_OF_INSTANCE";
    private static final String NUMBER_OF_ITEMS = "NUMBER_OF_ITEMS";

    public static final String KEY_Title_TEXT = "item_title";
    public static final String KEY_URL_TEXT = "item_name";
    public static final String KEY_ITEM_POSITION = "item_position";
    public static final int EDIT_TEXT_CODE = 20;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_collector);

        init(savedInstanceState);


        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = 0;
                addItem(pos);
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
//            outState.putInt(KEY_OF_INSTANCE + i + "0", itemList.get(i).getImageSource());
            // put title information into instance
            outState.putString(KEY_OF_INSTANCE + i + "0", itemList.get(i).getTitle());
            // put url information into instance
            outState.putString(KEY_OF_INSTANCE + i + "1", itemList.get(i).getItemName());

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
//                    Integer imgId = savedInstanceState.getInt(KEY_OF_INSTANCE + i + "0");
                    String title = savedInstanceState.getString(KEY_OF_INSTANCE + i + "0");
                    String itemName = savedInstanceState.getString(KEY_OF_INSTANCE + i + "1");


                    // We need to make sure names such as "XXX(checked)" will not duplicate
                    // Use a tricky way to solve this problem, not the best though

                    ItemCard itemCard = new ItemCard(title, itemName);

                    itemList.add(itemCard);
                }
            }
        }
//        else {
//            ItemCard item1 = new ItemCard("Gmail", "http://");
//            ItemCard item2 = new ItemCard("Google", "http://");
//            ItemCard item3 = new ItemCard("Youtube", "http://");
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
            public void onEditButtonClick(int position) {
                //attributions bond to the item has been changed
                itemList.get(position).onItemClick(position);

                Intent i = new Intent(LinkCollector.this, EditActivity.class);

                i.putExtra(KEY_Title_TEXT, itemList.get(position).getTitle());
                i.putExtra(KEY_URL_TEXT, itemList.get(position).getItemName());
                i.putExtra(KEY_ITEM_POSITION, position);

                startActivityForResult(i, EDIT_TEXT_CODE);

                rviewAdapter.notifyItemChanged(position);
            }
        };


        rviewAdapter.setOnItemClickListener(itemClickListener);

        recyclerView.setAdapter(rviewAdapter);
        recyclerView.setLayoutManager(rLayoutManger);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK  && requestCode == EDIT_TEXT_CODE) {
            String title = data.getStringExtra(KEY_Title_TEXT);
            String url = data.getStringExtra(KEY_URL_TEXT);
            int position = data.getExtras().getInt(KEY_ITEM_POSITION);

            ItemCard cur = new ItemCard(title, url);

            itemList.set(position, cur);
            rviewAdapter.notifyItemChanged(position);

            Toast.makeText(getApplicationContext(), "Add a URL", Toast.LENGTH_SHORT).show();


        } else {
            Log.w("LinkCollector", "Unknown call to on ActivityResult");
        }
    }

    private void addItem(int position) {
        itemList.add(position, new ItemCard( "", ""));
        Toast.makeText(LinkCollector.this, "Add an item", Toast.LENGTH_SHORT).show();

        rviewAdapter.notifyItemInserted(position);
    }

}
