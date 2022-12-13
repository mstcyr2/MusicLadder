package com.example.finalproject.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.core.database.getStringOrNull
import com.example.finalproject.database.models.LikedSongsModel
import com.example.finalproject.database.models.PlaylistModel
import com.example.finalproject.database.models.PlaylistSongsModel
import com.example.finalproject.database.models.SongModel
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper

interface SongInterface {
    suspend fun readSongs(): ArrayList<SongModel>
}



class DatabaseHandler(context: Context?) : SQLiteAssetHelper(context, DB_NAME, null, DB_VERSION), SongInterface {
    companion object {
        // Database constants
        private const val DB_NAME = "musicladder.db"
        private const val DB_VERSION = 1
        private const val SONGS_TABLE_NAME = "songs"
        private const val USERS_TABLE_NAME = "users"
        private const val LIKED_SONGS_TABLE_NAME = "likedsongs"
        private const val PLAYLISTS_SONGS_TABLE_NAME = "playlists_songs"
        private const val PLAYLISTS_TABLE_NAME = "playlists"

        // SongModel constants
        private const val ID_COL = "song_id"
        private const val NAME_COL = "song_name"
        private const val ARTIST_COL = "artist_name"
        private const val CATEGORY_COL = "category"
        private const val LIKES_COL = "likes"
        private const val SPOTIFY_COL = "spotify_link"

        // UserModel constraints
        private const val ID_COL_USER = "user_id"
        private const val USERNAME_COL_USER = "username"
        private const val PASSWORD_COL_USER = "password"

        //PlaylistModel constraints
        private const val ID_COL_PLAYLIST = "playlist_id"
        private const val USER_ID_COL_PLAYLIST = "playlist_user_id"
        private const val PLAYLIST_NAME_COL = "playlist_name"

        //PlaylistSongModel constraints TODO: USING THIS ONE
        private const val ID_COL_PLAYLIST_SONG = "playlist_song_id"
        private const val SONG_PLAYLIST_ID = "song_playlist_id"
        private const val USER_ID_COL_PLAYLIST_SONG = "playlist_song_user_id"
        private const val SONG_ID_COL_PLAYLIST_SONG = "song_playlist_song_id"


        // LikedSongsModel constraints
        private const val ID_COL_LIKED_SONGS = "liked_song_id"
        private const val USER_ID_COL_LIKED = "liked_user_id"
        private const val LIKED_SONG_COL = "liked_song"

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $SONGS_TABLE_NAME")
        db?.execSQL("DROP TABLE IF EXISTS $USERS_TABLE_NAME")
        db?.execSQL("DROP TABLE IF EXISTS $LIKED_SONGS_TABLE_NAME")
        db?.execSQL("DROP TABLE IF EXISTS $PLAYLISTS_TABLE_NAME")
        db?.execSQL("DROP TABLE IF EXISTS $PLAYLISTS_SONGS_TABLE_NAME")
        onCreate(db)
    }

    fun createTables() {
        val db = this.writableDatabase
        onCreate(db)
        db.close()
    }
    

    fun addNewPlaylist(user_id: String, playlist_name: String) {
        val db = this.writableDatabase

        val values = ContentValues()

        values.put(ID_COL_PLAYLIST, getRandomString(10))
        values.put(USER_ID_COL_PLAYLIST, user_id)
        values.put(PLAYLIST_NAME_COL, playlist_name)

        db.insertOrThrow(PLAYLISTS_TABLE_NAME, null, values)
        db.close()
    }

    /**
     * This will create a new song that is bound to a playlist
     *
     * EXAMPLE:
     *
     * When someone clicks the "add to playlist button", it will
     * call this function matching these values;
     * playlist_id = current category (rap / pop)
     * user_id = current vm logged in-user
     * song_id = currently selected card song_id
     */
    fun addNewSongToPlaylist(
        playlist_id: String?,
        user_id: String?,
        song_id: String?
    ) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.clear()

        values.put(ID_COL_PLAYLIST_SONG, getRandomString(10))
        values.put(SONG_PLAYLIST_ID, playlist_id)
        values.put(USER_ID_COL_PLAYLIST_SONG, user_id)
        values.put(SONG_ID_COL_PLAYLIST_SONG, song_id)

