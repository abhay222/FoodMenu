package com.abhaytomar.foodmenu;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    FoodDataModel foodDataModel;
    List<CategoryDataModel> categoryDataModelList;
    RecyclerView recyclerView, recyclerViewCategories;
    private Context context;
    private List<MetaDataList> metaDataList;
    private LinearLayoutManager linearLayoutManager;
    private LinearLayoutManager linearLayoutManagerCategories;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        recyclerView = findViewById(R.id.recyclerView);
        recyclerViewCategories = findViewById(R.id.recycler_categories);

        linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManagerCategories = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);

        recyclerViewCategories.setLayoutManager(linearLayoutManagerCategories);
        recyclerView.setLayoutManager(linearLayoutManager);

        String json = null;
        try {
            InputStream is = getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        foodDataModel = new Gson().fromJson(json, FoodDataModel.class);
        Log.e(TAG, "onCreate: " + foodDataModel.getResult().size());
        categoryDataModelList = foodDataModel.getResult();

        metaDataList = getMetaList(categoryDataModelList);
        CategoryAdapter categoryAdapter = new CategoryAdapter(categoryDataModelList, onCategoryClickListener);
        recyclerViewCategories.setAdapter(categoryAdapter);


        final ListAdapter foodAdapter = new ListAdapter(categoryDataModelList, metaDataList);

        recyclerView.setAdapter(foodAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int firstItem = linearLayoutManager.findFirstVisibleItemPosition();
                Log.e(TAG, "onScrolled: " + metaDataList.get(firstItem).getIsCategory());
                recyclerViewCategories.getLayoutManager().scrollToPosition(metaDataList.get(firstItem).getCategoryIndex());
            }
        });
    }

    OnCategoryClickListener onCategoryClickListener = new OnCategoryClickListener() {
        @Override
        public void onCategoryClicked(int position) {
            int indexOfCategoryInMeta = getCategoryIndexInMetaIndex(position, metaDataList);
            linearLayoutManager.scrollToPositionWithOffset(indexOfCategoryInMeta,0);
        }
    };

    private int getCategoryIndexInMetaIndex(int categoryIndex, List<MetaDataList> metaDataList) {
        for (MetaDataList dataList : metaDataList){
            if (dataList.getCategoryIndex() == categoryIndex){
                return dataList.getIndex();
            }
        }
        return 0;
    }

    private List<MetaDataList> getMetaList(List<CategoryDataModel> categoryDataModelList) {
        List<MetaDataList> list = new ArrayList<>();
        int index = 0;

        for (int i = 0; i < categoryDataModelList.size(); i++) {
            MetaDataList metaDataList = new MetaDataList();
            metaDataList.setIndex(index++);
            metaDataList.setCategoryIndex(i);
            metaDataList.setSubCategoryIndex(-1);
            metaDataList.setIsCategory(true);

            list.add(metaDataList);

            for (int j = 0; j < categoryDataModelList.get(i).getData().size(); j++) {
                MetaDataList metaDataList2 = new MetaDataList();
                metaDataList2.setIndex(index++);
                metaDataList2.setCategoryIndex(i);
                metaDataList2.setSubCategoryIndex(j);
                metaDataList2.setIsCategory(false);
                list.add(metaDataList2);
            }
        }
        return list;
    }
}
