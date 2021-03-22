package com.example.appkhushveehoreca;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomePageAdapter extends RecyclerView.Adapter {

    private List<HomePageModel> homePageModelList;

    public HomePageAdapter(List<HomePageModel> homePageModelList) {
        this.homePageModelList = homePageModelList;

    }
    @Override
    public int getItemViewType(int position) {
        switch (homePageModelList.get(position).getType()){
            case 0:
                return HomePageModel.BANNER_SLIDER;
            default:
                return -1;
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch (viewType){
            case HomePageModel.BANNER_SLIDER:
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sliding_ad_layout,viewGroup,false);
                return new BannerSliderViewHolder(view);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        switch (homePageModelList.get(position).getType()){
            case HomePageModel.BANNER_SLIDER:
                List<SliderModel> sliderModelList = homePageModelList.get(position).getSliderModelList();

            default:
                return;
        }

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class BannerSliderViewHolder extends RecyclerView.ViewHolder{

        private ViewPager bannerSliderViewPager;
        private List<SliderModel> sliderModelList;
        private int currentPage = 2;

        private Timer timer;
        final private long DELAY_TIME=3000;
        final private long PERIOD_TIME=3000;

         public BannerSliderViewHolder(@NonNull View itemView) {
            super(itemView);
            bannerSliderViewPager = itemView.findViewById(R.id.banner_slider_view_pager);

         }

         private void setBannerSliderViewPager(final List<SliderModel> sliderModelList){

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
                         pageLooper(sliderModelList);
                     }
                 }
             };
             bannerSliderViewPager.addOnPageChangeListener(onPageChangeListener);

             startBannerSlideShow(sliderModelList);

             bannerSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
                 @Override
                 public boolean onTouch(View v, MotionEvent event) {
                     pageLooper(sliderModelList);
                     stopBannerSlideshow();
                     if(event.getAction() == MotionEvent.ACTION_UP){
                         startBannerSlideShow(sliderModelList);
                     }
                     return false;
                 }
             });

         }
         private void pageLooper(List<SliderModel> sliderModelList){

            if (currentPage == sliderModelList.size()-2){
                currentPage = 2;
                bannerSliderViewPager.setCurrentItem(currentPage,false);

            }
            if (currentPage == 1){
                currentPage = sliderModelList.size()-3;
                bannerSliderViewPager.setCurrentItem(currentPage,false);

            }

        }
         private void startBannerSlideShow(final List<SliderModel> sliderModelList){
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
    }

}
