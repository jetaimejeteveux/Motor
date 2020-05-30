package com.erpeel.motor.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.erpeel.motor.R
import com.erpeel.motor.model.User
import com.erpeel.motor.utils.Preferences
import com.google.firebase.database.*

import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    lateinit var emailLogin: String
    lateinit var passwordLogin: String
    lateinit var mDatabase: DatabaseReference
    lateinit var preferences: Preferences
    val TAG = "SigninActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        preferences = Preferences(this)
        mDatabase = FirebaseDatabase.getInstance().getReference("User")
        preferences.setValues("onboarding", "1")
        if (preferences.getValues("status").equals("1")) {
            finishAffinity()
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }


        btn_login.setOnClickListener {
            emailLogin = edt_email_signin.text.toString()
            passwordLogin = edt_pw_signin.text.toString()
            if (emailLogin.equals("")) {
                edt_email_signin.error = "Silakan tulis username anda"
                edt_email_signin.requestFocus()
            } else if (passwordLogin.equals("")) {
                edt_pw_signin.error = "Silakan tulis password anda"
                edt_pw_signin.requestFocus()
            } else {
                pushLogin(emailLogin, passwordLogin)
            }
        }
        btn_toSignup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }

    private fun pushLogin(emailLogin: String, passwordLogin: String) {
        mDatabase.child(emailLogin).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val user = p0.getValue(User::class.java)
                if(user == null ){
                    Toast.makeText(this@LoginActivity, "User not found", Toast.LENGTH_LONG).show()

                } else {
                    if (user.password.equals(passwordLogin)){
                        Toast.makeText(this@LoginActivity, "Selamat datang", Toast.LENGTH_LONG).show()
                        preferences.setValues("nama",user.nama.toString())
                        preferences.setValues("email",user.email.toString())
                        preferences.setValues("alamat",user.alamat.toString())
                        preferences.setValues("url",user.url.toString())
                        preferences.setValues("status", "1")

                        finishAffinity()

                        startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                    } else {
                        Toast.makeText(this@LoginActivity, "Password anda salah", Toast.LENGTH_LONG).show()
                    }

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@LoginActivity, error.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}
