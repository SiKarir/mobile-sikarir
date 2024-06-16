package com.c241ps294.sikarir.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.c241ps294.sikarir.data.helper.Converters
import com.c241ps294.sikarir.data.remote.response.ListCareerItem

@Database(
    entities = [ListCareerItem::class, RemoteKeys::class],
    version = 2,
    exportSchema = false
)

@TypeConverters(Converters::class)
abstract class CareerDatabase : RoomDatabase() {

    abstract fun careerDao(): CareerDao
    abstract fun remoteKeysDao(): RemoteKeysDao

    companion object {
        @Volatile
        private var INSTANCE: CareerDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): CareerDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    CareerDatabase::class.java, "career_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}