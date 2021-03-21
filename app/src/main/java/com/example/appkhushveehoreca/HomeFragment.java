package com.example.appkhushveehoreca;

import android.graphics.Color;
import android.os.Bundle;

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

    ///////// Banner Slider Temporary

    private ViewPager bannerSliderViewPager;
    private List<SliderModel> sliderModelList;
    private int currentPage = 2;

    private Timer timer;
    final private long DELAY_TIME=3000;
    final private long PERIOD_TIME=3000;

    ///////// Banner Slider Temporary


    ////// Strip Ad Temporary

    private ImageView stripAdImage;
    private ConstraintLayout stripAdContainer;

    ////// Strip Ad Temporary


    ////// Horizontal Product Layout

    private TextView horizontalLayoutTitle;
    private Button horizontalLayoutViewAllBtn;
    private RecyclerView horizontalRecyclerView;

    ////// Horizontal Product Layout //////


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        categoryRecyclerView = (RecyclerView) view.findViewById(R.id.category_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(layoutManager);

        List <CategoryModel> categoryModelList = new ArrayList<CategoryModel>();

        categoryModelList.add(new CategoryModel("link","Home"));
        categoryModelList.add(new CategoryModel("link","Steel"));
        categoryModelList.add(new CategoryModel("link","Melamine"));
        categoryModelList.add(new CategoryModel("link","Wooden"));
        categoryModelList.add(new CategoryModel("link","Ceramic"));
        categoryModelList.add(new CategoryModel("link","Pot"));
        categoryModelList.add(new CategoryModel("link","Melamine"));
        categoryModelList.add(new CategoryModel("link","Wooden"));
        categoryModelList.add(new CategoryModel("link","Ceramic"));
        categoryModelList.add(new CategoryModel("link","Pot"));

        categoryAdapter = new CategoryAdapter(categoryModelList);
        categoryRecyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();


        ///////// Banner Slider Temporary

        bannerSliderViewPager = view.findViewById(R.id.banner_slider_view_pager);

        sliderModelList = new ArrayList<SliderModel>();

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


        SliderAdapter sliderAdapter = new SliderAdapter(sliderModelList);
        bannerSliderViewPager.setAdapter(sliderAdapter);
        bannerSliderViewPager.setClipToPadding(false);
        bannerSliderViewPager.setPageMargin(20);

        bannerSliderViewPager.setCurrentItem(currentPage);

        ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                currentPage = i;

            }

            @Override
            public void onPageScrollStateChanged(int i) {
                if (i == ViewPager.SCROLL_STATE_IDLE){
                    pageLooper();
                }
            }
        };
        bannerSliderViewPager.addOnPageChangeListener(onPageChangeListener);

        startBannerSlideShow();

        bannerSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                pageLooper();
                stopBannerSlideshow();
                if(event.getAction() == MotionEvent.ACTION_UP){
                    startBannerSlideShow();
                }
                return false;
            }
        });

        ///////// Banner Slider Temporary ////////


        ////// Strip Ad Temporary

        stripAdImage = view.findViewById(R.id.strip_ad_image);
        stripAdContainer = view.findViewById(R.id.strip_ad_container);

        stripAdImage.setImageResource(R.drawable.stripad);
        stripAdContainer.setBackgroundColor(Color.parseColor("#FFFFFF"));

        ////// Strip Ad Temporary ////////


        ////// Horizontal Product Layout

        horizontalLayoutTitle = view.findViewById(R.id.horizontal_scroll_layout_title);
        horizontalLayoutViewAllBtn = view.findViewById(R.id.horizontal_scroll_view_all_btn);
        horizontalRecyclerView = view.findViewById(R.id.horizontal_scroll_layout_recyclerView);

        List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.woodenone,"WoodenOne", "SDw","sd","RS1250"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.woodentwo,"WoodenOne", "SDewe","sd","RS1250"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.woodenthree,"WoodenOne", "SewD","sd","RS1250"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.woodenfour,"WoodenOne", "SD","sd","RS1250"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.woodenfive,"WoodenOne", "SD","sd","RS1250"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.woodensix,"WoodenOne", "SD","sd","RS1250"));

        HorizontalProductScrollAdapter horizontalProductScrollAdapter = new HorizontalProductScrollAdapter(horizontalProductScrollModelList);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        horizontalRecyclerView.setLayoutManager(linearLayoutManager);

        horizontalRecyclerView.setAdapter(horizontalProductScrollAdapter);
        horizontalProductScrollAdapter.notifyDataSetChanged();

        ////// Horizontal Product Layout //////



        ////// Grid Product Layout

        TextView gridLayoutTitle = view.findViewById(R.id.grid_product_layout_title);
        Button gridLayoutViewAllBtn = view.findViewById(R.id.grid_product_layout_viewall_btn);
        GridView gridView = view.findViewById(R.id.grid_product_layout_gridview);

        gridView.setAdapter(new GridProductLayoutAdapter(horizontalProductScrollModelList));

        ////// Grid Product Layout ///////


        return view;
    }

    ///////// Banner Slider Temporary

    private void pageLooper(){

        if (currentPage == sliderModelList.size()-2){
            currentPage = 2;
            bannerSliderViewPager.setCurrentItem(currentPage,false);

        }
        if (currentPage == 1){
            currentPage = sliderModelList.size()-3;
            bannerSliderViewPager.setCurrentItem(currentPage,false);

        }

    }

    private void startBannerSlideShow(){
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            @Override
            public void run() {
                if (currentPage >= sliderModelList.size()){
                    currentPage = 1;
                }
                bannerSliderViewPager.setCurrentItem(currentPage++,true);
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        },DELAY_TIME,PERIOD_TIME);
    }

    private void stopBannerSlideshow(){
        timer.cancel();
    }

    ///////// Banner Slider Temporary ////////
}