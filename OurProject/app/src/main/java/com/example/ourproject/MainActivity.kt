package com.example.ourproject


import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    val id: Int = 1
    var cName: String = ""
    var cNumber: String = ""
    companion object {
        private const val READ_CONTACT_PERMISSION_CODE = 111
        private const val WRITE_CONTACT_PERMISSION_CODE = 112
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val login: Button = findViewById(R.id.loginbutton)
        val con: Button = findViewById(R.id.continuebutton)
        val signup: Button = findViewById(R.id.signupbutton)

        login.setOnClickListener() {
            intent = Intent(this, CustomersActivity::class.java)
            intent.putExtra("id_value", id) //to send key using func put-extra to have access to other activity
            startActivity(intent)
        }
        con.setOnClickListener() {
            intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra("id_value", id) //to send key using func put-extra to have access to other activity
            startActivity(intent)
        }

        signup.setOnClickListener() {
            intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("id_value",id) //to send key using func put-extra to have access to other activity
            startActivity(intent)
        }


        //sign up permissions
        checkPermission(android.Manifest.permission.READ_CONTACTS, READ_CONTACT_PERMISSION_CODE)
        checkPermission(android.Manifest.permission.WRITE_CONTACTS, WRITE_CONTACT_PERMISSION_CODE)


    }

    public fun feedback(_Name: String, _Number: String) {
        cName = _Name
        cNumber = _Number

/*
        insertContactPhoneNumber(
            _Number,
            _Name
        )*/

    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        /*   var t1:String="itm 1 selected"*/
        when (item.itemId) {
            R.id.info -> {
                intent = Intent(this, infoActivity::class.java)
                intent.putExtra(
                    "id_value",
                    id
                ) //to send key using func put-extra to have access to other activity
                startActivity(intent)
            }
        }
        return true;
    }

    private fun checkPermission(permission: String, requestCode: Int) {
        if (ContextCompat.checkSelfPermission(
                this@MainActivity,
                permission
            ) == PackageManager.PERMISSION_DENIED
        ) {
            // Requesting the permission
            ActivityCompat.requestPermissions(this@MainActivity, arrayOf(permission), requestCode)
        } else {
            Toast.makeText(this@MainActivity, "Permission already granted", Toast.LENGTH_SHORT)
                .show()
        /*    readcontacts()*/
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        var Permissions_counter: Int = 0
        if (requestCode == READ_CONTACT_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(
                    this@MainActivity,
                    "Read Contacts Permission Granted",
                    Toast.LENGTH_SHORT
                ).show()
                Permissions_counter += 1
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "Read ContactsPermission Denied",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        if (requestCode == WRITE_CONTACT_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(
                    this@MainActivity,
                    "Write Contacts Permission Granted",
                    Toast.LENGTH_SHORT
                ).show()
                Permissions_counter += 1
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "Write Contacts Permission Denied",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        Toast.makeText(this@MainActivity, "Counter" + Permissions_counter, Toast.LENGTH_SHORT)
            .show()
        if (Permissions_counter == 2) {
        /*    readcontacts()*/
        }
    }


    /*  @RequiresApi(Build.VERSION_CODES.O)

    val cols = listOf<String>(
        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
        ContactsContract.CommonDataKinds.Phone.NUMBER,

    ).toTypedArray()
    private fun insertContactPhoneNumber(

        phoneNumber: String,
        displayName: String,

    ) {


        val operations : ArrayList<ContentProviderOperation> =  ArrayList<ContentProviderOperation>();
        operations.add(
            ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null).build()
        )

        if (displayName.trim { it <= ' ' } != "" && phoneNumber.trim { it <= ' ' } != "") {
            operations.add(
                ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                    .withValue(
                        ContactsContract.Data.MIMETYPE,
                        ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE
                    )
                    .withValue(
                        ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,
                        displayName
                    ).build()
            )
            operations.add(
                ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                    .withValue(
                        ContactsContract.Data.MIMETYPE,
                        ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE
                    )
                    .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, phoneNumber).build()
            )
        }
        try {
            contentResolver.applyBatch(ContactsContract.AUTHORITY, operations)
            Toast.makeText(applicationContext, "Insert Success", Toast.LENGTH_SHORT).show()

        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(applicationContext, "Insertion failed", Toast.LENGTH_SHORT).show()
        }
    }

    private  fun readcontacts(){
        var from = listOf<String>(cols[0],cols[1]).toTypedArray()
        var to = intArrayOf(android.R.id.text1,android.R.id.text2)
        var rs = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI ,cols,
            null,
            null,
            cols[0]
        )
        var adapter = SimpleCursorAdapter(this,
            android.R.layout.simple_list_item_2,
            rs,
            from,
            to,
            0)
        Toast.makeText(this,"",Toast.LENGTH_LONG).show()


            fun onQueryTextChange(q: String): Boolean {

                var rs = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI ,
                    cols,
                    "${cols[0]} LIKE ?",
                    Array(1){"%$q%"},
                    cols[0])
                adapter.changeCursor(rs)




                return false
            }

            fun onQueryTextSubmit(q: String): Boolean {
                return false
            }

    */

}