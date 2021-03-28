package com.example.appkhushveehoreca;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;
    private RecyclerView testing;
    private List <CategoryModel> categoryModelList;
    private FirebaseFirestore firebaseFirestore;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        categoryRecyclerView = (RecyclerView) view.findViewById(R.id.category_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(layoutManager);


        categoryModelList = new ArrayList<CategoryModel>();

        categoryAdapter = new CategoryAdapter(categoryModelList);
        categoryRecyclerView.setAdapter(categoryAdapter);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("CATEGORIES").orderBy("index").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                                categoryModelList.add(new CategoryModel(documentSnapshot.get("icon").toString(),documentSnapshot.get("categoryName").toString()));
                            }
                            categoryAdapter.notifyDataSetChanged();
                        }else{
                            String error = task.getException().getMessage();
                            Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });



        ///////// Banner Slider Temporary

        final List<SliderModel>sliderModelList = new ArrayList<SliderModel>();

        sliderModelList.add(new SliderModel(R.mipmap.banner,"#FFFFFF"));
        sliderModelList.add(new SliderModel(R.mipmap.defaultprofilepic,"#FFFFFF"));
        sliderModelList.add(new SliderModel(R.drawable.ic_baseline_add_24,"#FFFFFF"));
        sliderModelList.add(new SliderModel(R.drawable.ic_baseline_bookmark_24,"#FFFFFF"));
        sliderModelList.add(new SliderModel(R.drawable.ic_baseline_lock_24,"#FFFFFF"));
        sliderModelList.add(new SliderModel(R.drawable.ic_baseline_email_24,"#FFFFFF"));
        sliderModelList.add(new SliderModel(R.drawable.ic_baseline_home_24,"#FFFFFF"));

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

        testing = view.findViewById(R.id.home_page_recyclerview);
        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(getContext());
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        testing.setLayoutManager(testingLayoutManager);

        List<HomePageModel> homePageModelList = new ArrayList<>();
        homePageModelList.add(new HomePageModel(0,sliderModelList));
        homePageModelList.add(new HomePageModel(1,R.drawable.stripad,"#000000"));
        homePageModelList.add(new HomePageModel(2,"Latest Products",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(3,"Latest Products New",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(0,sliderModelList));

        homePageModelList.add(new HomePageModel(1,R.drawable.stripad,"#000000"));
        homePageModelList.add(new HomePageModel(2,"Latest Products",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(3,"Latest Products New",horizontalProductScrollModelList));

        HomePageAdapter adapter = new HomePageAdapter(homePageModelList);
        testing.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        /////////////////////

        return view;
    }

}