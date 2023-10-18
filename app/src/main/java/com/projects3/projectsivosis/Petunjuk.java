package com.projects3.projectsivosis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Petunjuk extends AppCompatActivity {

    private Button btnKdnt;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.petunjuk);

        btnKdnt = findViewById(R.id.btnkndt);

        btnKdnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kndt = new Intent(Petunjuk.this,DaftarKandidat.class);
                startActivity(kndt);
            }
        });
    }
}
