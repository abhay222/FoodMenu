package com.abhaytomar.foodmenu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Abhay Tomar on 3/9/18.
 */
class CategoryDataModel {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("data")
    @Expose
    private List<FoodItemModel> data = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<FoodItemModel> getData() {
        return data;
    }

    public void setData(List<FoodItemModel> data) {
        this.data = data;
    }
}
