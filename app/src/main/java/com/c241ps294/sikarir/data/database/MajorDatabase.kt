package com.c241ps294.sikarir.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.c241ps294.sikarir.data.remote.response.ListMajorItem

@Database(
    entities = [ListMajorItem::class, RemoteKeys::class],
    version = 2,
    exportSchema = false
)
abstract class MajorDatabase : RoomDatabase(){
    abstract fun majorDao(): MajorDao
    abstract fun remoteKeysDao(): RemoteKeysDao

    companion object {
        @Volatile
        private var INSTANCE: MajorDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): MajorDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    MajorDatabase::class.java, "major_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}