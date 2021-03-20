package com.example.appkhushveehoreca;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordFragment extends Fragment {

    public ResetPasswordFragment() {
        // Required empty public constructor
    }

    private EditText registeredEmail;
    private Button resetPasswordBtn;
    private TextView goBack;

    private FrameLayout parentFrameLayout;

    private FirebaseAuth firebaseAuth;

    private ViewGroup emailIconContainer;
    private ImageView emailIcon;
    private TextView emailIconText;
    private ProgressBar progressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reset_password, container, false);

        registeredEmail = view.findViewById(R.id.forgotPasswordEmail);
        resetPasswordBtn = view.findViewById(R.id.resetPasswordBtn);
        goBack = view.findViewById(R.id.forgotPasswordGoBack);
        parentFrameLayout = getActivity().findViewById(R.id.registerFrameLayout);

        emailIconContainer = view.findViewById(R.id.forgotPasswordEmailContainer);
        emailIcon = view.findViewById(R.id.forgotPasswordEmailIcon);
        emailIconText = view.findViewById(R.id.forgotPasswordEmailIconText);
        progressBar = view.findViewById(R.id.forgotPasswordProgressBar);

        firebaseAuth = FirebaseAuth.getInstance();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        registeredEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        resetPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TransitionManager.beginDelayedTransition(emailIconContainer);
                emailIcon.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);

                resetPasswordBtn.setEnabled(false);

                firebaseAuth.sendPasswordResetEmail(registeredEmail.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if(task.isSuccessful()){


                                    /// todo left to add buttons ///


                                    Toast.makeText(getActivity(), "email sent successfully", Toast.LENGTH_SHORT).show();
                                }else{
                                    String error = task.getException().getMessage();
                                    Toast.makeText(getActivity() , error, Toast.LENGTH_SHORT).show();
                                }
                                resetPasswordBtn.setEnabled(true);
                            }
                        });
            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new SingInFragment());
            }
        });

    }
    public void checkInputs(){
        if(TextUtils.isEmpty(registeredEmail.getText().toString())){
            resetPasswordBtn.setEnabled(false);
        }else{
            resetPasswordBtn.setEnabled(true);
        }
    }
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(parentFrameLayout.getId(),fragment);
        fragmentTransaction.commit();
    }
}