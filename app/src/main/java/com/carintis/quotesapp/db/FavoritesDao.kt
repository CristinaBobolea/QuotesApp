package com.carintis.quotesapp.db

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.carintis.quotesapp.data.Quote

@Dao
interface FavoritesDao {

    @Query("SELECT * FROM Quote")
    fun getFavorites(): DataSource.Factory<Int, Quote>

    @Query("SELECT * FROM Quote WHERE id = :id ")
    fun getFavorite(id: String): LiveData<Quote>

    @Query("SELECT count(*) FROM Quote")
    fun getFavoriteCount(): LiveData<Int>

    @Insert(onConflict = REPLACE)
    fun addFavorite(quote: Quote)

    @Delete
    fun removeFavorite(quote: Quote)
}