package com.dawa.mobilehealth;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class QrScanActivity extends AppCompatActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

        IntentIntegrator  intergrator = new IntentIntegrator(this);
        intergrator.setCaptureActivity(CaptureAct.class);
        intergrator.setOrientationLocked(false);
        intergrator.setPrompt("Scanning Code");
        intergrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultcode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultcode,data);
        if(result != null) {
            if (result.getContents() != null) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(result.getContents());
                builder.setTitle("Scanning Results");
                builder.setPositiveButton("Scan Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       scanCode();
                    }


                }).setNegativeButton("Finish", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
            else{
                Toast.makeText(this,"No Result", Toast.LENGTH_LONG).show();
            }
        }else{
            super.onActivityResult(requestCode,resultcode,data);
        }

    }

    private void scanCode() {
        IntentIntegrator  intergrator = new IntentIntegrator(this);
        intergrator.setCaptureActivity(CaptureAct.class);
        intergrator.setOrientationLocked(false);
        intergrator.setPrompt("Scanning Code");
        intergrator.initiateScan();
    }

}
