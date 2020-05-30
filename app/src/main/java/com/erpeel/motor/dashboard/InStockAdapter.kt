package com.erpeel.motor.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.erpeel.motor.R
import com.erpeel.motor.model.MotorTersedia

class InStockAdapter(private var data: List<MotorTersedia>,
                     private val listener: (MotorTersedia) -> Unit)
    : RecyclerView.Adapter<InStockAdapter.LeagueViewHolder>() {

    lateinit var contextAdapter : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView: View = layoutInflater.inflate(R.layout.row_item_in_stock, parent, false)

        return LeagueViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        holder.bindItem(data[position], listener, contextAdapter, position)
    }

    override fun getItemCount(): Int = data.size

    class LeagueViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val tvPlat: TextView = view.findViewById(R.id.tv_plat_motor)
        private val tvJenis: TextView = view.findViewById(R.id.tv_jenis)
        private val tvModel: TextView = view.findViewById(R.id.tv_model)

        private val tvImage: ImageView = view.findViewById(R.id.iv_motor_tersedia)

        fun bindItem(data: MotorTersedia, listener: (MotorTersedia) -> Unit, context : Context, position : Int) {

            tvPlat.text = data.plat
            tvJenis.text = data.jenis
            tvModel.text = data.model


            Glide.with(context)
                .load(data.url)
                .into(tvImage)

            itemView.setOnClickListener {
                listener(data)
            }
        }
    }
}