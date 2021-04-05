package com.example.appkhushveehoreca;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

import static com.example.appkhushveehoreca.DBqueries.currentUser;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private static final int HOME_FRAGMENT = 0;
    private static final int REWARDS_FRAGMENT = 1;
    private static final int ACCOUNT_FRAGMENT = 2;
    private static final int WISHLIST_FRAGMENT = 3;

    public MenuItem whatsAppIcon;

    private FrameLayout frameLayout;
    private ImageView actionBarLogo;
    private int currentFragment = -1;
    private NavigationView navigationView;
    private Toolbar toolbar;
//    private Dialog signUpDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = findViewById(R.id.main_frame_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        actionBarLogo = findViewById(R.id.actionBarLogo);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        setFragment(new HomeFragment(),HOME_FRAGMENT);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);

        whatsAppIcon = findViewById(R.id.whatsapp_icon);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
//                .setDrawerLayout(drawer)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);

        if(currentUser == null){
            navigationView.getMenu().getItem(navigationView.getMenu().size() - 1).setEnabled(false);
        }else{
            navigationView.getMenu().getItem(navigationView.getMenu().size() - 1).setEnabled(true);
        }

//        final Dialog signInDialog = new Dialog(MainActivity.this);
//        signInDialog.setContentView(R.layout.);
//        signInDialog.setCancelable(true);

    }


    @Override
    public void onBackPressed(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            if(currentFragment == HOME_FRAGMENT){
                currentFragment = -1;
                super.onBackPressed();
            }else{
                actionBarLogo.setVisibility(View.VISIBLE);
                invalidateOptionsMenu();
                setFragment(new HomeFragment(),HOME_FRAGMENT);
                navigationView.getMenu().getItem(0).setChecked(true);
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        if(currentFragment == HOME_FRAGMENT) {
            getMenuInflater().inflate(R.menu.main, menu);
            Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        }
//        else{
//            getSupportActionBar().setDisplayShowTitleEnabled(true);
//        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id ==  R.id.main_search_icon){

            /// todo: search

            return true;
        }else if(id == R.id.main_notification_icon){

            /// todo: notification

            return true;
        }else if (id == R.id.whatsapp_icon){

            /// todo: cart

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void goToFragment(String title,Fragment fragment, int fragmentNo){
        actionBarLogo.setVisibility(View.GONE);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(title);
        invalidateOptionsMenu();
        setFragment(fragment,fragmentNo);
//        if(fragmentNo == REWARDS_FRAGMENT){
//            navigationView.getMenu().getItem(1).setChecked(true);
//        }
    }

    MenuItem menuItem;
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        ////// Handle navigation view clicks here ///////
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        menuItem = item;

        drawer.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                int id = menuItem.getItemId();
                if(id == R.id.nav_my_khushveeHoreca){
                    actionBarLogo.setVisibility(View.VISIBLE);
                    invalidateOptionsMenu();
                    setFragment(new HomeFragment(),HOME_FRAGMENT);
                }else if(id == R.id.nav_my_account){
                    goToFragment("My Account",new MyAccountFragment(),ACCOUNT_FRAGMENT);
                }else if(id == R.id.nav_my_offers){
                    goToFragment("My Rewards",new MyRewardsFragment(),REWARDS_FRAGMENT);
                }else if(id == R.id.nav_my_wishlist){
                    goToFragment("My WishList",new MyWishlistFragment(),WISHLIST_FRAGMENT);
                }else if(id == R.id.nav_sign_out) {
                    FirebaseAuth.getInstance().signOut();
                    Intent registerIntent = new Intent(MainActivity.this,RegisterActivity.class);
                    startActivity(registerIntent);
                    finish();
                }
            }
        });

        return true;
    }

    private void setFragment(Fragment fragment,int fragmentNo){
        if(fragmentNo != currentFragment) {
            currentFragment = fragmentNo;
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(frameLayout.getId(),fragment);
            fragmentTransaction.commit();
        }
    }

}