        db.insertOrThrow(PLAYLISTS_SONGS_TABLE_NAME, null, values)

        db.close()
    }

    /**
     * Removes a song from a playlist based on the matching of
     * current logged in user_id, and the currently selected
     * song_id from the playlists page
     */
    fun deleteSongFromPlaylist(
        playlist_id: String?,
        user_id: String?,
        song_id: String?
    ){
        val db = this.writableDatabase


        val query = ("DELETE FROM $PLAYLISTS_SONGS_TABLE_NAME " +
                "WHERE $USER_ID_COL_PLAYLIST_SONG='$user_id' " +
                "AND $SONG_ID_COL_PLAYLIST_SONG='$song_id' " +
                "AND $SONG_PLAYLIST_ID='$playlist_id'")

        db?.execSQL(query)
        db.close()
    }

    /**
     * This returns a list of string song_id's to be displayed
     * in the desired playlist_id obtained from the playlist_songs table
     * by matching the current logged in user_id and the current
     * category (rap / pop)
     */
    fun getSongsFromPlaylist(
        playlist_id: String?,
        user_id: String?
    ) : ArrayList<String> {
        val db = this.readableDatabase

        val cursor: Cursor = db.rawQuery(
            "SELECT * FROM $PLAYLISTS_SONGS_TABLE_NAME " +
                    "WHERE $USER_ID_COL_PLAYLIST_SONG='$user_id' AND $SONG_PLAYLIST_ID='$playlist_id'", null)
        val fetchedSongs: ArrayList<PlaylistSongsModel> = ArrayList()

        if (cursor.moveToFirst()) {
            do {
                fetchedSongs.add(
                    PlaylistSongsModel(
                        cursor.getString(1), // playlist_id
                        cursor.getString(2), // user_id
                        cursor.getString(3) // song_id
                    )
                )
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        val songIdsInPlaylist: ArrayList<String> = ArrayList()

        for (i in fetchedSongs) {
            songIdsInPlaylist.add(i.song_id)
        }

        return songIdsInPlaylist
    }

    fun getUserPlaylists(user_id : String) : ArrayList<PlaylistModel> {
        val db = this.readableDatabase

        val cursor = db.rawQuery("SELECT * FROM $PLAYLISTS_TABLE_NAME WHERE " +
                "$USER_ID_COL_PLAYLIST='$user_id'", null)
        val playlists = ArrayList<PlaylistModel>()
        if (cursor.moveToFirst()) {
            do {
                playlists.add(
                    PlaylistModel(
                        cursor.getString(0), // playlist_id
                        cursor.getString(1), // user_id
                        cursor.getString(2) // name
                    )
                )
            } while (cursor.moveToNext())
            db.close()
            cursor.close()
            return playlists
        }
        db.close()
        cursor.close()
        return playlists
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

        db.insertOrThrow(SONGS_TABLE_NAME, null, values)
        db.close()
    }

    fun addNewUser(
        username: String?,
        password: String?
    ) : String {
        val db = this.writableDatabase
        val values = ContentValues()
        val userID = getRandomString(10)

        values.put(ID_COL_USER, userID)
        values.put(USERNAME_COL_USER, username)
        values.put(PASSWORD_COL_USER, password)

        db.insertOrThrow(USERS_TABLE_NAME, null, values)
        db.close()

        return userID
    }

    private fun getRandomString(length: Int) : String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

    fun likeSong(user_id: String, song: SongModel) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.clear()

        val newLiked = song.likes + 1

        song.likes = newLiked

        val query = ("DELETE FROM $SONGS_TABLE_NAME WHERE $ID_COL='${song.song_id}'")

        db?.execSQL(query)

        values.put(ID_COL, song.song_id)
        values.put(NAME_COL, song.song_name)
        values.put(ARTIST_COL, song.artist_name)
        values.put(CATEGORY_COL, song.category)
        values.put(LIKES_COL, song.likes)
        values.put(SPOTIFY_COL, song.spotify_link)

        db.insert(SONGS_TABLE_NAME, null, values)

        values.clear()


        values.put(ID_COL_LIKED_SONGS, getRandomString(10))
        values.put(USER_ID_COL_LIKED, user_id)
        values.put(LIKED_SONG_COL, song.song_id)

        db.insert(LIKED_SONGS_TABLE_NAME, null, values)
        db.close()
    }

    fun unlikeSong(user_id: String, song: SongModel) {
        val db = this.writableDatabase

        val query = ("DELETE FROM $LIKED_SONGS_TABLE_NAME WHERE $USER_ID_COL_LIKED='$user_id' AND $LIKED_SONG_COL='${song.song_id}'")

        db?.execSQL(query)

        val newLiked = song.likes - 1

        if (newLiked <= 0) {
            song.likes = 0
        } else {
            song.likes = newLiked
        }

        val query1 = ("DELETE FROM $SONGS_TABLE_NAME WHERE $ID_COL='${song.song_id}'")

        db?.execSQL(query1)

        val values = ContentValues()

        values.put(ID_COL, song.song_id)
        values.put(NAME_COL, song.song_name)
        values.put(ARTIST_COL, song.artist_name)
        values.put(CATEGORY_COL, song.category)
        values.put(LIKES_COL, song.likes)
        values.put(SPOTIFY_COL, song.spotify_link)

        db.insert(SONGS_TABLE_NAME, null, values)
        db.close()
    }

    /**
     * Return an array of "liked" song id's from strings
     */
    fun getLikedSongs(user_id: String): ArrayList<String> {
        val db = this.readableDatabase
        val cursor: Cursor = db.rawQuery(
            "SELECT * FROM $LIKED_SONGS_TABLE_NAME " +
                    "WHERE $USER_ID_COL_LIKED='$user_id'", null)
        val likedSongs: ArrayList<LikedSongsModel> = ArrayList()

        if (cursor.moveToFirst()) {
            do {
                likedSongs.add(
                    LikedSongsModel(
                        cursor.getString(0), // unique_id
                        cursor.getString(1), // user_id
                        cursor.getString(2) // liked_song_id
                    )
                )
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        val namesOfSongs: ArrayList<String> = ArrayList()

        for (i in likedSongs) {
            namesOfSongs.add(i.liked_song)
        }

        return namesOfSongs
    }

    fun findUser(username: String, password: String) : String? {
        val db = this.readableDatabase
        val cursor : Cursor = db.rawQuery(
            "SELECT $ID_COL_USER FROM $USERS_TABLE_NAME " +
                    "WHERE $USERNAME_COL_USER='$username' " +
                    "AND $PASSWORD_COL_USER='$password'" ,
            null)
        if (cursor.moveToFirst()) {
            val str = cursor.getStringOrNull(0)
            db.close()
            cursor.close()
            return str
        }
        cursor.close()
        db.close()
        return null
    }

    fun getUserName(user_id : String) : String? {
        val db = this.readableDatabase
        val cursor : Cursor = db.rawQuery("SELECT $USERNAME_COL_USER FROM $USERS_TABLE_NAME " +
                "WHERE $ID_COL_USER='$user_id'", null)
        if (cursor.moveToFirst()) {
            val str = cursor.getStringOrNull(0)
            db.close()
            cursor.close()
            return str
        }
        cursor.close()
        db.close()
        return null
    }

    override suspend fun readSongs(): ArrayList<SongModel> {
        val db = this.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM $SONGS_TABLE_NAME", null)
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
        db.close()

        return songs
    }

    /**
     * Returns DESCENDING list of objects by likes column
     */
    fun getOrderedByLikes(user_id : String = "") : ArrayList<SongModel> {

        val db = this.readableDatabase
        val cursor: Cursor = db.rawQuery(
            "SELECT * FROM $SONGS_TABLE_NAME ORDER BY $LIKES_COL DESC", null)
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
        db.close()

        return songs
    }

    fun getOrderedByGenre(category: String) : ArrayList<SongModel> {
        val db = this.readableDatabase
        val cursor: Cursor = db.rawQuery(
            "SELECT * FROM $SONGS_TABLE_NAME WHERE $CATEGORY_COL='$category' ORDER BY $LIKES_COL DESC", null)
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
        db.close()

        return songs
    }
}