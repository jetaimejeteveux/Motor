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
import com.erpeel.motor.model.MotorTerjual



class SoldAdapter(private var data: List<MotorTerjual>,
                  private val listener: (MotorTerjual) -> Unit)
    : RecyclerView.Adapter<SoldAdapter.LeagueViewHolder>() {

    lateinit var ContextAdapter : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        ContextAdapter = parent.context
        val inflatedView: View = layoutInflater.inflate(R.layout.row_item_terjual, parent, false)

        return LeagueViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        holder.bindItem(data[position], listener, ContextAdapter, position)
    }

    override fun getItemCount(): Int = data.size

    class LeagueViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val tvPlat: TextView = view.findViewById(R.id.tv_plat_motor_terjual)
        private val tvJenis: TextView = view.findViewById(R.id.tv_jenis_terjual)
        private val tvModel: TextView = view.findViewById(R.id.tv_model_terjual)

        private val tvImage: ImageView = view.findViewById(R.id.iv_motor_terjual)

        fun bindItem(data: MotorTerjual, listener: (MotorTerjual) -> Unit, context : Context, position : Int) {

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

