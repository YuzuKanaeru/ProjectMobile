package com.belajar.sivosisk

import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class DetailKandidat : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detailkandidat)

        findViewById<Button>(R.id.btnvote).setOnClickListener {
            showWarningAlertDialog()
        }

        // Dapatkan data dari intent
        val kandidatId = intent.getIntExtra("kandidat_id", -1)
        val kandidatName = intent.getStringExtra("kandidat_name")
        val kandidatImageResId = intent.getIntExtra("kandidat_image", -1)
        val kandidatVisi = intent.getStringExtra("kandidat_visi")
        val kandidatMisi = intent.getStringExtra("kandidat_misi")

        val nameTextView: TextView = findViewById(R.id.detail_kandidat_name)
        val imageView: ImageView = findViewById(R.id.detail_kandidat_image)

        nameTextView.text = kandidatName
        imageView.setImageResource(kandidatImageResId)
    }

    private fun showWarningAlertDialog() {
        val builder = AlertDialog.Builder(this, R.style.AlertDIalogTheme)
        val view = LayoutInflater.from(this).inflate(
            R.layout.layout_warning_dialog,
            findViewById(R.id.layoutDialogContainer) as ConstraintLayout?
        )
        builder.setView(view)

        view.findViewById<TextView>(R.id.textTittle).text = "Pemberitahuan"
        view.findViewById<TextView>(R.id.textMessage).text = "Yakin ingin memilih kandidat ini?"
        view.findViewById<Button>(R.id.buttonYes).text = "Iya"
        view.findViewById<Button>(R.id.buttonNo).text = "Tidak"
        view.findViewById<ImageView>(R.id.imgicon).setImageResource(R.drawable.warning)

        val alertDialog = builder.create()

        view.findViewById<Button>(R.id.buttonYes).setOnClickListener {
            alertDialog.dismiss()
            showSuccessAlertDialog()
        }

        view.findViewById<Button>(R.id.buttonNo).setOnClickListener {
            alertDialog.dismiss()
            Toast.makeText(this, "Silahkan pilih kandidat anda", Toast.LENGTH_SHORT).show()
        }

        alertDialog.window?.setBackgroundDrawable(ColorDrawable(0))
        alertDialog.show()
    }

    private fun showSuccessAlertDialog() {
        val builder = AlertDialog.Builder(this, R.style.AlertDIalogTheme)
        val view = LayoutInflater.from(this).inflate(
            R.layout.layout_success_dialog,
            findViewById(R.id.layoutDialogContainer) as ConstraintLayout?
        )
        builder.setView(view)

        view.findViewById<TextView>(R.id.textTittle).text = "Pemberitahuan"
        view.findViewById<TextView>(R.id.textMessage).text = "Terima Kasih Telah Voting"
        view.findViewById<Button>(R.id.buttonAct).text = "Oke"
        view.findViewById<ImageView>(R.id.imgicon).setImageResource(R.drawable.done)

        val alertDialog = builder.create()

        view.findViewById<Button>(R.id.buttonAct).setOnClickListener {
            alertDialog.dismiss()
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
            val editor = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE).edit()
            editor.putBoolean("hasVoted", true)
            editor.apply()
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }

        alertDialog.window?.setBackgroundDrawable(ColorDrawable(0))
        alertDialog.show()
    }
}