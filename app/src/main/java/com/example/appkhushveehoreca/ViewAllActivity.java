package com.example.appkhushveehoreca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class ViewAllActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private GridView gridView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Our Latest Products");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recycler_view);
        gridView = findViewById(R.id.grid_view);

        int layout_code = getIntent().getIntExtra("layout_code",-1);


        if(layout_code == 0) {
            recyclerView.setVisibility(View.VISIBLE);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(RecyclerView.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);

            List<WishlistModel> wishlistModelList = new ArrayList<>();

            wishlistModelList.add(new WishlistModel(R.drawable.steelfour, "Steel Vessel", "SC-125", "Contact us for", "Rs.1200/-", "Rs.1400/-"));
            wishlistModelList.add(new WishlistModel(R.drawable.steelthree, "Steel Vessel", "SC-125", "Contact us for", "Rs.1200/-", "Rs.1400/-"));
            wishlistModelList.add(new WishlistModel(R.drawable.steelone, "Steel Vessel", "SC-125", "Contact us for", "Rs.1200/-", "Rs.1400/-"));
            wishlistModelList.add(new WishlistModel(R.drawable.steeltwo, "Steel Vessel", "SC-125", "Contact us for", "Rs.1200/-", "Rs.1400/-"));

            wishlistModelList.add(new WishlistModel(R.drawable.steelfour, "Steel Vessel", "SC-125", "Contact us for", "Rs.1200/-", "Rs.1400/-"));
            wishlistModelList.add(new WishlistModel(R.drawable.steelthree, "Steel Vessel", "SC-125", "Contact us for", "Rs.1200/-", "Rs.1400/-"));
            wishlistModelList.add(new WishlistModel(R.drawable.steelone, "Steel Vessel", "SC-125", "Contact us for", "Rs.1200/-", "Rs.1400/-"));
            wishlistModelList.add(new WishlistModel(R.drawable.steeltwo, "Steel Vessel", "SC-125", "Contact us for", "Rs.1200/-", "Rs.1400/-"));

            WishlistAdapter adapter = new WishlistAdapter(wishlistModelList, false);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }else if(layout_code == 1) {

            gridView.setVisibility(View.VISIBLE);
            List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.woodenone, "WoodenOne", "SDw", "sd", "RS1250"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.woodentwo, "WoodenOne", "SDewe", "sd", "RS1250"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.woodenthree, "WoodenOne", "SewD", "sd", "RS1250"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.woodenfour, "WoodenOne", "SD", "sd", "RS1250"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.woodenfive, "WoodenOne", "SD", "sd", "RS1250"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.woodensix, "WoodenOne", "SD", "sd", "RS1250"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.woodenone, "WoodenOne", "SDw", "sd", "RS1250"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.woodentwo, "WoodenOne", "SDewe", "sd", "RS1250"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.woodenthree, "WoodenOne", "SewD", "sd", "RS1250"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.woodenone, "WoodenOne", "SDw", "sd", "RS1250"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.woodentwo, "WoodenOne", "SDewe", "sd", "RS1250"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.woodenthree, "WoodenOne", "SewD", "sd", "RS1250"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.woodenfour, "WoodenOne", "SD", "sd", "RS1250"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.woodenfive, "WoodenOne", "SD", "sd", "RS1250"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.woodensix, "WoodenOne", "SD", "sd", "RS1250"));

            GridProductLayoutAdapter gridProductLayoutAdapter = new GridProductLayoutAdapter(horizontalProductScrollModelList);
            gridView.setAdapter(gridProductLayoutAdapter);

        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}