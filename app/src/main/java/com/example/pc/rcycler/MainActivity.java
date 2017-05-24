package com.example.pc.rcycler;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {



        private SharedPreferences pref;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            pref = getPreferences(0);
            initFragment();
        }

    private void initFragment(){
        Fragment fragment;
        if(pref.getBoolean(Constants.IS_LOGGED_IN,false)){
            fragment = new ProfileFragment();
        }else {
            fragment = new StartFragment();
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame,fragment);
        ft.commit();


    }

    }