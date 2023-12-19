package com.example.ourproject

import FirstFragment
import SecondFragment
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import java.util.*

class Subcategory2Activity : AppCompatActivity() {

    var flag11:String="Anniversary" //default value
    var flag22:String="Levant Restaurant"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subcategory2)

        val bundle:Bundle? = intent.extras
        val key2= bundle?.get("key2")
        val result:TextView=findViewById(R.id.result2)
        //Toast.makeText(applicationContext,key2.toString(),Toast.LENGTH_LONG).show()

        //service
        val Book2:Button=findViewById(R.id.book2)
        Book2.setOnClickListener(View.OnClickListener {

            startService()
            result.text=flag22
        })




        val spinner11:Spinner=findViewById(R.id.spinner1)
        val spinner22:Spinner=findViewById(R.id.spinner2)

        var option11= arrayOf("Birthday Party","Graduation Party")
        var option22=arrayOf("Gusto Italian Restaurant", "Luxury Restaurant and Cafe","Luca Steak House","Salt Restaurant",
            "Solaya Restaurant","Levant Restaurant",
            "ThreeSixty","Cinco De Mayo Restaurant")

        spinner11.adapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,option11)
        spinner22.adapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,option22)


        spinner11.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                flag11 = option11.get(p2) //p2 is the index of selected item (in our course only p2 is used)

            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }


        spinner22.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                flag22 = option22.get(p2) //p2 is the index of selected item

            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        }

    fun startService(){
        val servicelntent = Intent(this, ForegroundService::class.java)
        servicelntent.putExtra("inputExtra","Foreground Service Example in Android" )
        ContextCompat.startForegroundService(this, servicelntent)
    }
}