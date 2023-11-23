package com.belajar.sivosisk

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class KandidatAdapter(private val context: Context, private val kandidatList: List<Kandidat>) :
    RecyclerView.Adapter<KandidatAdapter.KandidatViewHolder>() {

    private var itemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.itemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KandidatViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_kandidat, parent, false)
        return KandidatViewHolder(view)
    }

    override fun onBindViewHolder(holder: KandidatViewHolder, position: Int) {
        val kandidat = kandidatList[position]
        holder.namaKandidat.text = kandidat.nama
        holder.setData(kandidat.gambar)

        // Set the click listener on the itemView
        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return kandidatList.size
    }

    class KandidatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var fotoKandidat: ImageView
        var namaKandidat: TextView

        init {
            fotoKandidat = itemView.findViewById(R.id.fotoKandidat)
            namaKandidat = itemView.findViewById(R.id.namaKandidat)

            // Set this as the OnClickListener for the itemView
            itemView.setOnClickListener(this)
        }

        fun setData(gambar: Int) {
            fotoKandidat.setImageResource(gambar)
        }

        // Implement the OnClickListener method
        override fun onClick(view: View) {
            // You can handle item click here if needed
        }
    }
}
