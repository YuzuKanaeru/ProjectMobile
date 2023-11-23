package com.belajar.sivosisk

import Kandidat
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

        // Fetch data from API and update the adapter
        fetchDataFromApi()
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
                            "",
                            "",
                            it.gambar ?: "default_image_url"
                        )
                    }
                    updateAdapter(mappedList)
                }
            }

            override fun onFailure(call: Call<List<KandidatResponse>>, t: Throwable) {
                // Handle failure (e.g., show an error message)
            }
        })
    }

    private fun updateAdapter(kandidatList: List<Kandidat>) {
        kandidatAdapter.setData(kandidatList)
    }
}
