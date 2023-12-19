package com.example.ourproject

import FirstFragment
import SecondFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class infoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        val firstFragment= FirstFragment()
        val secondFragment =SecondFragment()
        val fragment1:Button =findViewById(R.id.aboutusbutton)
        val fragment2:Button =findViewById(R.id.creditbutton)


        fragment1.setOnClickListener(){
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.frame,firstFragment)
                commit()
            }
        }
        fragment2.setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.frame,secondFragment)
                commit()
            }
        }



    }

}