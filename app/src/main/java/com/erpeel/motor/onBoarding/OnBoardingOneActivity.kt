package com.erpeel.motor.onBoarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.erpeel.motor.R
import com.erpeel.motor.activity.LoginActivity
import com.erpeel.motor.utils.Preferences
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_on_boarding_one.*
import kotlinx.android.synthetic.main.activity_on_boarding_two.*

class OnBoardingOneActivity : AppCompatActivity(), View.OnClickListener{
    lateinit var  preferences: Preferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_one)
       button_lanjut_ob1.setOnClickListener(this)
        preferences = Preferences(this)
        if(preferences.getValues("onboarding").equals("1")){
            finishAffinity()
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.button_lanjut_ob1 -> startActivity(Intent(this, OnBoardingTwoActivity::class.java))
        }
    }
}



