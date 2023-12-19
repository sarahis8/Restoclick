package com.example.ourproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import android.content.Intent
import androidx.core.content.ContextCompat

class Subcategory1Activity : AppCompatActivity() {
    var flag1:String="Anniversary" //default value
    var flag2:String="Levant Restaurant"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subcategory1)

        val bundle:Bundle? = intent.extras
        val key1 = bundle?.get("key1")

        val result:TextView=findViewById(R.id.result)

      //  Toast.makeText(applicationContext,key1.toString(),Toast.LENGTH_LONG).show()



     //service

        val Book1:Button=findViewById(R.id.book1)

        Book1.setOnClickListener(){
            startService()
            result.text=flag2
        }



        val spinner1:Spinner=findViewById(R.id.spinner1)
        val spinner2:Spinner=findViewById(R.id.spinner2)


        var option1= arrayOf("Anniversary","Engagement","Gender reveal")
        var option2=arrayOf("Levant Restaurant", "Vinaigrette","Capri Restaurant","Bourj Al Hamam Restaurant",
            "Majdoleen Restaurant","The Lombard Restaurant",
            "La Capital Restaurant","Gusto Italian Restaurant","La Mison Verte Restaurant")

        spinner1.adapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,option1)
        spinner2.adapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,option2)


        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                flag1 = option1.get(p2) //p2 is the index of selected item

            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }


        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                flag2 = option2.get(p2) //p2 is the index of selected item

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