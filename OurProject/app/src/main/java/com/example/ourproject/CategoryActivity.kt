package com.example.ourproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import java.util.*

class CategoryActivity : AppCompatActivity() {
    val key1:Int=1
    val key2:Int=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        //Previous screen will be opened using this code
        val bundle:Bundle? = intent.extras
        val id = bundle?.get("id_value")

       // Toast.makeText(applicationContext,id.toString(),Toast.LENGTH_LONG).show()


        //To open Next Screen

        val special: Button = findViewById(R.id.specialbutton)
        val couple: Button = findViewById(R.id.couplebutton)


       couple.setOnClickListener() {
            intent = Intent(this, Subcategory1Activity::class.java)
            intent.putExtra("key1",key1) //to send key using func put-extra to have access to other activity
            startActivity(intent)
        }


        special.setOnClickListener() {
            intent = Intent(this, Subcategory2Activity::class.java)
            intent.putExtra("key2",key2) //to send key using func put-extra to have access to other activity
            startActivity(intent)
        }
    }


    }
