package com.abhaytomar.foodmenu;

/**
 * Created by Abhay Tomar on 3/9/18.
 */
public class MetaDataList {
    private int index;
    private int categoryIndex;
    private int subCategoryIndex;
    private Boolean isCategory;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getCategoryIndex() {
        return categoryIndex;
    }

    public void setCategoryIndex(int categoryIndex) {
        this.categoryIndex = categoryIndex;
    }

    public int getSubCategoryIndex() {
        return subCategoryIndex;
    }

    public void setSubCategoryIndex(int subCategoryIndex) {
        this.subCategoryIndex = subCategoryIndex;
    }

    public Boolean getIsCategory() {
        return isCategory;
    }

    public void setIsCategory(Boolean category) {
        isCategory = category;
    }
}
