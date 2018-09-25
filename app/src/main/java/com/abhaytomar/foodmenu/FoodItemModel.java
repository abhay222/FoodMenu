package com.abhaytomar.foodmenu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Abhay Tomar on 3/9/18.
 */
class FoodItemModel {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("dish")
    @Expose
    private String dish;
    @SerializedName("price")
    @Expose
    private String price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
