package com.dicoding.submisionfundamental3.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.dicoding.submisionfundamental3.db.DatabaseContract.FavoriteColumns.Companion.TABLE_NAME

internal class DataBaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE__NAME, null, DATABASE_VERSION) {

    companion object{
        private const val DATABASE__NAME = "dbfavuser"
        private const val DATABASE_VERSION = 1

        private const val SQL_CREATE_TABLE_NOTE = "CREATE TABLE $TABLE_NAME" +
                "(${DatabaseContract.FavoriteColumns.USERNAME} TEXT PRIMARY KEY  NOT NULL," +
                "${DatabaseContract.FavoriteColumns.NAME} TEXT NOT NULL," +
                "${DatabaseContract.FavoriteColumns.USERIMAGE} TEXT NOT NULL," +
                "${DatabaseContract.FavoriteColumns.COMPANY} TEXT NOT NULL," +
                "${DatabaseContract.FavoriteColumns.LOCATION} TEXT NOT NULL," +
                "${DatabaseContract.FavoriteColumns.REPOSITORY} TEXT NOT NULL," +
                "${DatabaseContract.FavoriteColumns.FOLLOWING} TEXT NOT NULL," +
                "${DatabaseContract.FavoriteColumns.FOLLOWERS} TEXT NOT NULL," +
                "${DatabaseContract.FavoriteColumns.FAVORITE} TEXT NOT NULL)"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE_NOTE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
}