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

    override fun onBindViewHolder(holder: KandidatViewHolder, position: Int) {
        val kandidat = kandidatList[position]
        holder.namaKandidat.text = kandidat.namaKetua
        holder.namawakil.text = kandidat.namaWakil
        holder.setData(kandidat.gambar)

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

            itemView.setOnClickListener(this)
        }

        fun setData(gambar: String) {
            Glide.with(itemView.context)
                .load(gambar)
                .placeholder(R.drawable.mua)
                .error(R.drawable.error)
                .into(fotoKandidat)
        }

        override fun onClick(view: View) {
        }
    }

    fun setData(newData: List<Kandidat>) {
        this.kandidatList = newData
        notifyDataSetChanged()
    }
}
