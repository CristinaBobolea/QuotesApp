package com.carintis.quotesapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.carintis.quotesapp.data.Quote

/**
 * Favorites database schema.
 */
@Database(entities = [Quote::class], version = 1)
abstract class FavoritesDatabase : RoomDatabase() {
    companion object {

        private const val DATABASE_NAME = "favorites.db"

        fun create(context: Context): FavoritesDatabase =
            Room.databaseBuilder(context, FavoritesDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }

    abstract fun favoritesDao(): FavoritesDao
}