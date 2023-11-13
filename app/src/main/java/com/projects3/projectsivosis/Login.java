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

    Button btnScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        btnScan = findViewById(R.id.btnScan);
        btnScan.setOnClickListener(v -> {
            scanCode();
        });
    }

    private void scanCode() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Volume up to flash on");
        options.setBeepEnabled(false);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLauncher.launch(options);
    }


    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), result ->
    {
        if (result.getContents() != null) {
            handleScanResult(result.getContents());
        } else {
            Toast.makeText(this, "Kode tidak valid", Toast.LENGTH_SHORT).show();
        }
    });

    private void handleScanResult(String scanResult) {
        // Mengecek apakah hasil pemindaian adalah QR code dan nilainya sesuai dengan yang diinginkan
        if (scanResult.equals("12345")) {
            Intent nextActivityIntent = new Intent(Login.this, Petunjuk.class);
            nextActivityIntent.putExtra("barcodeResult", scanResult);

            // Memulai activity selanjutnya
            startActivity(nextActivityIntent);
        } else {
            // Jika bukan QR code atau nilainya tidak sesuai, mungkin jalankan logika lain atau tampilkan pesan
            Toast.makeText(this, "Kode tidak valid", Toast.LENGTH_SHORT).show();
        }
    }
}
