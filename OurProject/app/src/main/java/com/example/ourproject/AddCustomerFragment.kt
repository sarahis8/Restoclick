package com.example.ourproject

import android.app.Activity
import android.content.ContentValues
import android.net.Uri
import android.view.View
import android.widget.*
import androidx.fragment.app.DialogFragment
import android.os.Bundle;
import android.widget.Button



class AddCustomerFragment: Activity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_add_dialog)

    val btn: Button = findViewById(R.id.insertBt)
        val values = ContentValues()

        val URL = "content://com.example.MyApplication.CustomersProvider"
        val customers = Uri.parse(URL)

     btn.setOnClickListener {

         val q="sarah"
         var c = contentResolver.query(customers, null, "username LIKE ?", Array(1){ q }, null)



          if (c != null) {
              if (c?.moveToFirst()) {
                  do {

                      Toast.makeText(this,
                           c.getString(c.getColumnIndex(CustomersProvider.USERNAME)) + ", " + c.getString(c.getColumnIndex(
                              CustomersProvider.PASSWORD)),
                          Toast.LENGTH_SHORT).show()
                  } while (c.moveToNext())
              }
          }


         val nameET : EditText = findViewById(R.id.username)
         val numberET : EditText = findViewById(R.id.password)

/*         feedback(nameET.text.toString(),numberET.text.toString())
        */
     }





    }

}


