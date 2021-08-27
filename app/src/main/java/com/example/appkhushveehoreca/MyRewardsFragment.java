package com.example.appkhushveehoreca;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyRewardsFragment extends Fragment {


    public MyRewardsFragment() {
        // Required empty public constructor
    }

    private RecyclerView rewardsRecyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_rewards, container, false);

        rewardsRecyclerView = view.findViewById(R.id.my_rewards_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        rewardsRecyclerView.setLayoutManager(layoutManager);

        List<RewardModel> rewardModelList = new ArrayList<>();
        rewardModelList.add(new RewardModel(" Contact Us For Special Offers ", "Till Dec 2021", "For best offers email us or call us on 94234234242"));
        rewardModelList.add(new RewardModel(" Contact Us For Special Offers ", "Till Dec 2021", "For best offers email khushveehorecas@gmail.com"));
        rewardModelList.add(new RewardModel(" Contact Us For Special Offers ", "Till Dec 2021", "For best prices and orders Contact Us"));

        MyRewardAdapter myRewardAdapter = new MyRewardAdapter(rewardModelList);
        rewardsRecyclerView.setAdapter(myRewardAdapter);
        myRewardAdapter.notifyDataSetChanged();

        return view;
    }
}