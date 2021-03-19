package com.example.mynotes.data.datasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mynotes.data.model.NoteModel

@Database(entities = [NoteModel::class], version = 1)
abstract class RoomDatabaseClient : RoomDatabase() {
    abstract fun getAppRoomDao(): RoomDao

    companion object {

        @Volatile
        private var database: RoomDatabaseClient? = null

        @Synchronized
        fun getInstance(context: Context): RoomDatabaseClient {
            if (database == null) {
                database = Room.databaseBuilder(
                    context,
                    RoomDatabaseClient::class.java,
                    "room_database"
                ).build()
            }

            return database as RoomDatabaseClient
        }
    }
}