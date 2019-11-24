package com.example.mysharedpreferences

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity2.*

class Activity2_Main: AppCompatActivity() {
    private var userMax = 6

    private var usercountkey = "users_in"

    private var userpostkey = "Users_"

    private var usersIn = 0


override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity2)



            val sharedPreferences = getSharedPreferences("user_info", Context.MODE_PRIVATE)

            usersIn = sharedPreferences.getInt(usercountkey, 0)
            Log.d("TAG_LOG", "Users in $usersIn")
            if (usersIn > 0) {
                val myUser = StringBuilder()
                for (i in 0 until usersIn) {
                    var user = sharedPreferences.getString(userpostkey + i, "")
                    myUser.append(user + "\n")
                }
                user_View.text = myUser.toString()
            } else {
                user_View.text = "no users"
            }

            Delete_User.setOnClickListener{
                MainActivity.editor.remove(userpostkey + (usersIn -1))
                MainActivity.editor.putInt(usercountkey, usersIn-1)
                MainActivity.editor.commit()

        }

    usersIn--
    }

}