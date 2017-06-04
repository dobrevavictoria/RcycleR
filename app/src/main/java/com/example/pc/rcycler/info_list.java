package com.example.pc.rcycler;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.R.attr.name;

public class info_list extends AppCompatActivity {
    private ImageView yellow_circle;
    private ImageView green_circle;
    private ImageView blue_circle;
    private ImageView calendar_img;
    private ImageView hist;
    int year_x, month_x, day_x;
    static final int DIALOG_ID = 0;
    int i = 0;
    String[] myDates;
    DatabaseHandler db = new DatabaseHandler(this);

    CaldroidFragment caldroidFragment = new CaldroidFragment();
    final SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");

    // според избраната от потребителя опция num приема ст-сти 1 - "Защо да рециклираме?",
    // 2 - "Как да рециклираме правилно", 3 - "Любопитно", и в зависимост от това число се използват различни масиви
    // в Try.java. Целта е да се използват само Try.class, MyAdapter.class, slide.xml, activity_try.xml  за 3-те опции
    //от info_list, и да се избегне създаването на други .class, .xml файлове с цел опростяване на приложението
    // и използване на по-малко памет.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_list);
        final Calendar cal = Calendar.getInstance();
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH); //starts from 0
        day_x = cal.get(Calendar.DAY_OF_MONTH);

        hist = (ImageView) findViewById(R.id.history);
        yellow_circle = (ImageView) findViewById(R.id.yellowCircle);
        green_circle = (ImageView) findViewById(R.id.greenCircle);
        blue_circle = (ImageView) findViewById(R.id.blueCircle);
        calendar_img = (ImageView) findViewById(R.id.calendar);

        yellow_circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Number.setNum(1);
                Intent intent1 = new Intent(info_list.this, Try.class);
                startActivity(intent1);
            }
        });
        green_circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Number.setNum(2);
                Intent intent2 = new Intent(info_list.this, Try.class);
                startActivity(intent2);
            }
        });
        blue_circle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Number.setNum(3);
                // TODO Auto-generated method stub
                Intent intent3 = new Intent(info_list.this, Try.class);
                startActivity(intent3);
            }
        });
        calendar_img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                calcal();
            }
        });

        hist.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                List<User_activity> useract = db.getAllActivities();
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(info_list.this);
                String str = "";
                alertDialog.setTitle("Хронология");
                for (User_activity ua : useract) {

                    str = str + "\n\n" + "Id: " + ua.getID() + "\n" + "Дата: " + ua.getDate() + "\n" + "Час: " + ua.getTime() + "\n" + "Рециклиран материал: " + ua.getMaterial();
                    Log.d("Name: ", str);
                    final View layout = getLayoutInflater().inflate(R.layout.custom, null);
                    final TextView txt = (TextView) layout.findViewById(R.id.history);
                    txt.setText(str);
                    alertDialog.setView(layout);
                    alertDialog.setCancelable(true);

                }
                    alertDialog.setNegativeButton("Затвори", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alertDialog.show(); 

            }


        });

    }
    public void calcal() {
//        List<User_activity> useract = db.getAllDates();
//
//        for (User_activity ua : useract) {
//            String dates = "Дата: " + ua.getDate() ;
//            // Writing Contacts to log
//            Log.d("дата на рециклиране: ", dates);
//
//            Toast.makeText(getApplicationContext(), dates, Toast.LENGTH_LONG ).show();
//
//            SimpleDateFormat df = new SimpleDateFormat("ddMMMyyyy");
//            Date startDate = df.parse(dates);
//            ColorDrawable green = new ColorDrawable(Color.GREEN);
//            caldroidFragment.setBackgroundDrawableForDate(green, startDate);}

        Bundle args = new Bundle();

        Calendar cal = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        args.putInt(CaldroidFragment.START_DAY_OF_WEEK,
                CaldroidFragment.MONDAY); // MONDAY
        caldroidFragment.setArguments(args);
//
//
//        cal.set(2017, 6, 1); //month -1 poneje po podrazbirane zapochva ot 0
//        Date myDate = cal.getTime();
//
////        listener.onLongClickDate(myDate, getWindow().getDecorView().findViewById(android.R.id.content)); //view -2
//        ColorDrawable green = new ColorDrawable(Color.GREEN);
//        caldroidFragment.setBackgroundDrawableForDate(green, myDate);
//
//        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
//        t.replace(R.id.activity_info_list, caldroidFragment);
//        t.commit();

        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.activity_info_list, caldroidFragment);
        t.commit();

        // Setup listener
        final CaldroidListener listener = new CaldroidListener() {

            @Override
            public void onSelectDate(Date date, View view) {
                Toast.makeText(getApplicationContext(), formatter.format(date),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onChangeMonth(int month, int year) {
                String text = "Месец: " + month + " година: " + year;
                Toast.makeText(getApplicationContext(), text,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClickDate(Date date, View view) {
                Toast.makeText(getApplicationContext(),
                        "Long click " + formatter.format(date),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCaldroidViewCreated() {
                if (caldroidFragment.getLeftArrowButton() != null) {
                    Toast.makeText(getApplicationContext(),
                            "RcycleR активност", Toast.LENGTH_LONG)
                            .show();
                }
            }

        };

        // Setup Caldroid
        caldroidFragment.setCaldroidListener(listener);
    }
}




