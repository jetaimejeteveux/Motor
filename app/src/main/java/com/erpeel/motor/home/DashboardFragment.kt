package com.erpeel.motor.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import com.erpeel.motor.R
import com.erpeel.motor.activity.DetailActivity
import com.erpeel.motor.dashboard.InStockAdapter
import com.erpeel.motor.dashboard.SoldAdapter
import com.erpeel.motor.model.MotorTerjual
import com.erpeel.motor.model.MotorTersedia
import com.erpeel.motor.utils.Preferences
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DashboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DashboardFragment : Fragment() {

    private lateinit var preferences: Preferences
    lateinit var mDatabaseMotor: DatabaseReference
    lateinit var mDatabaseMotorTerjual: DatabaseReference

    private var dataListTersedia = ArrayList<MotorTersedia>()
    private var dataListTerjual = ArrayList<MotorTerjual>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preferences = Preferences(activity!!.applicationContext)
        mDatabaseMotor = FirebaseDatabase.getInstance().getReference("Motor_tersedia")
        mDatabaseMotorTerjual = FirebaseDatabase.getInstance().getReference("motor_terjual")

        tv_nama_user.text = preferences.getValues("nama")


        Glide.with(this)
            .load(preferences.getValues("url"))
            .apply(RequestOptions.circleCropTransform())
            .into(iv_user)

        Log.v("tamvan", "url "+preferences.getValues("url"))

        rv_in_stock.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_terjual.layoutManager = LinearLayoutManager(context!!.applicationContext)
        getData()

    }

    private fun getData() {
        mDatabaseMotor.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                dataListTersedia.clear()
                for (getdataSnapshot in dataSnapshot.children) {

                    val motorTersedia = getdataSnapshot.getValue(MotorTersedia::class.java)
                    dataListTersedia.add(motorTersedia!!)
                }


                    rv_in_stock.adapter = InStockAdapter(dataListTersedia) {
                        val intent = Intent(
                            context,
                            DetailActivity::class.java
                        ).putExtra("data", it)
                        startActivity(intent)
                    }
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, ""+error.message, Toast.LENGTH_LONG).show()
            }
        })
        mDatabaseMotorTerjual.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                dataListTerjual.clear()
                for (getdataSnapshot in dataSnapshot.children) {

                    val motorTerjual = getdataSnapshot.getValue(MotorTerjual::class.java)
                    dataListTerjual.add(motorTerjual!!)
                }


                if (dataListTersedia.isNotEmpty()) {
                    rv_terjual.adapter = SoldAdapter(dataListTerjual) {
                        val intent = Intent(
                            context,
                            DetailActivity::class.java
                        ).putExtra("data", it)
                        startActivity(intent)
                    }
                }

            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, ""+error.message, Toast.LENGTH_LONG).show()
            }
        })
    }



}
