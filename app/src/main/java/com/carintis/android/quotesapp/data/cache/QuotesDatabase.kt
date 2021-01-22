package com.carintis.android.quotesapp.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
      CachedQuote::class
    ],
    version = 2
)
abstract class QuotesDatabase : RoomDatabase() {

  abstract fun quotesDao(): QuotesDao
}