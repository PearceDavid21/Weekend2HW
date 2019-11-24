package com.example.mysharedpreferences

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity2.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    companion object{
        lateinit var editor: SharedPreferences.Editor
    }
        private var userMax = 6

        private var usercountkey = "users_in"

        private var userpostkey = "Users_"

        private var usersIn = 0


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            val sharedPreferences = getSharedPreferences("user_info", Context.MODE_PRIVATE)
            editor=sharedPreferences.edit()
            new_user_button.setOnClickListener {
                val name = username_edittext.text.toString()

                val number = Integer.parseInt(phone_number_edittext.text.toString())

                usersIn = sharedPreferences.getInt(usercountkey, 0)
                Log.d("TAG_LOG", "Users in $usersIn")
                if (usersIn == userMax) {
                    Toast.makeText(this.getApplicationContext(), "max reached", Toast.LENGTH_LONG)
                        .show()
                    clearText()
                } else {
                    var newUser = "UserName:" + name + "|" + "PhoneNumber:" + number
                    editor = sharedPreferences.edit()
                    editor.putString(userpostkey + (usersIn + 1), newUser)
                    editor.putInt(usercountkey, (usersIn + 1))
                    editor.commit()
                    Toast.makeText(
                        this.getApplicationContext(),
                        "users in ${usersIn + 1}",
                        Toast.LENGTH_LONG
                    )
                        .show()
                    clearText()
                }
            }

            show_saves_button.setOnClickListener {
               // displayUsers()
                val userIntent = Intent(this, Activity2_Main::class.java)
                startActivity(userIntent)
            }

        }

        override fun onResume() {
            super.onResume()
           // displayUsers()
        }

        private fun clearText() {
            username_edittext.text.clear()
            phone_number_edittext.text.clear()
        }

       /* private fun displayUsers() {
            val sharedPreferences = getSharedPreferences("user_info", Context.MODE_PRIVATE)

            usersIn = sharedPreferences.getInt(usercountkey, 0)
            Log.d("TAG_LOG", "Users in $usersIn")
            if (usersIn > 0) {
                val myUser = StringBuffer()
                for (i in 0 until usersIn) {
                    var user = sharedPreferences.getString(userpostkey + i, "Received")
                    myUser.append(user + "\n")
                }
                user_View.text = myUser.toString()
            } else {
                user_View.text = "no users"
            }
        }*/
    }