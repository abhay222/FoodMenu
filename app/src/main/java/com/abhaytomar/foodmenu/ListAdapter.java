package com.abhaytomar.foodmenu;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Abhay Tomar on 1/9/18.
 */
public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<CategoryDataModel> itemList;
    private List<MetaDataList> metaDataList;

    public ListAdapter(List<CategoryDataModel> itemList, List<MetaDataList> metaDataList) {
        this.itemList = itemList;
        this.metaDataList = metaDataList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (metaDataList.get(viewType).getIsCategory()){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_category, parent, false);
            return new CategoryViewHolder(view);
        }else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_food, parent, false);
            return new SubCategoryViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position ;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int categoryPosition = metaDataList.get(position).getCategoryIndex();

        if (metaDataList.get(position).getIsCategory()){
            ((CategoryViewHolder) holder).tvHeading.setText(itemList.get(categoryPosition).getTitle());
        }else {
            int subCategoryPosition = metaDataList.get(position).getSubCategoryIndex();

            ((SubCategoryViewHolder) holder).tvPrice.setText(itemList.get(categoryPosition).getData().get(subCategoryPosition).getPrice());
            ((SubCategoryViewHolder) holder).tvName.setText(itemList.get(categoryPosition).getData().get(subCategoryPosition).getDish());
        }
    }

    public class SubCategoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPrice;
        public SubCategoryViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.name);
            tvPrice = itemView.findViewById(R.id.price);
        }
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvHeading;
        public CategoryViewHolder(View itemView) {
            super(itemView);
            tvHeading = itemView.findViewById(R.id.category_title);
        }
    }

    @Override
    public int getItemCount() {
        return (null != metaDataList ? metaDataList.size() : 0);
    }
}
