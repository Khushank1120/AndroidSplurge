package com.example.appkhushveehoreca;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ActivityFirstBeforeSignUp extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<PlateModel> plateModelList;
    private PlateAdapter plateAdapter;
    private LinearLayout emailContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_before_sign_up);

        /// App update Checker ///

        ///

        /// Hide status Bar //

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            emailContinue = (LinearLayout) findViewById(R.id.linearLayout2);
            recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setKeepScreenOn(true);
            recyclerView.setHasFixedSize(true);
            layoutManager.setOrientation(RecyclerView.HORIZONTAL);


            plateModelList = new ArrayList<>();
            plateModelList.add(new PlateModel(R.drawable.splashlogin2));
            plateModelList.add(new PlateModel(R.drawable.splashlogin1));
            plateModelList.add(new PlateModel(R.drawable.splashlogin3));
            plateModelList.add(new PlateModel(R.drawable.splashlogin5));
            plateModelList.add(new PlateModel(R.drawable.splashlogin4));
            plateModelList.add(new PlateModel(R.drawable.splashlogin6));

            plateAdapter = new PlateAdapter(plateModelList, this);
            recyclerView.setAdapter(plateAdapter);
            plateAdapter.notifyDataSetChanged();

            autoScroll();
        }

    }

    public void autoScroll(){
        final int speedScroll = 0;
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            int count = 0;
            @Override
            public void run() {

                if(count == plateAdapter.getItemCount())
                    count=0;
                if(count < plateAdapter.getItemCount()){
                    recyclerView.smoothScrollToPosition(++ count);
                    handler.postDelayed(this,speedScroll);
                }
            }

        };
        handler.postDelayed(runnable,speedScroll);
    }

    public void goToLogin(View view) {
        Intent intent = new Intent(ActivityFirstBeforeSignUp.this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }
    public void goToPhoneLogin(View view) {
        Intent intent = new Intent(ActivityFirstBeforeSignUp.this, OTPSend.class);
        startActivity(intent);
        finish();
    }
}