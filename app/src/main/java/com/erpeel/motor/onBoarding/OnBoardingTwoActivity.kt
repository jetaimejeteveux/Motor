package com.erpeel.motor.onBoarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.erpeel.motor.R
import com.erpeel.motor.activity.LoginActivity
import kotlinx.android.synthetic.main.activity_on_boarding_one.*
import kotlinx.android.synthetic.main.activity_on_boarding_two.*

class OnBoardingTwoActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_two)
        button_lanjut_ob2.setOnClickListener{
            startActivity(Intent(this, OnBoardingThreeActivity::class.java))
        }
        button_skip_ob2.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}




