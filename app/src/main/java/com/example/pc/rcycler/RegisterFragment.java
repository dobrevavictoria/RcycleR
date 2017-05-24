package com.example.pc.rcycler;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import models.ServerRequest;
import models.ServerResponse;
import models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//        import android.app.Fragment;
//        import android.app.FragmentTransaction;

public class RegisterFragment extends Fragment implements View.OnClickListener{

    private AppCompatButton btn_register;
    private EditText et_email,et_password,et_name;
    private TextView tv_login;
    private ProgressBar progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register,container,false);
        initViews(view);
        return view;
    }

    private void initViews(View view){

        btn_register = (AppCompatButton)view.findViewById(R.id.btn_register);
        tv_login = (TextView)view.findViewById(R.id.tv_login);
        et_name = (EditText)view.findViewById(R.id.et_name);
        et_email = (EditText)view.findViewById(R.id.et_email);
        et_password = (EditText)view.findViewById(R.id.et_password);

        progress = (ProgressBar)view.findViewById(R.id.progress);

        btn_register.setOnClickListener(this);
        tv_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.tv_login:
                goToLogin();
                break;

            case R.id.btn_register:

                String name = et_name.getText().toString();
                String email = et_email.getText().toString();
                String password = et_password.getText().toString();

                if(!name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {

                    progress.setVisibility(View.VISIBLE);
                    registerProcess(name,email,password);

                } else {

                    Snackbar.make(getView(), "Полетата са празни!", Snackbar.LENGTH_LONG).show();
                }
                break;

        }

    }

    private void registerProcess(String name, String email,String password){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.REGISTER_OPERATION);
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

    private void goToLogin(){

        Fragment login = new LoginFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame,login);
        ft.commit();
    }
}