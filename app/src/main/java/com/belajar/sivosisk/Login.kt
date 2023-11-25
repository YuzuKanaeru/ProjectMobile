package com.belajar.sivosisk

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AppCompatActivity
import com.journeyapps.barcodescanner.CaptureActivity
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {
    private var btnScan: Button? = null
    private var apiManager: ApiManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val sharedPreferences: SharedPreferences =
            getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val hasVoted: Boolean = sharedPreferences.getBoolean("hasVoted", false)

        if (hasVoted) {
        }

        apiManager = ApiManager()

        btnScan = findViewById<Button>(R.id.btnScan)
        btnScan!!.setOnClickListener { v: View? -> scanCode() }
    }

    private val barLauncher: ActivityResultLauncher<ScanOptions> =
        registerForActivityResult(ScanContract()) { result: ScanIntentResult? ->
            result?.let {
                val scanResult = it.contents
                if (!scanResult.isNullOrBlank()) {
                    handleScanResult(scanResult)
                } else {
                    Toast.makeText(this@Login, "Kode tidak valid", Toast.LENGTH_SHORT).show()
                }
            }
        }

    private fun scanCode() {
        val options = ScanOptions().apply {
            setPrompt("Volume up to flash on")
            setBeepEnabled(false)
            setOrientationLocked(true)
            captureActivity = CaptureAct::class.java
        }
        barLauncher.launch(options)
    }

    private fun handleScanResult(scanResult: String) {
        Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()

        apiManager?.loginWithQrScan()?.enqueue(object : Callback<List<akun>> {
            override fun onResponse(call: Call<List<akun>>, response: Response<List<akun>>) {
                if (response.isSuccessful && response.body() != null && response.body()!!.isNotEmpty()) {
                    val user = findUserByNisNip(response.body()!!, scanResult)

                    if (user != null) {
                        val intent = Intent(this@Login, Petunjuk::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            this@Login,
                            "Data QR code tidak sesuai dengan akun",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        this@Login,
                        "Gagal melakukan login dengan QR code",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<akun>>, t: Throwable?) {
                Toast.makeText(
                    this@Login,
                    "Gagal melakukan login dengan QR code",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun findUserByNisNip(userList: List<akun>, nisNipToFind: String): akun? {
        for (user in userList) {
            if (nisNipToFind == user.nis_nip) {
                return user
            }
        }
        return null
    }
}
