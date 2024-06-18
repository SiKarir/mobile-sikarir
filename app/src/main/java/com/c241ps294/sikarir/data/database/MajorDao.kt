package com.c241ps294.sikarir.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.c241ps294.sikarir.data.remote.response.ListMajorItem
@Dao
interface MajorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMajor(major: List<ListMajorItem>)

    @Query("SELECT * FROM major")
    fun getAllMajors(): PagingSource<Int, ListMajorItem>

    @Query("DELETE FROM major")
    suspend fun deleteMajor()
}