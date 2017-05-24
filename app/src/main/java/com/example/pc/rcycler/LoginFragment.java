package com.example.pc.rcycler;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import models.ServerRequest;
import models.ServerResponse;
import models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginFragment extends Fragment implements View.OnClickListener{

    //    private AppCompatButton btn_login;
    private ImageView img_login;
    private EditText et_email,et_password;
    private TextView tv_register;
    //private TextView tv_enterDirectly;
    private ProgressBar progress;
    private SharedPreferences pref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        initViews(view);
        return view;
    }
    private void initViews(View view){

        pref = getActivity().getPreferences(0);

        img_login = (ImageView) view.findViewById(R.id.img_login);
        tv_register = (TextView)view.findViewById(R.id.tv_register);
        et_email = (EditText)view.findViewById(R.id.et_email);
        et_password = (EditText)view.findViewById(R.id.et_password);
        progress = (ProgressBar)view.findViewById(R.id.progress);
        //     tv_enterDirectly=(TextView)view.findViewById(R.id.tv_enterDirectly);
        img_login.setOnClickListener(this);
        tv_register.setOnClickListener(this);
        // tv_enterDirectly.setOnClickListener(this);
//        if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
//            btn_login.setBackgroundResource(roundedbtn);
//
//        } else{
//            btn_login.setBackgroundColor(Integer.parseInt("#4CAF50"));
//        }
    }



    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.tv_register:
                goToRegister();
                break;
//            case R.id.tv_enterDirectly:
//                goToWhatToRecycle();
//                break;

            case R.id.img_login:
                String email = et_email.getText().toString();
                String password = et_password.getText().toString();

                if(!email.isEmpty() && !password.isEmpty()) {

                    progress.setVisibility(View.VISIBLE);
                    loginProcess(email,password);

                } else {

                    Snackbar.make(getView(), "Полетата са празни!", Snackbar.LENGTH_LONG).show();
                }
                break;

        }
    }
    private void loginProcess(String email,String password){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.LOGIN_OPERATION);
        request.setUser(user);
        Call<ServerResponse> response = requestInterface.operation(request);

        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                ServerResponse resp = response.body();
                if(resp.getMessage().equals("1"))
                {Snackbar.make(getView(), "Потребителят вече е регистриран!",
                        Snackbar.LENGTH_LONG).show();}
                if(resp.getMessage().equals("2"))
                {Snackbar.make(getView(), "Успешна регистрация!",
                        Snackbar.LENGTH_LONG).show();}
                if(resp.getMessage().equals("3"))
                {Snackbar.make(getView(), "Неуспешна регистрация!",
                        Snackbar.LENGTH_LONG).show();}
                if(resp.getMessage().equals("4")||resp.getMessage().equals("6"))
                {Snackbar.make(getView(), "Грешен имейл или парола!",
                        Snackbar.LENGTH_LONG).show();}
                if(resp.getMessage().equals("5"))
                {Snackbar.make(getView(), "Успешно влязохте в профила си!",
                        Snackbar.LENGTH_LONG).show();}
                if(resp.getMessage().equals("7"))
                {Snackbar.make(getView(), "Грешна стара парола!",
                        Snackbar.LENGTH_LONG).show();}
                if(resp.getMessage().equals("8"))
                {Snackbar.make(getView(), "Паролата е променена успешно!",
                        Snackbar.LENGTH_LONG).show();}
                if(resp.getMessage().equals("9"))
                {Snackbar.make(getView(), "Грешка при промяна на паролата!",
                        Snackbar.LENGTH_LONG).show();}
                if(resp.getMessage().equals("10"))
                {Snackbar.make(getView(), "Моля попълнете празните полета!",
                        Snackbar.LENGTH_LONG).show();}
                if(resp.getMessage().equals("11"))
                {Snackbar.make(getView(), "Грешни данни!",
                        Snackbar.LENGTH_LONG).show();}
                if(resp.getMessage().equals("12"))
                {Snackbar.make(getView(), "Невалиден имейл!",
                        Snackbar.LENGTH_LONG).show();}


                if(resp.getResult().equals(Constants.SUCCESS)){
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putBoolean(Constants.IS_LOGGED_IN,true);
                    editor.putString(Constants.EMAIL,resp.getUser().getEmail());
                    editor.putString(Constants.NAME,resp.getUser().getName());
                    editor.putString(Constants.UNIQUE_ID,resp.getUser().getUnique_id());
                    editor.apply();
                    goToProfile();

                }
                progress.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

                progress.setVisibility(View.INVISIBLE);
                Log.d(Constants.TAG,"failed");
                Snackbar.make(getView(), t.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();

            }
        });
    }
    private void goToWhatToRecycle(){
        Fragment whatToRecycle = new whatToRecycleFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame,whatToRecycle);
        ft.commit();

    }
    private void goToRegister(){

        Fragment register = new RegisterFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame,register);
        ft.commit();
    }

    private void goToProfile(){

        Fragment profile = new ProfileFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame,profile);
        ft.commit();
    }
}