package com.erpeel.motor.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.erpeel.motor.R
import com.erpeel.motor.home.DashboardFragment
import com.erpeel.motor.home.ProfileFragment
import com.erpeel.motor.home.SellFragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val sellFragment = SellFragment()
        val profileFragment = ProfileFragment()
        val fragmentHome = DashboardFragment()

        setFragment(fragmentHome)

        btn_home.setOnClickListener {
            setFragment(fragmentHome)

            changeIcon(btn_home, R.drawable.ic_motorcycle_active)
            changeIcon(btn_add, R.mipmap.ic_add)
            changeIcon(btn_profile, R.mipmap.ic_profile)
        }

        btn_add.setOnClickListener {
            setFragment(sellFragment)

            changeIcon(btn_home, R.mipmap.ic_motor)
            changeIcon(btn_add, R.drawable.ic_add_circle_active)
            changeIcon(btn_profile, R.mipmap.ic_profile)
        }

        btn_profile.setOnClickListener {
            setFragment(profileFragment)

            changeIcon(btn_home, R.mipmap.ic_motor)
            changeIcon(btn_add, R.mipmap.ic_add)
            changeIcon(btn_profile, R.drawable.ic_person_black_24dp)
        }
    }

    protected fun setFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.layout_frame, fragment)
        fragmentTransaction.commit()
    }

    private fun changeIcon(imageView: ImageView, int: Int){
        imageView.setImageResource(int)
    }
}

