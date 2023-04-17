package com.example.websearch

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SearchHistoryDatabase(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "search_history.db"
        private const val DATABASE_VERSION = 2
        const val TABLE_NAME = "search_history"
        const val COLUMN_ID = "_id"
        const val COLUMN_SEARCH_QUERY = "search_query"
        const val COLUMN_SAFE_SEARCH = "safe_search"
        const val COLUMN_TIMESTAMP = "timeStamp"
        const val COLUMN_SEARCH_TYPE = "search_type"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE_QUERY = "CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY," +
                "$COLUMN_SEARCH_QUERY TEXT NOT NULL," +
                "$COLUMN_SAFE_SEARCH INTEGER NOT NULL," +
                "$COLUMN_SEARCH_TYPE TEXT NOT NULL," +
                "$COLUMN_TIMESTAMP DATETIME NOT NULL" +
                ")"
        db.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val DROP_TABLE_QUERY = "DROP TABLE IF EXISTS $TABLE_NAME"
        db.execSQL(DROP_TABLE_QUERY)
        onCreate(db)
    }
}
