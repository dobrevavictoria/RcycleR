package com.example.pc.rcycler;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class StartFragment extends Fragment implements View.OnClickListener {

    private AppCompatButton login_btn, register_btn;
    private TextView tv_enterDirectly;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_start,
                container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        login_btn = (AppCompatButton) view.findViewById(R.id.login_btn);
        register_btn = (AppCompatButton) view.findViewById(R.id.register_btn);
        tv_enterDirectly=(TextView)view.findViewById(R.id.tv_enterDirectly);
        login_btn.setOnClickListener(this);
        register_btn.setOnClickListener(this);
        tv_enterDirectly.setOnClickListener(this);

    }

    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.login_btn:
                goToLogin();
                break;
            case R.id.register_btn:
                goToRegister();
                break;
            case R.id.tv_enterDirectly:
                goToWhatToRecycle();
                break;
        }

    }
    private void goToWhatToRecycle(){
        Fragment recyleFragment=new whatToRecycleFragment();
        FragmentTransaction ft=getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame,recyleFragment);
        ft.commit();
    }

    public void goToLogin(){
        Fragment login=new LoginFragment();
        FragmentTransaction ft=getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame, login);
        ft.commit();
    }
    private void goToRegister(){

        Fragment register = new RegisterFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame,register);
        ft.commit();
    }
}