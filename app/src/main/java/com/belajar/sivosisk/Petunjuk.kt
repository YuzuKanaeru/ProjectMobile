package com.belajar.sivosisk

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Petunjuk : AppCompatActivity() {
    private var btnKdnt: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.petunjuk)

        btnKdnt = findViewById(R.id.btnkndt)
        btnKdnt?.setOnClickListener {
            val kndt = Intent(this@Petunjuk, DaftarKandidat::class.java)
            startActivity(kndt)
        }
    }
}
