package com.belajar.sivosisk

import Kandidat
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class KandidatAdapter(private val context: Context, private var kandidatList: List<Kandidat>) :
    RecyclerView.Adapter<KandidatAdapter.KandidatViewHolder>() {

    private var itemClickListener: OnItemClickListener? = null

    // Inside KandidatAdapter
    interface OnItemClickListener {
        fun onItemClick(position: Int, kandidat: Kandidat)
    }


    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.itemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KandidatViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_kandidat, parent, false)
        return KandidatViewHolder(view)
    }

    // Inside KandidatAdapter
    override fun onBindViewHolder(holder: KandidatViewHolder, position: Int) {
        val kandidat = kandidatList[position]
        holder.namaKandidat.text = kandidat.namaKetua
        holder.namawakil.text = kandidat.namaWakil
        holder.setData(kandidat.gambar)

        // Set the click listener on the itemView
        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClick(position, kandidat)
        }
    }


    override fun getItemCount(): Int {
        return kandidatList.size
    }

    class KandidatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var fotoKandidat: ImageView
        var namaKandidat: TextView
        var namawakil : TextView


        init {
            fotoKandidat = itemView.findViewById(R.id.fotoKandidat)
            namaKandidat = itemView.findViewById(R.id.namaKandidat)
            namawakil = itemView.findViewById(R.id.namawakil)

            // Set this as the OnClickListener for the itemView
            itemView.setOnClickListener(this)
        }

        fun setData(gambar: String) {
            // Use a library like Glide to load images efficiently
            Glide.with(itemView.context)
                .load(gambar)
                .placeholder(R.drawable.mua) // placeholder image
                .error(R.drawable.error) // error image
                .into(fotoKandidat)
        }

        override fun onClick(view: View) {
            // Handle item click if needed
        }
    }

    fun setData(newData: List<Kandidat>) {
        this.kandidatList = newData
        notifyDataSetChanged()
    }
}
