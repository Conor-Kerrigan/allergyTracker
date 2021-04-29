package com.vogella.android.allergytrackerapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    //initialise variable
    Button btScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //assign variable
        btScan = findViewById(R.id.bt_scan);

        btScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //initalise intent integrator
                IntentIntegrator intentIntegrator = new IntentIntegrator(
                        MainActivity.this
                );
                //set prompt text
                intentIntegrator.setPrompt("For flash use volume up key");

                //set beep
                intentIntegrator.setBeepEnabled(true);

                //Locked orientation
                intentIntegrator.setOrientationLocked(true);

                //set capture activity
                intentIntegrator.setCaptureActivity(Capture.class);

                //initialise scan
                intentIntegrator.initiateScan();


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Initialise intent result
        IntentResult intentResult = IntentIntegrator.parseActivityResult(
                requestCode, resultCode, data
        );
        //check condition

        //when result content is null
        //display toast
        if (intentResult.getContents() != null) {

            //when result content is not null
            //initialisealert dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(
                    MainActivity.this
            );

            //set title
            builder.setTitle("Result");

            //set message
            builder.setMessage(intentResult.getContents());

            //set positive button
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //dismiss dialog
                    dialog.dismiss();
                }
            });
            //show alert dialog
            builder.show();
        } else Toast.makeText(getApplicationContext()
                , "Oops, you did not scan anything", Toast.LENGTH_SHORT)
                .show();


    }
}