package com.xfactor.noted.database

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Start with version == 1
 * Version is essential for future migrations
 **/
@Database(entities = arrayOf(List::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun listDao(): ListDao
}