package com.example.appkhushveehoreca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class WhatsAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whats_app);


        Button sendButton = findViewById(R.id.button_send);
        final EditText editTextMessage = findViewById(R.id.editText_whatsApp);
        ImageView backToHome = findViewById(R.id.imageView_backHome);


        backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHomePage(v);
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String message = editTextMessage.getText().toString();

                boolean installed = appInstalledOrNot("com.whatsapp");

                if (installed){
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=9909975572&text=" + message));
                    startActivity(intent);
                }else{
                    Toast.makeText(WhatsAppActivity.this, "WhatsApp not Installed on your Device!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean appInstalledOrNot(String url) {
        PackageManager packageManager = getPackageManager();
        boolean app_installed;
        try {
            packageManager.getPackageInfo(url,PackageManager.GET_ACTIVITIES);
            app_installed = true;
        }catch (PackageManager.NameNotFoundException e){
            app_installed = false;
            e.printStackTrace();
        }
        return app_installed;
    }

    public void goToHomePage(View view) {
        Intent intent = new Intent(WhatsAppActivity.this, MainActivity.class);
        startActivity(intent);

    }
}
