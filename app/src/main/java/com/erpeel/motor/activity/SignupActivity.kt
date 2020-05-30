package com.erpeel.motor.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.erpeel.motor.R
import com.erpeel.motor.model.User
import com.erpeel.motor.utils.Preferences
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity(){
    lateinit var emailSignup: String
    lateinit var passwordSignup: String
    lateinit var sNama : String
    lateinit var sAlamat : String
    lateinit var id: String

    private lateinit var mFirebaseDatabase: DatabaseReference
    private lateinit var mFirebaseInstance: FirebaseDatabase
    private lateinit var mDatabase: DatabaseReference

    private lateinit var preferences: Preferences

    val TAG = "SignUpActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        mFirebaseInstance = FirebaseDatabase.getInstance()
        mDatabase = FirebaseDatabase.getInstance().reference
        mFirebaseDatabase = mFirebaseInstance.getReference("User")

        preferences = Preferences(this)
        tv_punya_akun.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        btn_signup.setOnClickListener {

            emailSignup = edt_email_signup.text.toString()
            passwordSignup = edt_pw_signup.text.toString()
            sNama = edt_nama_signup.text.toString()
            sAlamat = edt_addr_signup.text.toString()
            id = edt_id.text.toString()

            if(emailSignup.equals("")){
                edt_email_signup.error = "Silakan isi email"
                edt_email_signup.requestFocus()
            }
            else if(passwordSignup.equals("")){
                edt_pw_signup.error = "Silakan isi password"
                edt_pw_signup.requestFocus()
            }
            else if(sNama.equals("")){
                edt_nama_signup.error = "Silakan isi nama anda"
                edt_nama_signup.requestFocus()
            }
            else if(sAlamat.equals("")) {
                edt_addr_signup.error = "Silakan isi alamat anda"
                edt_addr_signup.requestFocus()
            } else {


                    saveUser(emailSignup, passwordSignup, sNama, sAlamat)

            }
        }
    }
    private fun saveUser(emailSignup: String, passwordSignup: String, sNama: String, sAlamat: String) {

        val user = User()
        user.email = emailSignup
        user.nama = sNama
        user.password = passwordSignup
        user.alamat = sAlamat
        user.id = id


        if (id != null) {
            checkingUsername(id, user)
        }
    }

    private fun checkingUsername(id: String, data: User) {
            mFirebaseDatabase.child(id).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    val user = dataSnapshot.getValue(User::class.java)
                    if (user == null) {
                        mFirebaseDatabase.child(id).setValue(data)

                        preferences.setValues("nama", data.nama.toString())
                        preferences.setValues("saldo", "")
                        preferences.setValues("url", "")
                        preferences.setValues("alamat", data.alamat.toString())
                        preferences.setValues("email", data.email.toString())
                        preferences.setValues("status", "1")

                        val intent = Intent(this@SignupActivity,
                            SignupPhotoScreenActivity::class.java).putExtra("nama", data.nama)
                        startActivity(intent)

                    } else {
                        Toast.makeText(this@SignupActivity, "User sudah digunakan", Toast.LENGTH_LONG).show()

                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@SignupActivity, ""+error.message, Toast.LENGTH_LONG).show()
                }
            })
    }
}



