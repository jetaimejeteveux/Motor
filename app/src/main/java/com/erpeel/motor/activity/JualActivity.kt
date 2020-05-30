package com.erpeel.motor.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.erpeel.motor.R
import kotlinx.android.synthetic.main.activity_jual.*

class JualActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jual)
        btn_daftarkan.setOnClickListener {
            Toast.makeText(this, "Berhasil ditambahkan", Toast.LENGTH_LONG).show()
            startActivity(Intent(this,HomeActivity::class.java))
        }
    }
}
