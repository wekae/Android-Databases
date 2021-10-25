package com.xfactor.noted.database

import androidx.room.*
import kotlin.collections.List

@Dao
interface ListDao {
    @Query("SELECT * FROM list")
    fun getAll(): List<com.xfactor.noted.database.List>

    // Transaction - queries are bundled together and executed at once
    @Transaction
    @Query("SELECT * FROM list")
    fun getListWithListItems(): List<ListWithListItems>

    @Insert
    fun insertAll(vararg lists: com.xfactor.noted.database.List)

    @Delete
    fun delete(list: com.xfactor.noted.database.List)
}