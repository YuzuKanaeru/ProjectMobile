package com.projects3.projectsivosis;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DaftarKandidat extends AppCompatActivity {

    RecyclerView recyclerView;
    KandidatAdapter kandidatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daftarkandidat);

        // Fetch or create a list of candidates
        List<Kandidat> kandidatList = getKandidatList();

        recyclerView = findViewById(R.id.recyclerView);
        kandidatAdapter = new KandidatAdapter(this, kandidatList);
        recyclerView.setAdapter(kandidatAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Handle item click
        kandidatAdapter.setOnItemClickListener(new KandidatAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Handle item click, for example, start DetailKandidat activity
                Kandidat selectedKandidat = kandidatList.get(position);

                Intent detailIntent = new Intent(DaftarKandidat.this, DetailKandidat.class);
                detailIntent.putExtra("kandidat_id", selectedKandidat.getId());
                detailIntent.putExtra("kandidat_name", selectedKandidat.getNama());
                detailIntent.putExtra("kandidat_image", selectedKandidat.getGambar());
                startActivity(detailIntent);
            }
        });
    }

    private List<Kandidat> getKandidatList() {
        List<Kandidat> kandidatList = new ArrayList<>();

        kandidatList.add(new Kandidat(1, "Kandidat 1","","",R.drawable.kobo));
        kandidatList.add(new Kandidat(2, "Kandidat 2","","",R.drawable.mua));
        kandidatList.add(new Kandidat(3, "Kandidat 3","","",R.drawable.hutaomerah));
        kandidatList.add(new Kandidat(4, "Kandidat 4","","",R.drawable.toshi));

        return kandidatList;
    }
}
