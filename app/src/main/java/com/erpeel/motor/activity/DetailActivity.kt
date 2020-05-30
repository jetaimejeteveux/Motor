package com.erpeel.motor.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.erpeel.motor.R
import com.erpeel.motor.model.MotorTersedia
import com.erpeel.motor.model.Pemilik
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    lateinit var mDatabase: DatabaseReference
    private var dataList = ArrayList<Pemilik>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val data = intent.getParcelableExtra<MotorTersedia>("data")

        mDatabase = FirebaseDatabase.getInstance().getReference("Motor_tersedia")
            .child(data.plat.toString())
            .child("pemilik")

        tv_plat_motor_detail.text = data.plat
        tv_jenis_detail.text = data.jenis
        tv_harga_motor.text = data.harga
        tv_model_detail.text = data.model
        tv_tahun_motor.text = data.tahun
        tv_kapasitas_mesin_detail.text = data.kapasitas_mesin

        Glide.with(this)
            .load(data.url)
            .into(iv_motor_detail)

        btn_pilih_bangku.setOnClickListener {
            val intent = Intent(this@DetailActivity,
                CheckoutActivity::class.java).putExtra("data", data)
            startActivity(intent)
        }

        iv_back.setOnClickListener {
            finish()
        }

        rv_pemilik.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        getData()
    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                dataList.clear()
                for (getdataSnapshot in dataSnapshot.children) {

                    val pemilik = getdataSnapshot.getValue(Pemilik::class.java)
                    dataList.add(pemilik!!)
                }
                rv_pemilik.adapter = PemilikAdapter(dataList) {
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@DetailActivity, ""+error.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}
