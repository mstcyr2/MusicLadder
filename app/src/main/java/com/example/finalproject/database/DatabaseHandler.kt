package com.example.finalproject.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context?) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object {
        // Database constants
        private const val DB_NAME = "musicladder"
        private const val DB_VERSION = 1
        private const val TABLE_NAME = "songs"

        // SongModel constants
        private const val ID_COL = "id"
        private const val NAME_COL = "song_name"
        private const val ARTIST_COL = "artist_name"
        private const val CATEGORY_COL = "category"
        private const val LIKES_COL = "likes"
        private const val SPOTIFY_COL = "spotify_link"
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " TEXT PRIMARY KEY, "
                + NAME_COL + " TEXT,"
                + ARTIST_COL + " TEXT,"
                + CATEGORY_COL + " TEXT,"
                + LIKES_COL + " INTEGER,"
                + SPOTIFY_COL + " TEXT)")

        db?.execSQL(query)
    }

    fun createMainTable() {
        val db = this.writableDatabase

        val query1 = ("DROP TABLE $TABLE_NAME")

        db?.execSQL(query1)

        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " TEXT PRIMARY KEY, "
                + NAME_COL + " TEXT,"
                + ARTIST_COL + " TEXT,"
                + CATEGORY_COL + " TEXT,"
                + LIKES_COL + " INTEGER,"
                + SPOTIFY_COL + " TEXT)")

        db?.execSQL(query)
    }

    fun addNewSong(
        song_id: String?,
        song_name: String?,
        artist_name: String?,
        category: String?,
        likes: Int?,
        spotify_link: String?
    ) {
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(ID_COL, song_id)
        values.put(NAME_COL, song_name)
        values.put(ARTIST_COL, artist_name)
        values.put(CATEGORY_COL, category)
        values.put(LIKES_COL, likes)
        values.put(SPOTIFY_COL, spotify_link)

        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun deleteAllSongs() {
        val db = this.readableDatabase

        db.delete(TABLE_NAME, null, null);
    }

    fun readSongs(): ArrayList<SongModel> {
        val db = this.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        val songs: ArrayList<SongModel> = ArrayList()

        if (cursor.moveToFirst()) {
            do {
                songs.add(
                    SongModel(
                        cursor.getString(0), // song_id
                        cursor.getString(1), // song_name
                        cursor.getString(2), // artist_name
                        cursor.getString(3), // genre
                        cursor.getInt(4), // likes
                        cursor.getString(5) // spotify link
                    )
                )
            } while (cursor.moveToNext())
        }

        cursor.close()

        return songs
    }
}