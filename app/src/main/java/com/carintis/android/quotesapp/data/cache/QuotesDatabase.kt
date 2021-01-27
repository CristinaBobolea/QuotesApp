package com.carintis.android.quotesapp.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.carintis.android.quotesapp.domain.Quote

@Database(
    entities = [
      CachedQuote::class
    ],
    version = 4
)
abstract class QuotesDatabase : RoomDatabase() {

  abstract fun quotesDao(): QuotesDao

}