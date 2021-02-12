package edu.neu.madcourse.numad21s_yutinggan;

public class ItemCard implements ItemClickListener {

    private final int imageSource;
    private final String itemName;
    private boolean isChecked;

    //Constructor
    public ItemCard(int imageSource, String itemName,boolean isChecked) {
        this.imageSource = imageSource;
        this.itemName = itemName;
        this.isChecked = isChecked;
    }

    //Getters for the imageSource, itemName and itemDesc
    public int getImageSource() {
        return imageSource;
    }


    public String getItemName() {
        return itemName;
    }

    public boolean getStatus() {
        return isChecked;
    }


    @Override
    public void onItemClick(int position) {
        isChecked = !isChecked;
    }

    @Override
    public void onCheckBoxClick(int position) {
        isChecked = !isChecked;
    }

}