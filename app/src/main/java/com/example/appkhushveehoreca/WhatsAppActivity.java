package com.example.appkhushveehoreca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;


public class WhatsAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whats_app);


        Button sendButton = findViewById(R.id.button_send);
        final EditText editTextMessage = findViewById(R.id.editText_whatsApp);

        TextView whatsapp = findViewById(R.id.whatsapp_textview);
        TextView khushveeHorecaWebLink = findViewById(R.id.textViewKhushveeHorecaWebLink);

        ImageView facebook = findViewById(R.id.imageViewFacebook);
        ImageView instagram = findViewById(R.id.imageViewInstagram);
        ImageView linkedin = findViewById(R.id.imageViewLinkedIn);
        ImageView pinterest = findViewById(R.id.imageViewPinterest);
        ImageView twitter = findViewById(R.id.imageViewTwitter);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String title = "Contact Us through WhatsApp";

        Objects.requireNonNull(getSupportActionBar()).setTitle(title);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        whatsapp.setMovementMethod(LinkMovementMethod.getInstance());
        khushveeHorecaWebLink.setMovementMethod(LinkMovementMethod.getInstance());


        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToUrl("https://www.facebook.com/KhushveeHorecaIndia");
            }
        });
        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToUrl("https://www.instagram.com/khushveehoreca/");
            }
        });

        linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToUrl("https://www.linkedin.com/company/khushveehoreca/");
            }
        });
        pinterest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToUrl("https://in.pinterest.com/khushveehoreca/");
            }
        });
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToUrl("https://twitter.com/KhushveeH");
            }
        });



        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String message = editTextMessage.getText().toString();

                boolean installed = appInstalledOrNot("com.whatsapp");

                if (installed){
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://api.whatsapp.com/send?phone=+919909975572&text="+message));
                    startActivity(intent);
                }else{
                    Toast.makeText(WhatsAppActivity.this, "WhatsApp not Installed on your Device!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean appInstalledOrNot(String s) {
        PackageManager packageManager = getPackageManager();
        boolean app_installed;
        try {
            packageManager.getPackageInfo(s,PackageManager.GET_ACTIVITIES);
            app_installed = true;
        }catch (PackageManager.NameNotFoundException e){
            app_installed = false;
            e.printStackTrace();
        }
        return app_installed;
    }

    public void goToUrl(String s){
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}