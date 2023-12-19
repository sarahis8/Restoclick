package com.example.ourproject
import android.content.ContentResolver
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class CustomersActivity : Activity() {
    val id:Int=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customers)



        val login: Button =findViewById(R.id.login)
        login.setOnClickListener(){
            Toast.makeText(this,"YOU HAVE SUCCESSFULLY SIGNED UP",Toast.LENGTH_LONG).show()

            val values = ContentValues()

            val URL = "content://com.example.MyApplication.CustomersProvider"
            val customers = Uri.parse(URL)
            values.put(
                CustomersProvider.USERNAME,
                (findViewById<View>(R.id.username) as EditText).text.toString()
            )
            values.put(
                CustomersProvider.PASSWORD,
                (findViewById<View>(R.id.password) as EditText).text.toString()
            )

            val uri = contentResolver.insert(
                CustomersProvider.CONTENT_URI, values
            )
            Toast.makeText(baseContext, uri.toString(), Toast.LENGTH_LONG).show()




            intent = Intent(this, MainActivity::class.java)
            intent.putExtra("id_value", id) //to send key using func put-extra to have access to other activity
            startActivity(intent)

        }


    }





}