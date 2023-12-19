package com.example.ourproject



import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.Button


class LoginActivity() : AppCompatActivity() {

    var id=1

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_add_dialog)
        val bundle:Bundle? = intent.extras
         bundle?.get("id_value")


        val login: Button =findViewById(R.id.insertBt)
        login.setOnClickListener(){
        intent = Intent(this, CategoryActivity::class.java)
        intent.putExtra("id_value", id) //to send key using func put-extra to have access to other activity
        startActivity(intent)}
    }

}
