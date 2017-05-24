package com.example.pc.rcycler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class info_list extends AppCompatActivity{
    private ImageView yellow_circle;
    private ImageView green_circle;
    private ImageView blue_circle;

         // според избраната от потребителя опция num приема ст-сти 1 - "Защо да рециклираме?",
    // 2 - "Как да рециклираме правилно", 3 - "Любопитно", и в зависимост от това число се използват различни масиви
    // в Try.java. Целта е да се използват само Try.class, MyAdapter.class, slide.xml, activity_try.xml  за 3-те опции
    //от info_list, и да се избегне създаването на други .class, .xml файлове с цел опростяване на приложението
    // и използване на по-малко памет.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_list);

        yellow_circle = (ImageView) findViewById(R.id.yellowCircle);
        green_circle = (ImageView) findViewById(R.id.greenCircle);
        blue_circle=(ImageView)findViewById(R.id.blueCircle);
        yellow_circle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Number.setNum(1);
                Intent intent1 = new Intent(info_list.this, Try.class);
                startActivity(intent1);
            }
        });
        green_circle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Number.setNum(2);
                Intent intent2=new Intent(info_list.this, Try.class);
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

    }
}
