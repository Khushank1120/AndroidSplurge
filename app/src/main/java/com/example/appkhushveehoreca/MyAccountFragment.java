package com.example.appkhushveehoreca;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class MyAccountFragment extends Fragment {


    public MyAccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_account, container, false);

        ImageView ourLocation = view.findViewById(R.id.location_textView);
        ImageView whatsapp = view.findViewById(R.id.whatsapp_textview);
        TextView visionArtWebLink = view.findViewById(R.id.visionArtWebLink);

        visionArtWebLink.setMovementMethod(LinkMovementMethod.getInstance());


        ourLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToUrl("https://www.google.com/maps/place/KHUSHVEE+HORECA/@23.1072167,72.670719,17z/data=!3m1!4b1!4m5!3m4!1s0x395e872760ee3f8b:0x47ed490b45be39b!8m2!3d23.1072118!4d72.6729077");
            }
        });

        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToUrl("https://api.whatsapp.com/send?phone=+919909975572");
            }
        });

        return view;
    }

    public void goToUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}