package com.projects3.projectsivosis;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class KandidatAdapter extends RecyclerView.Adapter<KandidatAdapter.KandidatViewHolder> {
    private List<Kandidat> kandidatList;
    private Context context;
    private OnItemClickListener itemClickListener;

    public KandidatAdapter(Context context, List<Kandidat> kandidatList) {
        this.context = context;
        this.kandidatList = kandidatList;
    }

    @NonNull
    @Override
    public KandidatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kandidat, parent, false);
        return new KandidatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KandidatViewHolder holder, int position) {
        Kandidat kandidat = kandidatList.get(position);
        holder.namaKandidat.setText(kandidat.getNama());
        holder.setData(kandidat.getGambar());
        holder.visiKandidat.setText(kandidat.getVisi());
        holder.misiKandidat.setText(kandidat.getMisi());

        // Set the click listener on the itemView
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(position);
                }
            }
        });
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return kandidatList.size();
    }

    public static class KandidatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView fotoKandidat;
        TextView namaKandidat;
        TextView visiKandidat;
        TextView misiKandidat;

        public KandidatViewHolder(@NonNull View itemView) {
            super(itemView);
            fotoKandidat = itemView.findViewById(R.id.fotoKandidat);
            namaKandidat = itemView.findViewById(R.id.namaKandidat);
            visiKandidat = itemView.findViewById(R.id.visiKandidat);
            misiKandidat = itemView.findViewById(R.id.misiKandidat);

            // Set this as the OnClickListener for the itemView
            itemView.setOnClickListener(this);
        }

        public void setData(int gambar) {
            fotoKandidat.setImageResource(gambar);
        }

        // Implement the OnClickListener method
        @Override
        public void onClick(View view) {
            // You can handle item click here if needed
        }
    }
}
