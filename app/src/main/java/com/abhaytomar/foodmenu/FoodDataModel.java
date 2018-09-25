package com.abhaytomar.foodmenu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Abhay Tomar on 3/9/18.
 */
public class FoodDataModel {
    @SerializedName("Result")
    @Expose
    private List<CategoryDataModel> result = null;

    public List<CategoryDataModel> getResult() {
        return result;
    }

    public void setResult(List<CategoryDataModel> result) {
        this.result = result;
    }
}
