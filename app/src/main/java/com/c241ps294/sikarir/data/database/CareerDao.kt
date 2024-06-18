package com.c241ps294.sikarir.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.c241ps294.sikarir.data.remote.response.ListCareerItem
@Dao
interface CareerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCareer(career: List<ListCareerItem>)

    @Query("SELECT * FROM career")
    fun getAllCareers(): PagingSource<Int, ListCareerItem>

    @Query("DELETE FROM career")
    suspend fun deleteCareer()
}
