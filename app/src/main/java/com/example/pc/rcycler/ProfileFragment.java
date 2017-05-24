package com.example.pc.rcycler;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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



//import android.app.Fragment;
//import android.app.FragmentTransaction;

public class ProfileFragment extends Fragment implements View.OnClickListener {



    OnProfileListener mCallback;
    private TextView tv_name,tv_email,tv_message;
    private SharedPreferences pref;
    private AppCompatButton btn_change_password,btn_logout, btn_ok;
    //            private AppCompatActivity btn_ok;

    private EditText et_old_password,et_new_password;
    private AlertDialog dialog;
    private ProgressBar progress;


    public interface OnProfileListener{
//        void onProfileButtonOkClicked();
    }
    private OnProfileListener mCallBack;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile,container,false);
        initViews(view);
        return view;

    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        pref = getActivity().getPreferences(0);
        tv_name.setText("Здравей, "+pref.getString(Constants.NAME,"")+"!");
        tv_email.setText(pref.getString(Constants.EMAIL,""));
//        btn_ok=(AppCompatButton)view.findViewById(R.id.btn_ok);
//        btn_ok.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mCallBack.onProfileButtonOkClicked();
//            }
//        });


    }

    private void initViews(View view){

        tv_name = (TextView)view.findViewById(R.id.tv_name);
        tv_email = (TextView)view.findViewById(R.id.tv_email);
        btn_change_password = (AppCompatButton)view.findViewById(R.id.btn_chg_password);
        btn_logout = (AppCompatButton)view.findViewById(R.id.btn_logout);
//        btn_ok=(AppCompatButton)view.findViewById(R.id.btn_ok);
        btn_ok = (AppCompatButton) view.findViewById(R.id.btn_ok);
        btn_change_password.setOnClickListener(this);
        btn_logout.setOnClickListener(this);
//        btn_ok.setOnClickListener(this);
        btn_ok.setOnClickListener(this);
    }

    private void showDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_change_password, null);
        et_old_password = (EditText)view.findViewById(R.id.et_old_password);
        et_new_password = (EditText)view.findViewById(R.id.et_new_password);
        tv_message = (TextView)view.findViewById(R.id.tv_message);
        progress = (ProgressBar)view.findViewById(R.id.progress);
        builder.setView(view);
        builder.setTitle("Промени парола");
        builder.setPositiveButton("Промени парола", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("Отмени", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog = builder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String old_password = et_old_password.getText().toString();
                String new_password = et_new_password.getText().toString();
                if(!old_password.isEmpty() && !new_password.isEmpty()){

                    progress.setVisibility(View.VISIBLE);
                    changePasswordProcess(pref.getString(Constants.EMAIL,""),old_password,new_password);

                }else {

                    tv_message.setVisibility(View.VISIBLE);
                    tv_message.setText("Полетата са празни!");
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_chg_password:
                showDialog();
                break;
            case R.id.btn_logout:
                logout();
                break;
//            case R.id.btn_ok:
            case R.id.btn_ok:
                goTowhatToRecycle();
                break;
        }
    }
    private void goTowhatToRecycle(){
        Fragment recyleFragment=new whatToRecycleFragment();
        FragmentTransaction ft=getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame,recyleFragment);
        ft.commit();
    }

    private void logout() {
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(Constants.IS_LOGGED_IN,false);
        editor.putString(Constants.EMAIL,"");
        editor.putString(Constants.NAME,"");
        editor.putString(Constants.UNIQUE_ID,"");
        editor.apply();
        goToLogin();
    }

    private void goToLogin(){

        Fragment login = new LoginFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame,login);
        ft.commit();
    }



    private void changePasswordProcess(String email,String old_password,String new_password){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        User user = new User();
        user.setEmail(email);
        user.setOld_password(old_password);
        user.setNew_password(new_password);
        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.CHANGE_PASSWORD_OPERATION);
        request.setUser(user);
        Call<ServerResponse> response = requestInterface.operation(request);

        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                ServerResponse resp = response.body();
                if(resp.getResult().equals(Constants.SUCCESS)){
                    progress.setVisibility(View.GONE);
                    tv_message.setVisibility(View.GONE);
                    dialog.dismiss();

                    if(resp.getMessage().equals("5"))
                    {Snackbar.make(getView(), "Успешно влязохте в профила си!",
                            Snackbar.LENGTH_LONG).show();}

                    if(resp.getMessage().equals("8"))
                    {Snackbar.make(getView(), "Паролата е променена успешно!",
                            Snackbar.LENGTH_LONG).show();}


                }else {
                    progress.setVisibility(View.GONE);
                    tv_message.setVisibility(View.VISIBLE);

                    if(resp.getMessage().equals("5"))
                    {Snackbar.make(getView(), "Успешно влязохте в профила си!",
                            Snackbar.LENGTH_LONG).show();}
                    if(resp.getMessage().equals("7"))
                    {Snackbar.make(getView(), "Грешна стара парола!",
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

                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

                Log.d(Constants.TAG,"failed");
                progress.setVisibility(View.GONE);
                tv_message.setVisibility(View.VISIBLE);
                tv_message.setText(t.getLocalizedMessage());

            }
        });
    }
}

