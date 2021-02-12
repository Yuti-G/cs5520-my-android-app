package edu.neu.madcourse.numad21s_yutinggan;

public class ItemCard implements ItemClickListener {

    private final int imageSource;
    private final String url;


    //Constructor
    public ItemCard(int imageSource, String url) {
        this.imageSource = imageSource;
        this.url = url;

    }

    //Getters for the imageSource, itemName and itemDesc
    public int getImageSource() {
        return imageSource;
    }


    public String getItemName() {
        return url;
    }


    @Override
    public void onItemClick(int position) {


    }

    @Override
    public void onCheckBoxClick(int position) {


    }

}