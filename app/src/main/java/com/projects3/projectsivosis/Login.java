package com.projects3.projectsivosis;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class Login extends AppCompatActivity {


    private Button btnLogin, btnScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        btnScan = findViewById(R.id.btnScan);

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(Login.this);
                integrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            // Hasil pemindaian QR code
            String qrCodeData = result.getContents();
            if (qrCodeData.equals("12345")) {
                Intent intent = new Intent(Login.this,Petunjuk.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Kode tidak valid", Toast.LENGTH_SHORT).show();
                // Proses data QR code sesuai kebutuhan aplikasi Anda
            }
            } else{
                super.onActivityResult(requestCode, resultCode, data);
            }
        }



    private void Scanner() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Volume up to Flash on");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(StartScan.class);
        launcher.launch(options);
    }

    ActivityResultLauncher<ScanOptions> launcher = registerForActivityResult(new ScanContract(), result -> {
        if (result.getContents() != null ){
            AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
            builder.setTitle("QR-SCANNER RESULT");
            builder.setMessage(result.getContents());
            builder.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).show();
        }
    });
}