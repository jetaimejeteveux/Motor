package com.erpeel.motor.activity

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.erpeel.motor.R
import com.erpeel.motor.model.Pemilik

class PemilikAdapter(private var data: List<Pemilik>,
                   private val listener: (Pemilik) -> Unit)
    : RecyclerView.Adapter<PemilikAdapter.LeagueViewHolder>() {

    lateinit var ContextAdapter : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        ContextAdapter = parent.context
        val inflatedView: View = layoutInflater.inflate(R.layout.row_pemilik, parent, false)
        return LeagueViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        holder.bindItem(data[position], listener, ContextAdapter, position)
    }

    override fun getItemCount(): Int = data.size

    class LeagueViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val tvNama: TextView = view.findViewById(R.id.tv_nama_pemilik)

        private val tvImage: ImageView = view.findViewById(R.id.iv_poster_image)
        private val tvAlamat: TextView = view.findViewById(R.id.tv_addr_pemilik)

        fun bindItem(data: Pemilik, listener: (Pemilik) -> Unit, context : Context, position : Int) {

            tvNama.text = data.nama
            tvAlamat.text=data.alamat

            itemView.setOnClickListener {
                listener(data)
            }
        }
    }
}
