package edu.neu.madcourse.numad21s_yutinggan;

public class ItemCard implements ItemClickListener {

//    private final int imageSource;
    private String title;
    private String url;


    //Constructor
    public ItemCard(String title, String url) {
//        this.imageSource = imageSource;
        this.title = title;
        this.url = url;

    }

    //Getters for the imageSource, itemName and itemDesc
//    public int getImageSource() {
//        return imageSource;
//    }

    public String getTitle() {return title; }


    public String getItemName() {
        return url;
    }





    @Override
    public void onItemClick(int position) {


    }

    @Override
    public void onEditButtonClick(int position) {

    }


}