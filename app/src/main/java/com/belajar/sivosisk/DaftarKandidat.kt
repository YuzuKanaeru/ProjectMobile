package com.belajar.sivosisk
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DaftarKandidat : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var kandidatAdapter: KandidatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.daftarkandidat)

        // Fetch or create a list of candidates
        val kandidatList: List<Kandidat> = getKandidatList()

        recyclerView = findViewById(R.id.recyclerView)
        kandidatAdapter = KandidatAdapter(this, kandidatList)
        recyclerView.adapter = kandidatAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Handle item click
        kandidatAdapter.setOnItemClickListener(object : KandidatAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                // Handle item click, for example, start DetailKandidat activity
                val selectedKandidat: Kandidat = kandidatList[position]

                val detailIntent = Intent(this@DaftarKandidat, DetailKandidat::class.java)
                detailIntent.putExtra("kandidat_id", selectedKandidat.id)
                detailIntent.putExtra("kandidat_name", selectedKandidat.nama)
                detailIntent.putExtra("kandidat_image", selectedKandidat.gambar)
                startActivity(detailIntent)
            }
        })
    }

    private fun getKandidatList(): List<Kandidat> {
        val kandidatList = mutableListOf<Kandidat>()

        kandidatList.add(Kandidat(1, "Kandidat 1", "", "", R.drawable.kobo))
        kandidatList.add(Kandidat(2, "Kandidat 2", "", "", R.drawable.mua))
        kandidatList.add(Kandidat(3, "Kandidat 3", "", "", R.drawable.hutaomerah))
        kandidatList.add(Kandidat(4, "Kandidat 4", "", "", R.drawable.toshi))

        return kandidatList
    }
}