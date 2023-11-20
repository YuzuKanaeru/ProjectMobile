package com.projects3.projectsivosis;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    private Button btnScan;
    private ApiManager apiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        boolean hasVoted = sharedPreferences.getBoolean("hasVoted", false);

        if (hasVoted) {
            // Lakukan sesuatu jika pengguna sudah melakukan vote
        }

        apiManager = new ApiManager(); // Inisialisasi ApiManager

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

    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), result -> {
        if (result.getContents() != null) {
            handleScanResult(result.getContents());
        } else {
            Toast.makeText(this, "Kode tidak valid", Toast.LENGTH_SHORT).show();
        }
    });

    private void handleScanResult(String scanResult) {
        // Simpan hasil pemindaian dalam variabel baru
        String qrCodeData = scanResult;

        // Contoh: Menampilkan dalam Toast
        Toast.makeText(this, "Hasil Pemindaian: " + qrCodeData, Toast.LENGTH_SHORT).show();

        // Panggil API untuk login menggunakan QR code
        apiManager.loginWithQrScan().enqueue(new Callback<List<akun>>() {
            @Override
            public void onResponse(Call<List<akun>> call, Response<List<akun>> response) {
                if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                    // Jika response berhasil dan data ditemukan
                    // Pilih data pertama dengan nis_nip yang sesuai
                    akun user = findUserByNisNip(response.body(), qrCodeData);

                    if (user != null) {
                        // Jika qrCodeData sama dengan nis_nip
                        Intent intent = new Intent(Login.this, Petunjuk.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Login.this, "Data QR code tidak sesuai dengan akun", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Login.this, "Gagal melakukan login dengan QR code", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<akun>> call, Throwable t) {
                Toast.makeText(Login.this, "Gagal melakukan login dengan QR code", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private akun findUserByNisNip(List<akun> userList, String nisNipToFind) {
        for (akun user : userList) {
            if (nisNipToFind.equals(user.getNis_nip())) {
                return user;
            }
        }
        return null;
    }
}
