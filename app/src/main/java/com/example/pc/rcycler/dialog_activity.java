//package com.example.pc.rcycler;
//
///**
// * Created by Victoria on 4.6.2017 г..
// */
//
//import android.app.Activity;
//import android.app.Dialog;
//import android.content.Context;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import java.util.List;
//
//public class dialog_activity extends Activity {
//
//    final Context context = this;
//    private Button button;
//
//    public void onCreate(Bundle savedInstanceState) {
//        DatabaseHandler db = new DatabaseHandler(this);
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.custom);
//
////        button = (Button) findViewById(R.id.buttonShowCustomDialog);
//
//        // add button listener
////        button.setOnClickListener(new OnClickListener() {
////
////            @Override
////            public void onClick(View arg0) {
//
//                // custom dialog
//                final Dialog dialog = new Dialog(context);
//                dialog.setContentView(R.layout.custom);
//                dialog.setTitle("Хронология");
//
//                // set the custom dialog components - text, image and button
//                TextView text = (TextView) dialog.findViewById(R.id.text);
//
//        List<User_activity> useract = db.getAllActivities();
//        for (User_activity ua : useract) {
//            String str = "Id: "+ua.getID()+" ,Дата: " + ua.getDate() + " ,Час: " + ua.getTime() + ", Рециклиран материал: "+ ua.getMaterial();
//            // Writing Contacts to log
//            Log.d("Name: ", str);
//            text.setText(str);
//        }
////
////                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
////                // if button is clicked, close the custom dialog
////                dialogButton.setOnClickListener(new OnClickListener() {
////                    @Override
////                    public void onClick(View v) {
////                        dialog.dismiss();
////                    }
////                });
//
//                dialog.show();
//            }
//
//}
