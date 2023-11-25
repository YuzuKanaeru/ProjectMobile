package com.belajar.sivosisk

import Kandidat
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DaftarKandidat : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var kandidatAdapter: KandidatAdapter

    private val apiManager = ApiManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.daftarkandidat)

        recyclerView = findViewById(R.id.recyclerView)
        kandidatAdapter = KandidatAdapter(this, mutableListOf())

        recyclerView.adapter = kandidatAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchDataFromApi()
        kandidatAdapter.setOnItemClickListener(object : KandidatAdapter.OnItemClickListener {
            override fun onItemClick(position: Int, kandidat: Kandidat) {
                val intent = Intent(this@DaftarKandidat, DetailKandidat::class.java).apply {
                    putExtra("kandidat_id", kandidat.id)
                    putExtra("kandidat_name", kandidat.namaKetua)
                    putExtra("kandidat_wakil", kandidat.namaWakil)
                    putExtra("kandidat_image", kandidat.gambar)
                    putExtra("kandidat_visi", kandidat.visi)
                    putExtra("kandidat_misi", kandidat.misi)
                }
                startActivity(intent)
            }
        })

    }

    private fun fetchDataFromApi() {
        apiManager.getDataKandidat().enqueue(object : Callback<List<KandidatResponse>> {
            override fun onResponse(
                call: Call<List<KandidatResponse>>,
                response: Response<List<KandidatResponse>>
            ) {
                if (response.isSuccessful) {
                    val kandidatList = response.body() ?: emptyList()
                    val mappedList = kandidatList.map {
                        Kandidat(
                            it.id_kandidat,
                            it.nama_ketua ?: "",
                            it.nama_wakil ?: "",
                            it.visi ?: "",
                            it.misi ?: "",
                            it.gambar ?: "default_image_url"
                        )
                    }
                    updateAdapter(mappedList)
                }
            }

            override fun onFailure(call: Call<List<KandidatResponse>>, t: Throwable) {
            }
        })
    }

    private fun updateAdapter(kandidatList: List<Kandidat>) {
        kandidatAdapter.setData(kandidatList)
    }
}
