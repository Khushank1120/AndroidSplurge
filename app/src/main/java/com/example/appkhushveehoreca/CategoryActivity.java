package com.example.appkhushveehoreca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    private RecyclerView categoryRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String title = getIntent().getStringExtra("CategoryName");
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        categoryRecyclerView = findViewById(R.id.category_recyclerview);


        ///////// Banner Slider Temporary

        List<SliderModel>sliderModelList = new ArrayList<SliderModel>();

        sliderModelList.add(new SliderModel(R.drawable.ic_baseline_email_24,"#FFFFFF"));
        sliderModelList.add(new SliderModel(R.drawable.ic_baseline_home_24,"#FFFFFF"));
        sliderModelList.add(new SliderModel(R.mipmap.banner,"#FFFFFF"));

        sliderModelList.add(new SliderModel(R.mipmap.defaultprofilepic,"#FFFFFF"));
        sliderModelList.add(new SliderModel(R.drawable.ic_baseline_add_24,"#FFFFFF"));
        sliderModelList.add(new SliderModel(R.drawable.ic_baseline_bookmark_24,"#FFFFFF"));
        sliderModelList.add(new SliderModel(R.drawable.ic_baseline_lock_24,"#FFFFFF"));
        sliderModelList.add(new SliderModel(R.drawable.ic_baseline_email_24,"#FFFFFF"));

        sliderModelList.add(new SliderModel(R.drawable.ic_baseline_home_24,"#FFFFFF"));
        sliderModelList.add(new SliderModel(R.mipmap.banner,"#FFFFFF"));
        sliderModelList.add(new SliderModel(R.mipmap.defaultprofilepic,"#FFFFFF"));

        ///////// Banner Slider Temporary ////////



        ////// Strip Ad Temporary

        ////// Strip Ad Temporary ////////



        ////// Horizontal Product Layout

        List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.woodenone,"WoodenOne", "SDw","sd","RS1250"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.woodentwo,"WoodenOne", "SDewe","sd","RS1250"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.woodenthree,"WoodenOne", "SewD","sd","RS1250"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.woodenfour,"WoodenOne", "SD","sd","RS1250"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.woodenfive,"WoodenOne", "SD","sd","RS1250"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.woodensix,"WoodenOne", "SD","sd","RS1250"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.ic_baseline_add_24,"Add", "SD","sd","RS1250"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.ic_baseline_bookmark_24,"Bookmark", "SD","sd","RS1250"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.ic_baseline_close_24,"Close", "SD","sd","RS1250"));

        ////// Horizontal Product Layout //////



        //////////////////////

         LinearLayoutManager testingLayoutManager = new LinearLayoutManager(this);
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        categoryRecyclerView.setLayoutManager(testingLayoutManager);

        List<HomePageModel> homePageModelList = new ArrayList<>();
        homePageModelList.add(new HomePageModel(0,sliderModelList));
        homePageModelList.add(new HomePageModel(3,"Latest Products New",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(1,R.drawable.stripad,"#000000"));
        homePageModelList.add(new HomePageModel(2,"Latest Products",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(1,R.drawable.stripad,"#ff0000"));

        HomePageAdapter adapter = new HomePageAdapter(homePageModelList);
        categoryRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_icon, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id ==  R.id.main_search_icon){

            /// todo: search

            return true;
        }else if(id == android.R.id.home){
            finish();
            return true;

        }
        return super.onOptionsItemSelected(item);
    }
}