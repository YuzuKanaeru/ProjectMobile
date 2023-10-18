package com.projects3.projectsivosis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DaftarKandidat extends AppCompatActivity {

    private Button btnKndt1;

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daftarkandidat);

        btnKndt1 = findViewById(R.id.btnkndt1);

        btnKndt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kekndt1 = new Intent(DaftarKandidat.this,DetailKandidat.class);
                startActivity(kekndt1);
                }

        });
    }
}
