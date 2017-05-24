package com.example.pc.rcycler;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class QRscanner extends AppCompatActivity {
    private AlertDialog dialog;

    // private  Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_qrscanner);
        //getSupportActionBar().setTitle("Сканирай QR код");

        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Сканирай!");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(false);
        integrator.initiateScan();

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        final IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        int qr_result = Integer.parseInt(result.getContents());
        if (result != null) {

            if (qr_result == 1 || qr_result == 2 || qr_result == 3 || qr_result == 4 || qr_result == 5 ||
                    qr_result == 6 || qr_result == 7 || qr_result == 8 || qr_result == 9 ||
                    qr_result == 10 || qr_result == 11 || qr_result == 12 || qr_result == 13 || qr_result == 14||
                    qr_result == 15 || qr_result == 16 || qr_result == 17) {

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Резултат от сканирането:");
                builder.setMessage("Поздравления! Вие успешно сканирахте обект №" +
                        qr_result + "! Желаете ли да продължите напред към видеоклиповете?");
                builder.setPositiveButton("Да, разбира се!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // String value = result.getContents();
                        Intent intent = new Intent(QRscanner.this, YouTube.class);
                        intent.putExtra("value", result.getContents());
                        startActivity(intent);

                    }
                });
                builder.setNegativeButton("Не, благодаря!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(QRscanner.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
                dialog = builder.create();
                dialog.show();


            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Резултат от сканирането");
                builder.setMessage(result.getContents() +
                        "\nРезултатът не съвпада с обозначенията на обектите върху RcycleR картата");
                builder.setCancelable(true);
                dialog = builder.create();
                dialog.show();


            }
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Резултат от сканирането:");
            builder.setMessage("Вие отказахте сканирането!");
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }

    }

}
   // }
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data){
//        IntentResult result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
//        if(result!=null){
//            if(result.getContents()==null){
//                Toast.makeText(QRscanner.this, "Вие отказахте сканирането", Toast.LENGTH_LONG).show();}
//            else
//            {Toast.makeText(QRscanner.this,result.getContents(),Toast.LENGTH_LONG).show();}
//
//        }
//        else
//
//
//            super.onActivityResult(requestCode,resultCode,data);
//    }



//}
