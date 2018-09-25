package com.abhaytomar.foodmenu;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Abhay Tomar on 3/9/18.
 */
public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<CategoryDataModel> itemList;
    private OnCategoryClickListener onCategoryClickListener;

    public CategoryAdapter(List<CategoryDataModel> itemList, OnCategoryClickListener onCategoryClickListener) {
        this.itemList = itemList;
        this.onCategoryClickListener = onCategoryClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_header_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((CategoryViewHolder) holder).tvHeading.setText(itemList.get(position).getTitle());
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvHeading;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvHeading = itemView.findViewById(R.id.category_name);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            onCategoryClickListener.onCategoryClicked(position);
        }
    }

    @Override
    public int getItemCount() {
        return (null != itemList ? itemList.size() : 0);
    }
}