package com.example.ourproject


import android.content.*
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri
import android.provider.BaseColumns._ID
import android.text.TextUtils
import java.lang.IllegalArgumentException
import java.util.HashMap
import android.content.ContentValues

class CustomersProvider() : ContentProvider() {
    companion object {
        val PROVIDER_NAME = "com.example.MyApplication.CustomersProvider"
        val URL = "content://" + PROVIDER_NAME + "/customers"
        val CONTENT_URI = Uri.parse(URL)


        val USERNAME = "username"
        val PASSWORD = "password"

        private val CUSTOMERS_PROJECTION_MAP: HashMap<String, String>? = null

        val CUSTOMERS = 1
        val CUSTOMERS_ID = 2
        val uriMatcher: UriMatcher? = null
        val DATABASE_NAME = "USERS"
        val CUSTOMERS_TABLE_NAME = "customers"
        val DATABASE_VERSION = 1

        val CREATE_DB_TABLE = " CREATE TABLE " + CUSTOMERS_TABLE_NAME +
                " (username TEXT NOT NULL , " + " password TEXT NOT NULL);"


        private var sUriMatcher = UriMatcher(UriMatcher.NO_MATCH);

        init {

            sUriMatcher.addURI(PROVIDER_NAME, "customers", CUSTOMERS);
            sUriMatcher.addURI(PROVIDER_NAME, "customers/#", CUSTOMERS_ID);
        }
    }
//CREATING DATABASE
    private var db: SQLiteDatabase? = null
//EXECUTES TABLE WE MADE
    private class DatabaseHelper internal constructor(context: Context?) :
        SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    //ONCREATE : Executes database (inside it table)
        override fun onCreate(db: SQLiteDatabase) {
            db.execSQL(CREATE_DB_TABLE)
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            db.execSQL("DROP TABLE IF EXISTS " +CUSTOMERS_TABLE_NAME)
            onCreate(db)
        }
    }
//TILL HERE




//ON CREATE FUNCTION
    override fun onCreate(): Boolean {
        val context = context
        val dbHelper = DatabaseHelper(context)
        /**
         * Create a write able database which will trigger its
         * creation if it doesn't already exist.  */
        db = dbHelper.writableDatabase
        return if (db == null) false else true
    }



    
    //PROVIDER FINCTIONS (QUEREIS)
    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        /**
         * Add a new student record
         */
        val rowID = db!!.insert(CUSTOMERS_TABLE_NAME, "", values)
        /**
         * If record is added successfully
         */

        //adding la 14 (inside toast) everyone new insert a new username and pass it will add one
        if (rowID > 0) {
            val _uri = ContentUris.withAppendedId(CONTENT_URI, rowID)
            context!!.contentResolver.notifyChange(_uri, null)
            return _uri
        }
        throw SQLException("Failed to add a record into $uri")
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        TODO("Not yet implemented")
    }
//calling yestad3e username and password ele already inserted
    override fun query(
        uri: Uri, projection: Array<String>?,
        selection: String?, selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        var sortOrder = sortOrder
        val qb = SQLiteQueryBuilder()
        qb.tables = CUSTOMERS_TABLE_NAME
        if (uriMatcher != null) {
            when (uriMatcher.match(uri)) {
              /*   CUSTOMERS -> qb.projectionMap =
                    CUSTOMERS_PROJECTION_MAP*/
                CUSTOMERS_ID -> qb.appendWhere(_ID + "=" + uri.pathSegments[1])
                else -> {null
                }
            }
        }

//calling by sort order
        if (sortOrder == null || sortOrder === "") {
            /**
             * By default sort on  names
             */
            sortOrder = USERNAME
        }
        val c = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder)
        /**
         * register to watch a content URI for changes  */
        c.setNotificationUri(context!!.contentResolver, uri)
        return c
    }


    /*override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        var count = 0
        when (uriMatcher!!.match(uri)) {
        CUSTOMERS-> count = db!!.delete(
                CUSTOMERS_TABLE_NAME, selection,
                selectionArgs
            )
            CUSTOMERS_ID -> {
                val id = uri.pathSegments[1]
*//*                count = db!!.delete(
                    CUSTOMERS_TABLE_NAME,
                    _ID + " = " + id +
                            if (!TextUtils.isEmpty(selection)) " AND ($selection)" else "",
                    selectionArgs
                )*//*
            }
            else -> throw IllegalArgumentException("Unknown URI $uri")
        }
        context!!.contentResolver.notifyChange(uri, null)
        return count
    }*/

    override fun getType(uri: Uri): String? {
        when (uriMatcher!!.match(uri)) {
            CUSTOMERS -> return "vnd.android.cursor.dir/vnd.example.customers"
            CUSTOMERS_ID -> return "vnd.android.cursor.item/vnd.example.customers"
            else -> throw IllegalArgumentException("Unsupported URI: $uri")
        }
    }

}

